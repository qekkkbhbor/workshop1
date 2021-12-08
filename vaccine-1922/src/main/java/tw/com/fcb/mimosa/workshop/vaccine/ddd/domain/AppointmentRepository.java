package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain;

import java.util.List;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.ResidentProfile;

public interface AppointmentRepository {

  long save(Appointment domain);

  Appointment findById(long id);

  List<ResidentProfile> findResidents();
}
