package api.Roamly.Service.Interface.Trip;

import org.springframework.http.ResponseEntity;

public interface ITripParticipantService {

    ResponseEntity<Void> inviteParticipants();

    ResponseEntity<Void> removeParticipant();

}
