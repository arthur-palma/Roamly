package api.Roamly.DTO.Trip;

import api.Roamly.Domain.Enum.InvitationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripInviteDTO {

    private UUID id;

    private UUID trip;

    private UUID invitedUser;

    private InvitationStatus status;



}
