package api.Roamly.Mapper;

import api.Roamly.DTO.Trip.TripDTO;
import api.Roamly.DTO.Trip.TripTrendingDTO;
import api.Roamly.Domain.Trip;

import java.time.temporal.ChronoUnit;

public class TripTrendingMapper {

    public static TripTrendingDTO toDTO(Trip trip){
        if (trip == null) {
            return null;
        }

        return TripTrendingDTO.builder()
                .id(trip.getId())
                .destination(trip.getDestination())
                .days(ChronoUnit.DAYS.between(trip.getStartDate(), trip.getEndDate()))
                .budget(trip.getBudget())
                .build();
    }


}
