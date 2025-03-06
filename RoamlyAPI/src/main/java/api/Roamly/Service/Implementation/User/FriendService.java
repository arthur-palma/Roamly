package api.Roamly.Service.Implementation.User;

import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Domain.Enum.FriendshipStatus;
import api.Roamly.Domain.Friendship;
import api.Roamly.Domain.User;
import api.Roamly.Repository.FriendshipRepository;
import api.Roamly.Repository.UserRepository;
import api.Roamly.Service.Interface.Auth.IAuthService;
import api.Roamly.Service.Interface.User.IFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static api.Roamly.Mapper.UserMapper.toDTO;


@RequiredArgsConstructor
@Service
public class FriendService implements IFriendService {


    private final FriendshipRepository friendshipRepository;
    private final IAuthService authService;

    private final UserRepository userRepository;


    @Override
    public ResponseEntity<UserDTO> requestFriendship(UUID receiverId) {

        User requester = authService.getAuthenticatedUser();

        User receiver = userRepository.getReferenceById(receiverId);

        Optional<Friendship> existingFriendship = friendshipRepository.findByRequesterAndReceiver(requester.getId(),receiver.getId());

        if (existingFriendship.isPresent()) {
            throw new RuntimeException("Já existe uma solicitação ou amizade entre esses usuários.");
        }

        Friendship friendship = Friendship.builder()
                .requester(requester)
                .receiver(receiver)
                .status(FriendshipStatus.PENDING)
                .build();

        friendshipRepository.save(friendship);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(receiver));

    }

    @Override
    public UserDTO addFriend(UUID id) {
        return null;
    }

    @Override
    public void unfriend(UUID id) {

    }
}
