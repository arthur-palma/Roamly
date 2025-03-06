package api.Roamly.Service.Interface.Friendship;

import api.Roamly.DTO.User.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IFriendService {

     ResponseEntity<UserDTO> requestFriendship(UUID requestedId);

     ResponseEntity<UserDTO> acceptRequest(Long requestId);

     void declineRequest(UUID requestId);

     void unfriend(UUID id);

}
