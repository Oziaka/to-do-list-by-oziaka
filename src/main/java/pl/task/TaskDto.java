package pl.task;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class TaskDto {
   private Long id;
   private String name;
   private String description;
   private LocalDateTime term;
   private LocalDateTime dateOfExecution;
   private Boolean isDone;
   private Boolean isImportant;
   private Boolean isUrgent;

   @Builder
   public TaskDto(Long id, String name, String description, LocalDateTime term, LocalDateTime dateOfExecution, Boolean isDone, Boolean isImportant, Boolean isUrgent) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.term = term;
      this.dateOfExecution = dateOfExecution;
      this.isDone = isDone;
      this.isImportant = isImportant;
      this.isUrgent = isUrgent;
   }
}
