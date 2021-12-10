package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.query;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.ResidentInfo;

@Service
@RequiredArgsConstructor
public class QueryResidentsInfo {

  final AppointmentRepository repository;

  public List<ResidentInfo> getResidentsInfo() {
    return repository.findResidentsInfo();
  }

}
