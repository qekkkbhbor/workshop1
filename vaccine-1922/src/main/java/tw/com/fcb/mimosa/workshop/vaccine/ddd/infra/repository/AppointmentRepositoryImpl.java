package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Appointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler.ResidentAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.ResidentProfile;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {

  final ResidentRepository jpaRepository;
  final ResidentAssembler assembler;
  final ApplicationEventPublisher publisher;
  final ResidentProfileRepository jpaResidentProfileRepository;

  @Override
  public long save(Appointment domain) {
    var saved = jpaRepository
        .save(assembler.toEntity(domain));
    domain.domainEvents().forEach(publisher::publishEvent);
    return saved.getId();
  }

  @Override
  public Appointment findById(long id) {
    return jpaRepository.findById(id)
        .map(assembler::fromEntity)
        .orElseThrow();
  }

  @Override
  public List<ResidentProfile> findResidents() {
    return jpaResidentProfileRepository.findAll()
        .stream()
        .map(assembler::toProfile)
        .collect(Collectors.toList());
  }

}
