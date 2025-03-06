package api.Roamly.Service.Implementation.Friendship;

import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Domain.Friendship;
import api.Roamly.Domain.User;
import api.Roamly.Repository.FriendshipRepository;
import api.Roamly.Service.Interface.Auth.IAuthService;
import api.Roamly.Service.Interface.Friendship.IFriendService;
import api.Roamly.Service.Interface.Friendship.IFriendshipValidation;
import api.Roamly.Service.Interface.User.IUserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static api.Roamly.Domain.Enum.FriendshipStatus.ACCEPTED;
import static api.Roamly.Domain.Enum.FriendshipStatus.PENDING;
import static api.Roamly.Mapper.UserMapper.toDTO;


@RequiredArgsConstructor
@Service
public class FriendService implements IFriendService {


    private final FriendshipRepository friendshipRepository;
    private final IAuthService authService;
    private final IUserValidationService userValidationService;

    private final IFriendshipValidation friendshipValidation;


    @Override
    public ResponseEntity<UserDTO> requestFriendship(UUID receiverId) {

        User requester = authService.getAuthenticatedUser();

        User receiver = userValidationService.validateUserExists(receiverId);

        Optional<Friendship> existingFriendship = friendshipRepository.findByRequesterAndReceiver(requester.getId(),receiver.getId());

        if (existingFriendship.isPresent()) {
            throw new RuntimeException("There is already a request or friendship between these users.");
        }

        Friendship friendship = Friendship.builder()
                .requester(requester)
                .receiver(receiver)
                .status(PENDING)
                .build();

        friendshipRepository.save(friendship);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(receiver));

    }

    @Override
    public ResponseEntity<UserDTO> acceptRequest(Long requestId) {
        Friendship friendship = friendshipValidation.validateRequestExist(requestId);
        User user = authService.getAuthenticatedUser();

        if (friendship.getReceiver().equals(user)) {
            if (friendship.getStatus() == PENDING) {
                friendship.setStatus(ACCEPTED);
                friendshipRepository.save(friendship);
                return ResponseEntity.status(HttpStatus.OK).body(toDTO(user));
            } else {
                throw new IllegalStateException("This request has already been accepted or rejected.");
            }
        } else {
            throw new IllegalStateException("You cannot accept a request that is not addressed to you.");
        }
    }

    @Override
    public void declineRequest(UUID requestId) {

    }

    @Override
    public void unfriend(UUID id) {

    }
}
