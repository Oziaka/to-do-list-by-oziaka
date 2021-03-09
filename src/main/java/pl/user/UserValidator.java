package pl.user;

import org.springframework.stereotype.Component;
import pl.ErrorMap;
import pl.Validator;

@Component
public class UserValidator implements Validator<UserDto> {
   private static String EMAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";
   private static int MIN_PASSWORD_LENGTH = 5;
   private UserRepository userRepository;
   private static String PASSWORD_FIELD = "password";
   private static String EMAIL_FIELD = "email";

   private ErrorMap errorMap;

   public UserValidator(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   public ErrorMap valid(UserDto user) {
      errorMap = new ErrorMap();
      validEmail(user.getEmail());
      validPassword(user.getPassword());
      return errorMap;
   }

   private void validPassword(String password) {
      if (password == null) {
         errorMap.addError(PASSWORD_FIELD, "Password can not be empty");
      }
      if (password.length() < MIN_PASSWORD_LENGTH)
         errorMap.addError(PASSWORD_FIELD, "Password must be longer or equal 5");
   }

   private void validEmail(String email) {
      if (email == null) {
         errorMap.addError(EMAIL_FIELD, "Email can not be empty");
         return;
      }
      if (!email.matches(EMAIL_PATTERN))
         errorMap.addError(EMAIL_FIELD, "Email must be well preformed");
      userRepository.findByEmail(email).flatMap(u -> {
         if (u.getEmail() != null)
            errorMap.addError(EMAIL_FIELD, "Email is used");
         return null;
      }).subscribe();
   }
}
