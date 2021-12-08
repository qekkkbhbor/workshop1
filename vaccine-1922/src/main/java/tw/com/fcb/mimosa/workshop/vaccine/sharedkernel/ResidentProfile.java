package tw.com.fcb.mimosa.workshop.vaccine.sharedkernel;

import java.util.List;
import lombok.Data;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Data
public class ResidentProfile {
  String nhiNo;
  String phoneNo;
  List<Vaccine> chooses;
}
