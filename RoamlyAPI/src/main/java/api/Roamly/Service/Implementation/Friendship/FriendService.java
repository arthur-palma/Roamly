package api.Roamly.Service.Implementation.Friendship;

import api.Roamly.DTO.Friendship.FriendshipDTO;
import api.Roamly.Domain.Enum.FriendshipStatus;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static api.Roamly.Domain.Enum.FriendshipStatus.*;
import static api.Roamly.Mapper.FriendshipMapper.toDTO;
import static org.springframework.http.HttpStatus.*;


@RequiredArgsConstructor
@Service
public class FriendService implements IFriendService {


    private final FriendshipRepository friendshipRepository;
    private final IAuthService authService;
    private final IUserValidationService userValidationService;
    private final IFriendshipValidation friendshipValidation;


    @Override
    public ResponseEntity<FriendshipDTO> requestFriendship(UUID receiverId) {

        User requester = authService.getAuthenticatedUser();

        if(requester.getId().equals(receiverId)){
            throw new ResponseStatusException(BAD_REQUEST,"A request cannot be made to the same user.");
        }

        User receiver = userValidationService.validateUserExists(receiverId);

        Optional<Friendship> existingFriendship = friendshipRepository.findByRequesterAndReceiver(requester.getId(),receiver.getId());

        Friendship friendship;

        if (existingFriendship.isPresent()) {
            friendship = existingFriendship.get();
            if(friendship.getStatus() != REJECTED)
                throw new ResponseStatusException(BAD_REQUEST,"There is already a request or friendship between these users.");
            else {
                friendship.setStatus(PENDING);
                friendship.setReceiver(receiver);
                friendship.setRequester(requester);
            }
        }
        else {
            friendship = createFriendship(requester,receiver);
        }

        friendshipRepository.save(friendship);

        return ResponseEntity.status(CREATED).body(toDTO(friendship));
    }


    private Friendship createFriendship(User requester, User receiver) {
        return Friendship.builder()
                .requester(requester)
                .receiver(receiver)
                .status(PENDING)
                .build();
    }


    @Override
    public ResponseEntity<FriendshipDTO> handleRequest(FriendshipStatus option, Long requestId) {
        Friendship friendship = friendshipValidation.validateRequestExist(requestId);
        User user = authService.getAuthenticatedUser();

        if (friendship.getReceiver().equals(user)) {
            if (friendship.getStatus() == FriendshipStatus.PENDING) {
                if (option == FriendshipStatus.ACCEPTED || option == FriendshipStatus.REJECTED) {
                    friendship.setStatus(option);
                    friendshipRepository.save(friendship);
                } else {
                    throw new ResponseStatusException(BAD_REQUEST,"Invalid friendship status provided.");
                }
            } else {
                throw new ResponseStatusException(CONFLICT,"This request has already been accepted or rejected.");
            }
        } else {
            throw new ResponseStatusException(UNAUTHORIZED,"You cannot accept a request that is not addressed to you.");
        }

        return ResponseEntity.status(OK).body(toDTO(friendship));
    }

    @Override
    public ResponseEntity<Void> unfriend(UUID receiverId) {
        User user = authService.getAuthenticatedUser();
        Optional<Friendship> friendship = friendshipRepository.findByRequesterAndReceiver(user.getId(), receiverId);

        if(friendship.isPresent()){
            friendshipRepository.delete(friendship.get());
            return ResponseEntity.status(OK).build();
        }
        else {
            throw new ResponseStatusException(UNAUTHORIZED,"You cannot unfriend someone that is not your friend");
        }

    }
}
