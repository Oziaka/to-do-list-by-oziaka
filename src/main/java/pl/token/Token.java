package pl.token;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Token {
   @Id
   private Long id;
   private String value;
   private Long userId;

   @Builder
   public Token(Long id, String value, Long userId) {
      this.id = id;
      this.value = value;
      this.userId = userId;
   }
}
