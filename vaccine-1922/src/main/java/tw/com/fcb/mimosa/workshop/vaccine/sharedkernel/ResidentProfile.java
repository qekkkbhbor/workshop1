package tw.com.fcb.mimosa.workshop.vaccine.sharedkernel;

import java.util.List;

import lombok.Data;

@Data
public class ResidentProfile {
  String nhiNo;
  String phoneNo;
  List<Vaccine> chooses;

}
