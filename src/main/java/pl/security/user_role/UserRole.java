package pl.security.user_role;

import org.springframework.data.annotation.Id;

public class UserRole {
   @Id
   private Long id;

   private String roleName;

   private String roleDescription;
}
