package tw.com.fcb.mimosa.examples.gettingstarted;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIErrorException;

@Service
@RequiredArgsConstructor
public class ErrorService {
  final ErrorRepository errRepository;

  public Error getMessageByCode(String code) {
    return errRepository.findByCode(code)
        .orElseThrow(() -> {
          return new APIErrorException(err -> err.code("ERR0").message("Error code not found"));
        });
  }

  public Error createError(Error error) {
    return errRepository.save(error);
  }

}
