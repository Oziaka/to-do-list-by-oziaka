package pl.task_list;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/task_list", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskListResource {
   private TaskListService taskListService;

   @PutMapping(value = "/add")
   public Mono<TaskList> addTaskList(Principal principal, @RequestBody TaskList taskList) {
      return taskListService.addTaskList(principal, taskList);
   }

   @GetMapping
   public Flux<TaskList> getListTasks(Principal principal) {
      return taskListService.getTasks(principal);
   }

   @PostMapping("{taskListId}/add_user/{userId}")
   public Mono<TaskList> addUserToTaskList(Principal principal, @PathVariable Long taskListId, @PathVariable Long userId) {
      return taskListService.addUserToTaskList(principal, taskListId, userId);
   }

}
