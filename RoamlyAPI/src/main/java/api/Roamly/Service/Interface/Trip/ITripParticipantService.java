package api.Roamly.Service.Interface.Trip;
import api.Roamly.DTO.Trip.CreateTripInviteDTO;
import api.Roamly.DTO.Trip.TripInviteDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ITripParticipantService {

    ResponseEntity<TripInviteDTO> inviteParticipants(CreateTripInviteDTO createTripInviteDTO);

    ResponseEntity<Void> removeParticipant();

}
