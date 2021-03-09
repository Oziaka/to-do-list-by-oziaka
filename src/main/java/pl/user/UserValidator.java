package pl.user;

import org.springframework.stereotype.Component;
import pl.exception.ErrorMap;
import pl.validator.Validator;

@Component
public class UserValidator implements Validator<UserDto> {

   private final String EMAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";
   private final String EMAIL_FIELD_NAME = "email";
   private final String PASSWORD_FIELD_NAME = "password";
   private final int MIN_PASSWORD_LENGTH = 5;

   public UserValidator(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   private UserRepository userRepository;
   private ErrorMap errorMap;

   @Override
   public ErrorMap validate(UserDto userDto) {
      errorMap = new ErrorMap();
      validEmail(userDto.getEmail());
      validPassword(userDto.getPassword());
      return errorMap;
   }

   private void validPassword(String password) {
      if (password == null) {
         errorMap.addError(PASSWORD_FIELD_NAME, "Password can not be empty");
         return;
      }
      if (password.length() < MIN_PASSWORD_LENGTH)
         errorMap.addError(PASSWORD_FIELD_NAME, "Password length must by longer or equal 5");
   }

   private void validEmail(String email) {
      if (email == null) {
         errorMap.addError(EMAIL_FIELD_NAME, "Email can not be empty");
         return;
      }
      if (!email.matches(EMAIL_PATTERN))
         errorMap.addError(EMAIL_FIELD_NAME, "Email must be well preformed");
      userRepository.findByEmail(email).map(user -> {
         if (user != null)
            errorMap.addError(EMAIL_FIELD_NAME, "Email is used");
         return "end";
      }).subscribe();
   }
}
