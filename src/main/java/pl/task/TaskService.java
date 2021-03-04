package pl.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.user.UserProvider;
import pl.user_task_list.UserTaskListProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static java.lang.Boolean.FALSE;

@Service
@AllArgsConstructor
public class TaskService {
   private TaskRepository taskRepository;
   private UserProvider userProvider;
   private UserTaskListProvider userTaskListProvider;

   public Mono<Task> addTask(Principal principal, Long taskListId, Task task) {
      task.setTaskListId(taskListId);
      task.setIsDone(FALSE);
      return userProvider.getUser(principal).flatMap(u ->
         userTaskListProvider.findUserTaskList(taskListId, u.getId()).flatMap(ut ->
            (ut.getId() != null) ?
               taskRepository.save(task) :
               Mono.error(new RuntimeException("There is no your property"))));
   }

   public Flux<Task> getTasks(Principal principal, Long taskListId) {
      return userProvider.getUser(principal).flatMapMany(u ->
         userTaskListProvider.findUserTaskList(taskListId, u.getId()).flatMap(ut ->
            (ut.getId() != null) ?
               taskRepository.findAll(taskListId) :
               Mono.error(new RuntimeException("There is no yout property"))));
   }
}
