package pl.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class TokenProvider {
   private TokenRepository tokenRepository;

   public Mono<Token> save(Token token) {
      return tokenRepository.save(token);
   }
}
