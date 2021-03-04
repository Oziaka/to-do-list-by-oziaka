package pl.task;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping(value = "task_list/{taskListId}/task", produces = MediaType.APPLICATION_NDJSON_VALUE)
public class TaskResource {
   private TaskService taskService;

   @PutMapping("/add")
   public Mono<Task> addTask(Principal principal, @PathVariable Long taskListId, @RequestBody Task task) {
      return taskService.addTask(principal, taskListId, task);
   }

   @GetMapping
   public Flux<Task> getTasks(Principal principal, @PathVariable Long taskListId) {
      return taskService.getTasks(principal, taskListId);
   }

   @PostMapping("/edit/{taskId}")
   public Mono<Task> updateTask(Principal principal, @PathVariable Long taskListId, @PathVariable Long taskId, @RequestBody Task task) {
      return taskService.updateTask(principal,taskListId,taskId,task);
   }
}
