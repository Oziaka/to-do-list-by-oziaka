package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class UserResource {
   private UserService userService;

   @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE, path = "/register")
   public Mono<User> register(@RequestBody User user) {
      return userService.addUser(user);
   }

   @GetMapping(path = "/user", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
   public Flux<User> getUsers() {
      return userService.getUsers();
   }
}
