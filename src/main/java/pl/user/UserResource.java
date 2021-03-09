package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class UserResource {
   private UserService userService;

   @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE, path = "/register")
   public Mono<UserDto> register(@RequestBody UserDto userDto) {
      return userService.addUser(userDto);
   }

   @GetMapping(path = "/users", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
   public Flux<UserDto> getUsers() {
      return userService.getUsers();
   }

   @RequestMapping(path = "/user", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
   public Mono<Principal> getUsers(Principal principal) {
      return Mono.just(principal);
   }
}
