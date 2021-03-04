package pl.token;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends ReactiveCrudRepository<Token, Long> {
}
