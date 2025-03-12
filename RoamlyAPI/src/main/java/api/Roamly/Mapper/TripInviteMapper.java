package api.Roamly.Mapper;

import api.Roamly.DTO.Trip.TripInviteDTO;
import api.Roamly.Domain.TripInvite;

public class TripInviteMapper {


    public static TripInviteDTO toDto(TripInvite tripInvite){
        return TripInviteDTO.builder()
                .id(tripInvite.getId())
                .invitedUser(tripInvite.getInvitedUser().getId())
                .status(tripInvite.getStatus())
                .trip(tripInvite.getTrip().getId())
                .build();
    }



}
