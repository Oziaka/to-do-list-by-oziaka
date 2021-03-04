package pl.token;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TokenRepository extends ReactiveCrudRepository<Token, Long> {
   @Query("SELECT * FROM token t WHERE t.value = :value")
   Mono<Token> findByValue(String value);
}
