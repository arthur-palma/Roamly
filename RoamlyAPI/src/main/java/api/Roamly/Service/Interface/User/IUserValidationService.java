package api.Roamly.Service.Interface.User;

import api.Roamly.DTO.User.CreateUserDTO;

public interface IUserValidationService {


    void validateEmail(CreateUserDTO createUserDTO);

}
