package api.Roamly.Service.Implementation.User;


import api.Roamly.DTO.User.CreateUserDTO;
import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Domain.User;
import api.Roamly.Mapper.UserMapper;
import api.Roamly.Repository.UserRepository;
import api.Roamly.Service.Interface.Security.IPasswordEncryptionService;
import api.Roamly.Service.Interface.User.IUserService;
import api.Roamly.Service.Interface.User.IUserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static api.Roamly.Mapper.UserMapper.toDTO;
import static api.Roamly.Mapper.UserMapper.toEntity;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IUserValidationService userValidationService;
    private final IPasswordEncryptionService passwordEncryptionService;

    @Override
    public ResponseEntity<UserDTO> createUser(CreateUserDTO createUserDTO) {

        userValidationService.validateEmail(createUserDTO);

        User user = toEntity(createUserDTO, passwordEncryptionService.encrypt(createUserDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(savedUser));

    }

    @Override
    public ResponseEntity<UserDTO> getUserById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));

        return ResponseEntity.ok(toDTO(user));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }
}
