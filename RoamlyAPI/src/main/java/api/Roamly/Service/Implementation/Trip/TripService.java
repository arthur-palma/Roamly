package api.Roamly.Service.Implementation.Trip;


import api.Roamly.DTO.Trip.CreateTripDTO;
import api.Roamly.DTO.Trip.EditTripDTO;
import api.Roamly.DTO.Trip.TripDTO;
import api.Roamly.Domain.Trip;
import api.Roamly.Domain.User;
import api.Roamly.Repository.TripRepository;
import api.Roamly.Service.Implementation.Auth.AuthService;
import api.Roamly.Service.Interface.Trip.ITripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static api.Roamly.Mapper.TripMapper.toDTO;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class TripService implements ITripService {

    private final TripRepository tripRepository;

    private final AuthService authService;

    @Override
    public ResponseEntity<TripDTO> createTrip(CreateTripDTO createTripDTO) {

        int MAX_TRIPS = 25;

        User user = authService.getAuthenticatedUser();

        if(user.getTrips().size() < MAX_TRIPS) {

            Trip trip = Trip.builder()
                    .name(createTripDTO.getName())
                    .destination(createTripDTO.getDestination())
                    .startDate(createTripDTO.getStartDate())
                    .endDate(createTripDTO.getEndDate())
                    .budget(createTripDTO.getBudget())
                    .owner(user)
                    .participants(new ArrayList<>())
                    .build();

            user.addTrip(trip);

            tripRepository.save(trip);

            return ResponseEntity.status(CREATED).body(toDTO(trip));
        }
        else
            throw new ResponseStatusException(CONFLICT,"You already reached the maximum trips a user can have");
    }

    @Override
    public ResponseEntity<TripDTO> editTrip(EditTripDTO editTripDTO) {
        User user = authService.getAuthenticatedUser();

        Trip trip = tripRepository.findById(editTripDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Trip Not Found"));

        if (!trip.getOwner().getId().equals(user.getId())) {
            throw new ResponseStatusException(UNAUTHORIZED, "You cannot edit a trip that is not yours");
        }

            trip.setName(editTripDTO.getName());
            trip.setDestination(editTripDTO.getDestination());
            trip.setStartDate(editTripDTO.getStartDate());
            trip.setEndDate(editTripDTO.getEndDate());
            trip.setBudget(editTripDTO.getBudget());

        tripRepository.save(trip);

        return ResponseEntity.status(OK).body(toDTO(trip));
    }

    @Override
    public ResponseEntity<Void> removeTrip() {
        return null;
    }
}
