package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.Boolean.TRUE;

@AllArgsConstructor
@Service
public class UserService implements ReactiveUserDetailsService {

   private final String DEFAULT_USER_ROLE = "USER_ROLE";
   private UserRepository userRepository;
   private PasswordEncoder passwordEncoder;

   public Mono<User> addUser(User user) {
      user.setId(null);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setUserRole(DEFAULT_USER_ROLE);
      user.setIsActive(TRUE);
      return userRepository.save(user);
   }

   public Flux<User> getUsers() {
      return userRepository.getAll();
   }

   @Override
   public Mono<UserDetails> findByUsername(String email) {
      return userRepository.findByEmail(email).cast(UserDetails.class);
   }
}
