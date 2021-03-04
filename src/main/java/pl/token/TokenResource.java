package pl.token;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.user.User;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class TokenResource {
   private TokenService tokenService;

   @GetMapping("/{tokenValue}")
   public Mono<User> activeAccount(@PathVariable String tokenValue){
      return tokenService.activeAccount(tokenValue);
   }

}
