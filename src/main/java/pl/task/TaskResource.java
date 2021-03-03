package pl.task;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("task_list/{taskListId}/task")
public class TaskResource {
   private TaskService taskService;

   @PutMapping(path = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_STREAM_JSON_VALUE)
   public Mono<Task> addTask(@RequestBody Task task, @PathVariable Long taskListId) {
      return taskService.save(task,taskListId);
   }

   @GetMapping(produces = APPLICATION_STREAM_JSON_VALUE)
   public Flux<Task> getTasks() {
      return taskService.getTasks();
   }
}
