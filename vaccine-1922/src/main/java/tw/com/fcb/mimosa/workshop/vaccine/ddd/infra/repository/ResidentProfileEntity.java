package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "RESIDENT_PROFILE")
@Entity
public class ResidentProfileEntity {

  @Id
  @GeneratedValue
  Long id;
  Long residentId;
  String nhiNo;
  String phoneNo;
  String chooses;
}
