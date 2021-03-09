package pl.task;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/task_list", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskResource {
   private final TaskService taskService;

   @PutMapping("/{taskListId}/task/add")
   public Mono<TaskDto> addTask(Principal principal, @PathVariable Long taskListId, @RequestBody TaskDto taskDto) {
      return taskService.addTask(principal, taskListId, taskDto);
   }

   @GetMapping(path="/{taskListId}/task",consumes = MediaType.ALL_VALUE)
   public Flux<TaskDto> getTasksOfTaskList(Principal principal, @PathVariable Long taskListId) {
      return taskService.getTasks(principal, taskListId);
   }

   @PostMapping("/{taskListId}/task/edit/{taskId}")
   public Mono<TaskDto> updateTask(Principal principal, @PathVariable Long taskListId, @PathVariable Long taskId, @RequestBody TaskDto taskDto) {
      return taskService.updateTask(principal, taskListId, taskId, taskDto);
   }

   @GetMapping(path = "/all_tasks",consumes = MediaType.ALL_VALUE)
   public Flux<TaskDto> getAllUseTasks(Principal principal){
      return taskService.getAllTasks(principal);
   }

   @DeleteMapping("/{taskListId}/task/remove/{taskId}")
   public Mono<Void> removeTask(Principal principal, @PathVariable Long taskListId, @PathVariable Long taskId){
      return taskService.removeTask(principal,taskListId,taskId);
   }
}
