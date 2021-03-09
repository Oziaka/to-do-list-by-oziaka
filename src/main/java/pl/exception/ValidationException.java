package pl.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
   private ErrorMap errorMap;

   public ValidationException(ErrorMap errorMap) {
      this.errorMap = errorMap;
   }
}
