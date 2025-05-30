package api.Roamly.Service.Interface.Auth;

import api.Roamly.DTO.Auth.LoginDTO;
import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Domain.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IAuthService {


    ResponseEntity<UserDTO> login(LoginDTO loginDTO, HttpServletResponse response);

    User getAuthenticatedUser();


}
