package pl.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.ErrorMap;
import pl.exception.ThereIsNoYourPropertyException;
import pl.exception.ValidationException;
import pl.user.UserProvider;
import pl.user_task_list.UserTaskListProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static java.lang.Boolean.FALSE;

@Service
@AllArgsConstructor
public class TaskService {
   private final TaskRepository taskRepository;
   private final UserProvider userProvider;
   private final UserTaskListProvider userTaskListProvider;
   private final TaskValidator taskValidator;

   public Mono<TaskDto> addTask(Principal principal, Long taskListId, TaskDto taskDto) {
      ErrorMap errorMap = taskValidator.validate(taskDto);
      if (errorMap.hasErrors())
         throw new ValidationException(errorMap);
      Task task = TaskMapper.toEntity(taskDto);
      task.setTaskListId(taskListId);
      task.setIsDone(FALSE);
      return userProvider.getUser(principal).flatMap(u ->
         userTaskListProvider.findUserTaskList(taskListId, u.getId()).flatMap(ut -> {
            if (ut.getId() != null)
               return taskRepository.save(task).map(TaskMapper::toDto);
            else
               return Mono.error(new ThereIsNoYourPropertyException());
         }));
   }

   public Flux<TaskDto> getTasks(Principal principal, Long taskListId) {
      return userProvider.getUser(principal).flatMapMany(u -> userTaskListProvider.findUserTaskList(taskListId, u.getId()).flatMapMany(ut -> {
         if (ut.getId() != null)
            return taskRepository.findAll(taskListId).map(TaskMapper::toDto);
         else
            return Mono.error(new ThereIsNoYourPropertyException());
      }));
   }

   public Mono<TaskDto> updateTask(Principal principal, Long taskListId, Long taskId, TaskDto taskDto) {
      Task task = TaskMapper.toEntity(taskDto);
      task.setTaskListId(taskListId);
      return userProvider.getUser(principal).flatMap(u ->
         userTaskListProvider.findUserTaskList(taskListId, u.getId()).flatMap(ut ->
            (ut.getId() != null) ?
               taskRepository.findById(taskId).flatMap(t ->
                  (t.getTaskListId().equals(taskListId)) ?
                     taskRepository.save(updateNoNullFields(t, task)).map(TaskMapper::toDto) :
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

   public Flux<TaskDto> getAllTasks(Principal principal) {
      return userProvider.getUser(principal).flatMapMany(u -> taskRepository.findAllUserTasks(u.getId())).map(TaskMapper::toDto);
   }

   public Mono<Void> removeTask(Principal principal, Long taskListId, Long taskId) {
      return userProvider.getUser(principal).flatMap(u ->
         taskRepository.findByUserIdAndTaskListIdAndTaskId(u.getId(), taskListId, taskId))
         .flatMap(t ->
            (t.getId() != null) ? taskRepository.delete(t).then(Mono.empty()) : Mono.error(new ThereIsNoYourPropertyException())
         );
   }
}
