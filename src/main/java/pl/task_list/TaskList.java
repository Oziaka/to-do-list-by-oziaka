package pl.task_list;

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
}
