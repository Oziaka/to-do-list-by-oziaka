package pl.exception;

import pl.ErrorMap;

public class ValidationException extends RuntimeException {
   ErrorMap errorMap = new ErrorMap();

   public ValidationException(ErrorMap errorMap) {
      this.errorMap = errorMap;
   }

   public ErrorMap getErrorMap() {
      return errorMap;
   }
}
