package api.Roamly.Service.Implementation.Friendship;

import api.Roamly.Domain.Friendship;
import api.Roamly.Repository.FriendshipRepository;
import api.Roamly.Service.Interface.Friendship.IFriendshipValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class FriendshipValidation implements IFriendshipValidation {

    private final FriendshipRepository friendshipRepository;

    @Override
    public Friendship validateRequestExist(Long id) {
        if(!friendshipRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Friendship Request not found");
        }
        else{
            return friendshipRepository.getReferenceById(id);
        }
    }
}
