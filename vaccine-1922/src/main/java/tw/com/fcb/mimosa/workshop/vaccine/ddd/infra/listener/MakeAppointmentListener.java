package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.listener;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event.AppointmentMade;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler.ResidentAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.EventEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.EventRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentProfileRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentRepository;

@Component
@RequiredArgsConstructor
public class MakeAppointmentListener {

  final EventRepository repository;

  @EventListener
  public void onAppointmentMade(AppointmentMade event) {
    var eventEntity = new EventEntity();
    eventEntity.setEventType(event.getClass().getSimpleName());
    eventEntity.setEventTime(LocalDateTime.now());
    eventEntity.setAggregateId(event.getResidentId());
    eventEntity.setPhoneNo(event.getPhoneNo());
    eventEntity.setNhiNo(event.getNhiNo());
    repository.save(eventEntity);

  }

}
