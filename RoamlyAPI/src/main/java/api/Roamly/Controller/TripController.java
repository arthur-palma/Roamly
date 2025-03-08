package api.Roamly.Controller;


import api.Roamly.DTO.Trip.CreateTripDTO;
import api.Roamly.DTO.Trip.EditTripDTO;
import api.Roamly.DTO.Trip.TripDTO;
import api.Roamly.Service.Interface.Trip.ITripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final ITripService tripService;


    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@RequestBody @Valid CreateTripDTO createTripDTO){
        return tripService.createTrip(createTripDTO);
    }

    @PutMapping
    public ResponseEntity<TripDTO> editTrip(@RequestBody @Valid EditTripDTO editTripDTO){
        return tripService.editTrip(editTripDTO);
    }


}
