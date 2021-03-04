package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.token.MailProvider;
import pl.token.Token;
import pl.token.TokenProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import java.util.UUID;

import static java.lang.Boolean.FALSE;

@AllArgsConstructor
@Service
public class UserService implements ReactiveUserDetailsService {

   private final String DEFAULT_USER_ROLE = "USER_ROLE";
   private UserRepository userRepository;
   private PasswordEncoder passwordEncoder;
   private TokenProvider tokenProvider;
   private MailProvider mailProvider;

   public Mono<User> addUser(User user) {
      user.setId(null);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setUserRole(DEFAULT_USER_ROLE);
      user.setIsActive(FALSE);
      return userRepository.save(user).flatMap(u -> {
         String tokenValue = UUID.randomUUID().toString();
         try {
            mailProvider.sendMail(u.getEmail(), "Potwierdzenie rejestracji na ToDo By Oziaka",
               "Aby aktywować konto wejdź w ten <a href=\"http://localhost:8080/token/" + tokenValue + "\">link</a>", true);
         } catch (MessagingException e) {
            e.printStackTrace();
         }
         return tokenProvider.save(Token.builder().userId(u.getId()).value(tokenValue).build()).thenReturn(u);
      });
   }

   public Flux<User> getUsers() {
      return userRepository.getAll();
   }

   @Override
   public Mono<UserDetails> findByUsername(String email) {
      return userRepository.findByEmail(email).cast(UserDetails.class);
   }
}
