package api.Roamly.Controller;

import api.Roamly.DTO.User.CreateUserDTO;
import api.Roamly.DTO.Auth.LoginDTO;
import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Service.Interface.Auth.IAuthService;
import api.Roamly.Service.Interface.User.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    private final IAuthService authService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO loginDTO, HttpServletResponse response){
        return authService.login(loginDTO,response);
    }

}
