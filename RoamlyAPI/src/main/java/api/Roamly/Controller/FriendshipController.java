package api.Roamly.Controller;


import api.Roamly.DTO.Friendship.FriendshipDTO;
import api.Roamly.Domain.Enum.FriendshipStatus;
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


    @PostMapping("/{id}")
    public ResponseEntity<FriendshipDTO> requestFriendship(@PathVariable UUID id){
        return friendService.requestFriendship(id);
    }

    @PutMapping("/handle-request/{requestId}/{status}")
    public ResponseEntity<FriendshipDTO> handleRequest(@PathVariable Long requestId, @PathVariable FriendshipStatus status) {
        return friendService.handleRequest(status, requestId);
    }



}
