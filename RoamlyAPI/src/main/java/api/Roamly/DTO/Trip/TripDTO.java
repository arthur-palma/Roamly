package api.Roamly.DTO.Trip;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TripDTO {

    @NotNull
    private UUID id;

    @NotNull
    private UUID ownerId;

    @Nullable
    private String name;

    @Nullable
    private String destination;

}
