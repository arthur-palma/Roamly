package api.Roamly.Service.Interface.Trip;

import api.Roamly.DTO.Trip.CreateTripDTO;
import org.springframework.http.ResponseEntity;

public interface ITripService {


    ResponseEntity<Void> createTrip(CreateTripDTO createTripDTO);

    ResponseEntity<Void> editTrip();

    ResponseEntity<Void> removeTrip();

}
