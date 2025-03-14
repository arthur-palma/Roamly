package api.Roamly.DTO.Trip;


import api.Roamly.Domain.Enum.InvitationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HandleTripInviteDTO {

    UUID tripInviteId;

    InvitationStatus status;

}
