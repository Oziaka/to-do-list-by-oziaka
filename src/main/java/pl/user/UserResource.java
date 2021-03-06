package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(produces = APPLICATION_STREAM_JSON_VALUE)
public class UserResource {
   private UserService userService;

   @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/register")
   public Mono<UserDto> register(@RequestBody UserDto user) {
      return userService.addUser(user);
   }

   @GetMapping(path = "/users")
   public Flux<UserDto> getUsers() {
      return userService.getUsers();
   }

   @RequestMapping(path = "/user")
   public Mono<Principal> getUsers(Principal principal) {
      return Mono.just(principal);
   }
}
