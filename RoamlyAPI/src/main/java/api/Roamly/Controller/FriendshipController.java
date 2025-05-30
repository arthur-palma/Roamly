package api.Roamly.Controller;


import api.Roamly.DTO.Friendship.FriendshipDTO;
import api.Roamly.Domain.Enum.InvitationStatus;
import api.Roamly.Service.Interface.Friendship.IFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/friendship")
@RequiredArgsConstructor
public class FriendshipController {


    private final IFriendService friendService;


    @PostMapping("/{friendId}")
    public ResponseEntity<FriendshipDTO> requestFriendship(@PathVariable UUID friendId){
        return friendService.requestFriendship(friendId);
    }

    @PutMapping("/handle-request/{status}/{requestId}")
    public ResponseEntity<FriendshipDTO> handleRequest(@PathVariable Long requestId, @PathVariable InvitationStatus status) {
        return friendService.handleRequest(status, requestId);
    }

    @DeleteMapping("/{friendId}")
    public ResponseEntity<Void> unfriend(@PathVariable UUID friendId){
        return friendService.unfriend(friendId);
    }


}
