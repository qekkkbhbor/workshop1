package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Data
@RequiredArgsConstructor
public class VaccineCanceled {

  final List<Vaccine> vaccines;

}
