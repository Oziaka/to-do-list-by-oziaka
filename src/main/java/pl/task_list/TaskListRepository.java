package pl.task_list;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TaskListRepository extends ReactiveCrudRepository<TaskList, Long> {
   @Query("SELECT t.id, t.name, t.description FROM task_list t, user_task_list ut WHERE t.id = ut.task_list_id AND ut.user_id = :userId")
   Flux<TaskList> findAllByUserId(Long userId);
}
