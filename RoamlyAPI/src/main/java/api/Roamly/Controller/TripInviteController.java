package api.Roamly.Controller;

import api.Roamly.DTO.Trip.CreateTripInviteDTO;
import api.Roamly.DTO.Trip.HandleTripInviteDTO;
import api.Roamly.DTO.Trip.TripInviteDTO;
import api.Roamly.Service.Interface.Trip.ITripParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip/invite")
@RequiredArgsConstructor
public class TripInviteController {

    private final ITripParticipantService tripParticipantService;

    @PostMapping
    public ResponseEntity<TripInviteDTO> createTripInvite(@RequestBody CreateTripInviteDTO createTripInviteDTO) {
        return tripParticipantService.inviteParticipants(createTripInviteDTO);
    }

    @GetMapping
    public ResponseEntity<List<TripInviteDTO>> getUserTripInvites() {
        return tripParticipantService.getUserInvites();
    }

    @PutMapping
    public ResponseEntity<TripInviteDTO> handleTripInvite(@RequestBody HandleTripInviteDTO handleTripInviteDTO) {
        return tripParticipantService.handleTripInvite(handleTripInviteDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeParticipant(@RequestBody CreateTripInviteDTO createTripInviteDTO) {
        return tripParticipantService.removeParticipant(createTripInviteDTO);
    }
}