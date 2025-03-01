package api.Roamly.Service.Interface.User;

import api.Roamly.DTO.User.CreateUserDTO;
import api.Roamly.DTO.User.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    ResponseEntity<UserDTO> createUser(CreateUserDTO createUserDTO);

    ResponseEntity<UserDTO> getUserById(UUID id);

    ResponseEntity<List<UserDTO>> getAllUsers();

}
