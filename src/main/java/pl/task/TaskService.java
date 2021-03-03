package pl.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

@Service
@AllArgsConstructor
public class TaskService {
   private TaskRepository taskRepository;

   public Mono<Task> save(Task task, Long taskListId) {
      return taskRepository.save(task.getName(), task.getDescription());
   }

   public Flux<Task> getTasks() {
      return taskRepository.getAll();
   }
}
