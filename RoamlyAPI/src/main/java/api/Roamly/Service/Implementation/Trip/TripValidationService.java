package api.Roamly.Service.Implementation.Trip;

import api.Roamly.Domain.Enum.InvitationStatus;
import api.Roamly.Domain.TripInvite;
import api.Roamly.Domain.User;
import api.Roamly.Repository.TripInviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static api.Roamly.Domain.Enum.InvitationStatus.PENDING;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class TripValidationService {

    private final TripInviteRepository tripInviteRepository;


    public TripInvite validateTripInviteExists(UUID tripInviteId){
        if(!tripInviteRepository.existsById(tripInviteId)){
            throw new ResponseStatusException(NOT_FOUND,"Invite Not Found");
        }
        else{
            return tripInviteRepository.getReferenceById(tripInviteId);
        }
    }

    public void validateUserCanHandleInvite(User user, TripInvite tripInvite) {
        if(!tripInvite.getInvitedUser().equals(user)){
            throw new ResponseStatusException(UNAUTHORIZED,"User cannot handle Invite");
        }
    }

    public void validateStatusOption(InvitationStatus currentStatus) {
        if(currentStatus != PENDING)
            throw new ResponseStatusException(BAD_REQUEST,"This invite has already been accepted or rejected.");
    }
}
