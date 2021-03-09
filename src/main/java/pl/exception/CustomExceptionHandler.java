package pl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class CustomExceptionHandler {

   @ResponseStatus(BAD_REQUEST)
   @ExceptionHandler(ValidationException.class)
   public Mono handleValidationException(ValidationException exception) {
      return Mono.just(exception.errorMap);
   }

   @ExceptionHandler(RuntimeException.class)
   public Mono handleAllException(RuntimeException exception) {
      return Mono.just(exception.getMessage());
   }
}
