package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;
import tw.com.fcb.mimosa.tracing.Traced;
@Traced
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/error")
public class ErrorController {

	  final ErrorService errservice;
	  @PostMapping("/err")
	  APIResponse<Error> getBCode(@RequestBody APIRequest<String> code) {
	    return APIResponse.success(errservice.getMessageByCode(code.getBody()));
	  }
	  
	  @PostMapping //RequestMethod.POST
	  APIResponse<Long> createError(@Validated @RequestBody APIRequest<Error> error) {
	    return APIResponse.success(errservice.createError(error.getBody()))
	        .map(Error::getId);
	  }
	  
}
