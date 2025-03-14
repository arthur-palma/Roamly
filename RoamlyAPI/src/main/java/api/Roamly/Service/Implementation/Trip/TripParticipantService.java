package api.Roamly.Service.Implementation.Trip;

import api.Roamly.DTO.Trip.CreateTripInviteDTO;
import api.Roamly.DTO.Trip.HandleTripInviteDTO;
import api.Roamly.DTO.Trip.TripDTO;
import api.Roamly.DTO.Trip.TripInviteDTO;
import api.Roamly.Domain.Trip;
import api.Roamly.Domain.TripInvite;
import api.Roamly.Domain.User;
import api.Roamly.Mapper.TripInviteMapper;
import api.Roamly.Mapper.TripMapper;
import api.Roamly.Repository.TripInviteRepository;
import api.Roamly.Repository.TripRepository;
import api.Roamly.Service.Interface.Auth.IAuthService;
import api.Roamly.Service.Interface.Trip.ITripParticipantService;
import api.Roamly.Service.Interface.User.IUserValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final TripValidationService tripValidationService;


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
    public ResponseEntity<TripInviteDTO> handleTripInvite(HandleTripInviteDTO handleTripInviteDTO) {
        User user = authService.getAuthenticatedUser();
        TripInvite tripInvite = tripValidationService.validateTripInviteExists(handleTripInviteDTO.getTripInviteId());
        Trip trip = tripInvite.getTrip();

        tripValidationService.validateUserCanHandleInvite(user,tripInvite);
        tripValidationService.validateStatusOption(tripInvite.getStatus());

        switch (handleTripInviteDTO.getStatus()) {
            case ACCEPTED -> {
                user.addTrip(trip);
                tripInvite.setStatus(handleTripInviteDTO.getStatus());
                tripInviteRepository.save(tripInvite);
                tripRepository.save(trip);
            }
            case REJECTED -> {
                tripInvite.setStatus(handleTripInviteDTO.getStatus());
                tripInviteRepository.save(tripInvite);
            }
            default -> throw new ResponseStatusException(BAD_REQUEST, "Invalid status provided.");
        }

        return ResponseEntity.status(OK).body(toDto(tripInvite));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> removeParticipant(CreateTripInviteDTO tripInviteDTO) {
        User user = authService.getAuthenticatedUser();
        User friend = userValidationService.validateUserExists(tripInviteDTO.getFriendId());

        Trip trip = tripRepository.findById(tripInviteDTO.getTripId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Trip not found"));

        if (!trip.getOwner().equals(user)) {
            throw new ResponseStatusException(UNAUTHORIZED, "You cannot remove users from a trip that is not yours");
        }

        if (!friend.getTrips().contains(trip)) {
            throw new ResponseStatusException(NOT_FOUND, "User is not a participant of this trip");
        }

        friend.leaveTrip(trip);

        tripInviteRepository.findByTripIdAndInvitedUserId(tripInviteDTO.getTripId(), tripInviteDTO.getFriendId())
                .ifPresent(tripInviteRepository::delete);

        tripRepository.save(trip);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<TripInviteDTO>> getUserInvites() {
        User user = authService.getAuthenticatedUser();
        List<TripInvite> tripInvites = tripInviteRepository.findByInvitedUser(user);

        if (tripInvites.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<TripInviteDTO> tripDTOs = tripInvites.stream()
                .map(TripInviteMapper::toDto)
                .toList();

        return ResponseEntity.ok(tripDTOs);

    }
}
