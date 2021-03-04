package pl.user;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

   @Query("SELECT u.id, u.email, u.password, u.name, u.user_role, u.is_active FROM user u WHERE u.email = :email")
   Mono<User> findByEmail(String email);

   @Query("SELECT user.email, user.name FROM user")
   Flux<User> getAll();
}
