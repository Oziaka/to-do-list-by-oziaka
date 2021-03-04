package pl.task;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {

   @Query("SELECT * FROM task t WHERE t.task_list_id = :taskListId")
   Mono<Task> findAll(Long taskListId);
}
