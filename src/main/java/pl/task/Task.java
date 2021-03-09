package pl.task;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Task {
   @Id
   private Long id;
   private String name;
   private String description;
   private LocalDateTime term;
   private LocalDateTime dateOfExecution;
   private Boolean isDone;
   private Boolean isImportant;
   private Boolean isUrgent;
   private Long taskListId;

   @Builder
   public Task(Long id, String name, String description, LocalDateTime term, LocalDateTime dateOfExecution, Boolean isDone, Boolean isImportant, Boolean isUrgent, Long taskListId) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.term = term;
      this.dateOfExecution = dateOfExecution;
      this.isDone = isDone;
      this.isImportant = isImportant;
      this.isUrgent = isUrgent;
      this.taskListId = taskListId;
   }
}
