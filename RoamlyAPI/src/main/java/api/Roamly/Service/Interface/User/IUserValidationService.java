package api.Roamly.Service.Interface.User;

import api.Roamly.DTO.User.CreateUserDTO;
import api.Roamly.Domain.User;

import java.util.UUID;

public interface IUserValidationService {


    void validateEmail(CreateUserDTO createUserDTO);

    User validateUserExists(UUID id);

}
