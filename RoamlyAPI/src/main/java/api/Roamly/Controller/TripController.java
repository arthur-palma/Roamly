package api.Roamly.Controller;

import api.Roamly.DTO.Trip.*;
import api.Roamly.Service.Interface.Trip.ITripParticipantService;
import api.Roamly.Service.Interface.Trip.ITripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final ITripService tripService;

    private final ITripParticipantService tripParticipantService;

    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@RequestBody @Valid CreateTripDTO createTripDTO){
        return tripService.createTrip(createTripDTO);
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getUsersTrip(){
        return tripService.getUserTrip();
    }

    @PutMapping
    public ResponseEntity<TripDTO> editTrip(@RequestBody @Valid EditTripDTO editTripDTO){
        return tripService.editTrip(editTripDTO);
    }

    @DeleteMapping("{tripId}")
    public ResponseEntity<Void> removeTrip(@PathVariable UUID tripId){
        return tripService.removeTrip(tripId);
    }

    @PostMapping("/invite")
    public ResponseEntity<TripInviteDTO> createTrip(@RequestBody CreateTripInviteDTO createTripInviteDTO){
        return tripParticipantService.inviteParticipants(createTripInviteDTO);
    }

}
