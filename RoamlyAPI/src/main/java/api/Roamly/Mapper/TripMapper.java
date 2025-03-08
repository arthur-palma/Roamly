package api.Roamly.Mapper;


import api.Roamly.DTO.Friendship.FriendshipDTO;
import api.Roamly.DTO.Trip.TripDTO;
import api.Roamly.Domain.Friendship;
import api.Roamly.Domain.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {


    public static TripDTO toDTO(Trip trip) {
        if (trip == null) {
            return null;
        }

        return TripDTO.builder()
                .id(trip.getId())
                .name(trip.getName())
                .destination(trip.getDestination())
                .ownerId(trip.getOwner().getId())
                .build();
    }




}
