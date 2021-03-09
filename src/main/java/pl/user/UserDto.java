package pl.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class UserDto {
   private Long id;
   private String email;
   private String password;
   private String name;

   @Builder
   public UserDto(Long id, String email, String password, String name) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.name = name;
   }
}
