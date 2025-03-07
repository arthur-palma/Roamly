package api.Roamly.Mapper;

import api.Roamly.DTO.Friendship.FriendshipDTO;
import api.Roamly.Domain.Friendship;
import org.springframework.stereotype.Component;

@Component
public class FriendshipMapper {

    public static FriendshipDTO toDTO(Friendship friendship) {
        if (friendship == null) {
            return null;
        }

        return FriendshipDTO.builder()
                .id(friendship.getId())
                .requester(friendship.getRequester().getId())
                .receiver(friendship.getReceiver().getId())
                .status(friendship.getStatus())
                .build();
    }
}
