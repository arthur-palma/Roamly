package api.Roamly.DTO.Friendship;


import api.Roamly.Domain.Enum.InvitationStatus;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FriendshipDTO {

    private Long id;

    private UUID requester;

    private UUID receiver;

    private InvitationStatus status;
}
