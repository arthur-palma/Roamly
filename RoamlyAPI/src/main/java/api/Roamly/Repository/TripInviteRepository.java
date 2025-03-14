package api.Roamly.Repository;

import api.Roamly.Domain.TripInvite;
import api.Roamly.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TripInviteRepository extends JpaRepository<TripInvite, UUID> {

    @Query("SELECT t FROM TripInvite t WHERE (t.trip.id = :tripId AND t.invitedUser.id = :friendId)")
    Optional<TripInvite> findByTripIdAndInvitedUserId(UUID tripId, UUID friendId);

    @Query("SELECT t FROM TripInvite t WHERE (t.invitedUser = :user)")
    List<TripInvite> findByInvitedUser(User user);
}
