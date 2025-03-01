package api.Roamly.Controller;


import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Service.Interface.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
         return userService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

}
