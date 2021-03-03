package pl.user;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

   @Query("INSERT INTO user (email,password,name,user_role) VALUES(:email,:password,:name,:userRole)")
   Mono<User> save(String email, String password, String name,String userRole);

   @Query("SELECT user.email, user.password, user.name, user.user_role FROM user WHERE user.email = :email")
   Mono<User> findByEmail(String email);

   @Query("SELECT user.email, user.name FROM user")
   Flux<User> getAll();
}
