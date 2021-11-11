package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplaceUserDto {

  @NotBlank
  String name;
}
