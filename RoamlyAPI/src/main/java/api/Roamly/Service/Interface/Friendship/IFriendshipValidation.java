package api.Roamly.Service.Interface.Friendship;

import api.Roamly.Domain.Friendship;

public interface IFriendshipValidation {

    Friendship validateRequestExist(Long id);

}
