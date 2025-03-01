package api.Roamly.Service.Implementation.User;

import api.Roamly.DTO.User.CreateUserDTO;
import api.Roamly.Repository.UserRepository;
import api.Roamly.Service.Interface.User.IUserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserValidationService implements IUserValidationService {

    private final UserRepository userRepository;

    public void validateEmail(CreateUserDTO userDTO){
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already in use");
        }
    }
}