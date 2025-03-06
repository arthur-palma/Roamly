package api.Roamly.Service.Interface.User;

import api.Roamly.DTO.User.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IFriendService {

    public ResponseEntity<UserDTO> requestFriendship(UUID requestedId);

    public UserDTO addFriend(UUID requestId);

    public void unfriend(UUID id);

}
