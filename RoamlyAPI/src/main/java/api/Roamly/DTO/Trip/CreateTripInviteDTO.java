package api.Roamly.DTO.Trip;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateTripInviteDTO {

    @NotBlank
    private UUID friendId;

    @NotBlank
    private UUID tripId;
}
