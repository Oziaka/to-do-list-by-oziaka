package pl.user_task_list;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserTaskListProvider {
   private UserTaskListRepository userTaskListRepository;

   public Mono<UserTaskList> save(UserTaskList userTaskList) {
      return userTaskListRepository.save(userTaskList);
   }

   public Mono<UserTaskList> findUserTaskList(Long taskListId, Long userId) {
      return userTaskListRepository.find(taskListId, userId);
   }
}
