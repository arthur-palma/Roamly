package api.Roamly.Controller;


import api.Roamly.DTO.Trip.CreateTripDTO;
import api.Roamly.Service.Interface.Trip.ITripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final ITripService tripService;


    @PostMapping
    public ResponseEntity<Void> createTrip(@RequestBody @Valid CreateTripDTO createTripDTO){
        return tripService.createTrip(createTripDTO);
    }


}
