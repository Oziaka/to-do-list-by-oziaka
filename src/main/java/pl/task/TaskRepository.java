package pl.task;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {

   @Query("INSERT INTO task (name, description) VALUES (:name, :description)")
   Mono<Task> save(String name, String description);

   @Query("SELECT * FROM task")
   Flux<Task> getAll();
}
