package pl.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
   @Id
   private Long id;
   private String email;
   private String name;
   private String password;
   private String userRole;
   private Boolean isActive;

   @Builder
   public User(Long id, String email, String name, String password) {
      this.id = id;
      this.email = email;
      this.name = name;
      this.password = password;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      if (userRole == null)
         return Collections.emptyList();
      else
         return Collections.singletonList(new SimpleGrantedAuthority(userRole));
   }

   @Override
   public String getUsername() {
      return getEmail();
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return isActive;
   }
}
