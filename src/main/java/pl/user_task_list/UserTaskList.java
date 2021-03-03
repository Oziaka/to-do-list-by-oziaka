package pl.user_task_list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class UserTaskList {
   @Id
   private Long id;
   private Long userId;
   private Long taskListId;
}
