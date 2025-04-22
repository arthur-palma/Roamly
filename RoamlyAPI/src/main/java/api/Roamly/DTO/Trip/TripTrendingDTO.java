package api.Roamly.DTO.Trip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TripTrendingDTO {

    UUID id;

    String destination;

    String image_url;

    Long days;

    BigDecimal budget;
}
