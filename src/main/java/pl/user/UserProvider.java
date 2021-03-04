package pl.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;

@AllArgsConstructor
@Service
public class UserProvider {
   private UserRepository userRepository;

   public Mono<User> getUser(Principal principal) {
      return userRepository.findByEmail(principal.getName());
   }

   public Mono<User> getUser(Long userId) {
      return userRepository.findById(userId);
   }

   public Mono<User> save(User user) {
      return userRepository.save(user);
   }
}
