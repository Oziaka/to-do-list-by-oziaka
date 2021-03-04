package pl.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserProvider;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TokenService {
   private TokenRepository tokenRepository;
   private UserProvider userProvider;

   public Mono<User> activeAccount(String tokenValue) {
      return tokenRepository.findByValue(tokenValue).flatMap(t -> userProvider.getUser(t.getUserId()).flatMap(u -> {
         u.setIsActive(Boolean.TRUE);
         tokenRepository.deleteById(t.getId());
         return userProvider.save(u);
      }));
   }
}
