package api.Roamly.Service.Interface.Trip;

import api.Roamly.DTO.Trip.CreateTripDTO;
import api.Roamly.DTO.Trip.EditTripDTO;
import api.Roamly.DTO.Trip.TripDTO;
import org.springframework.http.ResponseEntity;

public interface ITripService {


    ResponseEntity<TripDTO> createTrip(CreateTripDTO createTripDTO);

    ResponseEntity<TripDTO> editTrip(EditTripDTO editTripDTO);

    ResponseEntity<Void> removeTrip();

}
