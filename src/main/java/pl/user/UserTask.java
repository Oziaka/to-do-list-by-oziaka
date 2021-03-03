package pl.user;

import org.springframework.data.annotation.Id;

public class UserTask {
   @Id
   Long userTaskId;
   Long userId;
   Long taskId;

}
