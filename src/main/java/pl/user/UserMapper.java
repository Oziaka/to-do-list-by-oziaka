package pl.user;

public class UserMapper {
   public static User toEntity(UserDto userDto) {
      return User.builder()
         .id(userDto.getId())
         .email(userDto.getEmail())
         .password(userDto.getPassword())
         .name(userDto.getName())
         .build();
   }

   public static UserDto toDto(User user) {
      return UserDto.builder()
         .id(user.getId())
         .email(user.getEmail())
         .name(user.getName())
         .build();
   }
}
