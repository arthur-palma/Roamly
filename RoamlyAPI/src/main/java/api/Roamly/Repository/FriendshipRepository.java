package api.Roamly.Repository;

import api.Roamly.Domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {

    @Query("SELECT f FROM Friendship f WHERE (f.requester.id = :requesterId AND f.receiver.id = :receiverId) OR (f.requester.id = :receiverId AND f.receiver.id = :requesterId)")
    Optional<Friendship> findByRequesterAndReceiver(@Param("requesterId") UUID requesterId, @Param("receiverId") UUID receiverId);
}
