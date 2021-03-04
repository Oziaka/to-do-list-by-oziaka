package pl.user_task_list;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserTaskListRepository extends ReactiveCrudRepository<UserTaskList, Long> {

   @Query("SELECT ut.id, ut.task_list_id, ut.user_id FROM user_task_list ut, user u WHERE u.id = ut.user_id AND u.id = :userId AND ut.task_list_id = :taskListId")
   Mono<UserTaskList> find(Long taskListId, Long userId);
}
