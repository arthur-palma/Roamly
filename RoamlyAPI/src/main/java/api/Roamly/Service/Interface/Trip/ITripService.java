package api.Roamly.Service.Interface.Trip;

import api.Roamly.DTO.Trip.CreateTripDTO;
import api.Roamly.DTO.Trip.EditTripDTO;
import api.Roamly.DTO.Trip.TripDTO;
import api.Roamly.DTO.Trip.TripTrendingDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ITripService {


    ResponseEntity<TripDTO> createTrip(CreateTripDTO createTripDTO);

    ResponseEntity<TripDTO> editTrip(EditTripDTO editTripDTO);

    ResponseEntity<Void> removeTrip(UUID id);

    ResponseEntity<List<TripDTO>> getUserTrip();

    ResponseEntity<List<TripTrendingDTO>> getTrendingTrips();

}
