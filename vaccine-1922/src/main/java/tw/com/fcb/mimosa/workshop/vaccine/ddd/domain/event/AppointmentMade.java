package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event;

import lombok.Data;

@Data
public class AppointmentMade {
  long residentId;
  String nhiNo;
  String phoneNo;
}
