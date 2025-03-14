package api.Roamly.Service.Interface.Trip;
import api.Roamly.DTO.Trip.CreateTripInviteDTO;
import api.Roamly.DTO.Trip.HandleTripInviteDTO;
import api.Roamly.DTO.Trip.TripInviteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITripParticipantService {

    ResponseEntity<TripInviteDTO> inviteParticipants(CreateTripInviteDTO createTripInviteDTO);

    ResponseEntity<TripInviteDTO> handleTripInvite(HandleTripInviteDTO handleTripInviteDTO);

    ResponseEntity<Void> removeParticipant(CreateTripInviteDTO createTripInviteDTO);

    ResponseEntity<List<TripInviteDTO>> getUserInvites();
}
