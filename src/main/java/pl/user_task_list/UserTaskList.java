package pl.user_task_list;

import lombok.Builder;
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

   @Builder
   public UserTaskList(Long id, Long userId, Long taskListId) {
      this.id = id;
      this.userId = userId;
      this.taskListId = taskListId;
   }
}
