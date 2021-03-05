package pl.task_list;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class TaskList {
   @Id
   private Long id;
   private String name;
   private String description;

   @Builder
   public TaskList(Long id, String name, String description) {
      this.id = id;
      this.name = name;
      this.description = description;
   }
}
