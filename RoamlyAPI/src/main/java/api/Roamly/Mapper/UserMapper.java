package api.Roamly.Mapper;

import api.Roamly.DTO.User.CreateUserDTO;
import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .profilePicture(userDTO.getProfilePicture())
                .build();
    }

    public static User toEntity(CreateUserDTO userDTO, String password) {
        if (userDTO == null) {
            return null;
        }

        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .profilePicture(userDTO.getProfilePicture())
                .password(password)
                .build();
    }
}
