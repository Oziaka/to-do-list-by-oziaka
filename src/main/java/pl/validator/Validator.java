package pl.validator;

import pl.exception.ErrorMap;

public interface Validator<T> {
   ErrorMap validate(T object);
}
