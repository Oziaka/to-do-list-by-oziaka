package pl.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
   private Long id;
   private String email;
   private String name;
   private String password;

   @Builder
   public UserDto(Long id, String email, String name, String password) {
      this.id = id;
      this.email = email;
      this.name = name;
      this.password = password;
   }
}
