package api.Roamly.Service.Interface.Friendship;

import api.Roamly.DTO.Friendship.FriendshipDTO;
import api.Roamly.Domain.Enum.FriendshipStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IFriendService {

     ResponseEntity<FriendshipDTO> requestFriendship(UUID requestedId);

     ResponseEntity<FriendshipDTO> handleRequest(FriendshipStatus option, Long requestId);

     void unfriend(UUID id);

}
