package api.Roamly.Service.Implementation.Trip;

import api.Roamly.DTO.Trip.CreateTripInviteDTO;
import api.Roamly.DTO.Trip.TripInviteDTO;
import api.Roamly.Domain.Trip;
import api.Roamly.Domain.TripInvite;
import api.Roamly.Domain.User;
import api.Roamly.Repository.TripInviteRepository;
import api.Roamly.Repository.TripRepository;
import api.Roamly.Service.Interface.Auth.IAuthService;
import api.Roamly.Service.Interface.Trip.ITripParticipantService;
import api.Roamly.Service.Interface.User.IUserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

import static api.Roamly.Domain.Enum.InvitationStatus.PENDING;
import static api.Roamly.Mapper.TripInviteMapper.toDto;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class TripParticipantService implements ITripParticipantService {

    private final IAuthService authService;
    private final TripRepository tripRepository;

    private final IUserValidationService userValidationService;

    private final TripInviteRepository tripInviteRepository;


    @Override
    public ResponseEntity<TripInviteDTO> inviteParticipants(CreateTripInviteDTO createTripInviteDTO) {

        User user = authService.getAuthenticatedUser();
        User friend = userValidationService.validateUserExists(createTripInviteDTO.getFriendId());

        Trip trip = tripRepository.findById(createTripInviteDTO.getTripId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Trip Not Found"));

        if(!trip.getOwner().equals(user)){
            throw new ResponseStatusException(UNAUTHORIZED,"You cannot invite friends to a trip that is not yours");
        }

        if(!user.getFriends().contains(friend)){
            throw new ResponseStatusException(UNAUTHORIZED,"You cannot invite someone that is not your friend");
        }

        Optional<TripInvite> optionalTripInvite = tripInviteRepository.findByTripIdAndInvitedUserId(createTripInviteDTO.getTripId(), createTripInviteDTO.getFriendId());

        if(optionalTripInvite.isPresent()){
            throw new ResponseStatusException(BAD_REQUEST,"User already invited to this trip");
        }

        TripInvite tripInvite = TripInvite.builder()
                .invitedUser(friend)
                .trip(trip)
                .status(PENDING)
                .build();

        tripInviteRepository.save(tripInvite);

        return ResponseEntity.status(CREATED).body(toDto(tripInvite));
    }

    @Override
    public ResponseEntity<Void> removeParticipant() {
        return null;
    }
}
