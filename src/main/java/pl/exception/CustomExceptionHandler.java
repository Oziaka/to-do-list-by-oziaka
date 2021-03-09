package pl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class CustomExceptionHandler {

   @ResponseBody
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(RuntimeException.class)
   public Mono<String> handleRuntimeException(RuntimeException exception) {
      return Mono.just(exception.getMessage());
   }

   @ResponseBody
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(ValidationException.class)
   public Mono<ErrorMap> handleRuntimeException(ValidationException exception) {
      return Mono.just(exception.getErrorMap());
   }

}
