package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ErrorMap;
import pl.exception.ValidationException;
import pl.token.MailProvider;
import pl.token.Token;
import pl.token.TokenProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

@AllArgsConstructor
@Service
public class UserService implements ReactiveUserDetailsService {

   private final String DEFAULT_USER_ROLE = "USER_ROLE";
   private UserRepository userRepository;
   private PasswordEncoder passwordEncoder;
   private TokenProvider tokenProvider;
   private MailProvider mailProvider;
   private UserValidator userValidator;

   public Mono<UserDto> addUser(UserDto userDto) {
      ErrorMap errorMap = userValidator.valid(userDto);
      if (!errorMap.isEmpty())
         return Mono.error(new ValidationException(errorMap));
      User user = UserMapper.toEntity(userDto);
      addDefaultValues(user);
      return userRepository.save(user).flatMap(u -> performTokenAndSendEmail(user, u));
   }

   private Mono<UserDto> performTokenAndSendEmail(User user, User u) {
      String tokenValue = UUID.randomUUID().toString();
      try {
         prefromAndSendEmail(u, tokenValue);
      } catch (MessagingException e) {
         throw new RuntimeException(e.getMessage());
      }
      return tokenProvider.save(Token.builder().userId(u.getId()).value(tokenValue).build()).thenReturn(UserMapper.toDto(user));
   }

   private void prefromAndSendEmail(User u, String tokenValue) throws MessagingException {
      mailProvider.sendMail(u.getEmail(), "Potwierdzenie rejestracji na To Do List By Oziaka",
         "Aby aktywować konto wejdź w ten <a href=\"http://localhost:8080/token/" + tokenValue + "\">link</a>", true);
   }

   private User addDefaultValues(User user) {
      user.setId(null);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setUserRole(DEFAULT_USER_ROLE);
      user.setIsActive(TRUE);
      return user;
   }

   public Flux<UserDto> getUsers() {
      return userRepository.getAll().map(UserMapper::toDto);
   }

   @Override
   public Mono<UserDetails> findByUsername(String email) {
      return userRepository.findByEmail(email).cast(UserDetails.class);
   }
}
