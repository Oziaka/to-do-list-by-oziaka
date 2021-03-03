package pl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import pl.user.UserService;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
   @Bean
   public SecurityWebFilterChain securityWebFilterChain(
      ServerHttpSecurity http) {
      return http
         .authorizeExchange().pathMatchers("/register", "/login","/user").permitAll()
         .anyExchange().authenticated()
         .and().formLogin()
         .and()
         .httpBasic()
         .and()
         .csrf().disable()
         .build();
   }

   @Bean
   ReactiveAuthenticationManager authenticationManager(UserService userService) {
      return new UserDetailsRepositoryReactiveAuthenticationManager(userService);
   }


}
