package pl.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.ThereIsNoYourPropertyException;
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
               Mono.error(new ThereIsNoYourPropertyException())));
   }

   public Flux<Task> getTasks(Principal principal, Long taskListId) {
      return userProvider.getUser(principal).flatMapMany(u ->
         userTaskListProvider.findUserTaskList(taskListId, u.getId()).flatMap(ut ->
            (ut.getId() != null) ?
               taskRepository.findAll(taskListId) :
               Mono.error(new ThereIsNoYourPropertyException())));
   }

   public Mono<Task> updateTask(Principal principal, Long taskListId, Long taskId, Task task) {
      return userProvider.getUser(principal).flatMap(u ->
         userTaskListProvider.findUserTaskList(taskListId, u.getId()).flatMap(ut ->
            (ut.getId() != null) ?
               taskRepository.findById(taskId).flatMap(t ->
                  (t.getTaskListId().equals(taskListId)) ?
                     taskRepository.save(updateNoNullFields(t, task)) :
                     Mono.error(new RuntimeException("Bad task or task list"))) :
               Mono.error(new ThereIsNoYourPropertyException())));
   }

   private Task updateNoNullFields(Task oldTask, Task updatedTask) {
      oldTask.setName(updatedTask.getName() != null ? updatedTask.getName() : oldTask.getName());
      oldTask.setDescription(updatedTask.getDescription() != null ? updatedTask.getDescription() : oldTask.getDescription());
      oldTask.setIsImportant(updatedTask.getIsImportant() != null ? updatedTask.getIsImportant() : oldTask.getIsImportant());
      oldTask.setIsDone(updatedTask.getIsDone() != null ? updatedTask.getIsDone() : oldTask.getIsDone());
      oldTask.setIsUrgent(updatedTask.getIsUrgent() != null ? updatedTask.getIsUrgent() : oldTask.getIsUrgent());
      oldTask.setDateOfExecution(updatedTask.getDateOfExecution() != null ? updatedTask.getDateOfExecution() : oldTask.getDateOfExecution());
      oldTask.setTerm(updatedTask.getTerm() != null ? updatedTask.getTerm() : oldTask.getTerm());
      return oldTask;
   }
}
