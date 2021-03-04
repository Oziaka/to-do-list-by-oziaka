package pl.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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

}
