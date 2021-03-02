package pl.user;

import org.springframework.data.annotation.Id;

public class User {

   @Id
   private Long id;

   private String email;

   private String password;
}
