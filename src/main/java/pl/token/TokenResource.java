package pl.token;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenResource {
   private TokenService tokenService;
}
