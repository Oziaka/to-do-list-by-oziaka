package pl.task_list;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.ErrorMap;
import pl.exception.ValidationException;
import pl.user.UserProvider;
import pl.user_task_list.UserTaskList;
import pl.user_task_list.UserTaskListProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@AllArgsConstructor
@Service
public class TaskListService {
   private TaskListRepository taskListRepository;
   private UserProvider userProvider;
   private UserTaskListProvider userTaskListProvider;
   private TaskListValidator taskListValidator;

   public Mono<TaskList> addTaskList(Principal principal, TaskList taskList) {
      ErrorMap errorMap = taskListValidator.validate(taskList);
      if (errorMap.hasErrors())
         throw new ValidationException(errorMap);
      return userProvider.getUser(principal).flatMap(u ->
         taskListRepository.save(taskList).map(t -> UserTaskList.builder().taskListId(t.getId()).userId(u.getId()).build())
            .flatMap(userTaskListProvider::save)
      ).flatMap(t -> taskListRepository.findById(t.getTaskListId()));
   }

   public Flux<TaskList> getTasks(Principal principal) {
      return userProvider.getUser(principal).flatMapMany(u -> taskListRepository.findAllByUserId(u.getId()));
   }

   public Mono<TaskList> addUserToTaskList(Principal principal, Long taskListId, Long userId) {
      return userProvider.getUser(principal).flatMap(u -> {
         return userTaskListProvider.findUserTaskList(taskListId, u.getId()).flatMap(ut -> {
            if (ut.getId() != null)
               return userTaskListProvider.save(UserTaskList.builder().taskListId(ut.getId()).userId(userId).build());
            else
               return Mono.error(new RuntimeException("There is no your property"));
         });
      }).then(taskListRepository.findById(taskListId));
   }
}
