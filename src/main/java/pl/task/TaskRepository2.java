package pl.task;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TaskRepository2 extends ReactiveCrudRepository<Task, Long> {

   @Query("SELECT * FROM task t WHERE t.task_list_id = :taskListId")
   Flux<Task> findAll(Long taskListId);

   @Query("SELECT t.id, t.name, t.description, t.term, t.data_od_execution, t.is_done, t.is_important, ,t.is_urgent, t.task_list_id FROM task t, user_task_list ut WHERE :userId = ut.user_id AND ut.task_list_id = t.task_list_id")
   Flux<Task> findAllUserTasks(Long userId);
}
