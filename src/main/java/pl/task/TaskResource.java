package pl.task;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/task_list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
public class TaskResource {
   private TaskService taskService;

   @PutMapping("/{taskListId}/task/add")
   public Mono<Task> addTask(Principal principal, @PathVariable Long taskListId, @RequestBody Task task) {
      return taskService.addTask(principal, taskListId, task);
   }

   @GetMapping(value = "/{taskListId}/task", consumes = MediaType.ALL_VALUE)
   public Flux<Task> getTasksOfTaskList(Principal principal, @PathVariable Long taskListId) {
      return taskService.getTasks(principal, taskListId);
   }

   @PostMapping("/{taskListId}/task/edit/{taskId}")
   public Mono<Task> updateTask(Principal principal, @PathVariable Long taskListId, @PathVariable Long taskId, @RequestBody Task task) {
      return taskService.updateTask(principal, taskListId, taskId, task);
   }

   @GetMapping(value = "/all_tasks", consumes = MediaType.ALL_VALUE)
   public Flux<Task> getAllUseTasks(Principal principal) {
      return taskService.getAllTasks(principal);
   }

   @DeleteMapping(value = "/{taskListId}/task/remove/{taskId}", consumes = MediaType.ALL_VALUE)
   public void removeTask(Principal principal, @PathVariable Long taskListId, @PathVariable Long taskId) {
      taskService.removeTask(principal, taskListId, taskId);
   }
}
