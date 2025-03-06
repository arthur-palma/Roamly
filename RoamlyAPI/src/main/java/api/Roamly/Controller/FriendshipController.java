package api.Roamly.Controller;


import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Service.Interface.User.IFriendService;
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
    public ResponseEntity<UserDTO> requestFriendship(@PathVariable UUID id){
        return friendService.requestFriendship(id);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<UserDTO> acceptRequest(@PathVariable Long id){
        return friendService.acceptRequest(id);
    }



}
