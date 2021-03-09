package pl.task;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {

   @Query("SELECT * FROM task t WHERE t.task_list_id = :taskListId")
   Flux<Task> findAll(Long taskListId);

   @Query("SELECT t.* " +
      "FROM task t, user_task_list ut WHERE :userId = ut.user_id AND ut.task_list_id = t.task_list_id")
   Flux<Task> findAllUserTasks(Long userId);

   @Query("SELECT t.* FROM task t, task_list tl, user_task_list utl WHERE t.id = :taskId AND utl.user_id = :userId AND " +
      ":taskListId = tl.id AND t.task_list_id = tl.id AND utl.task_list_id = tl.id")
   Mono<Task> findByUserIdAndTaskListIdAndTaskId(Long userId, Long taskListId, Long taskId);

}
