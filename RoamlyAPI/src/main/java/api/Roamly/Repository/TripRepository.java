package api.Roamly.Repository;

import api.Roamly.Domain.Trip;
import api.Roamly.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    @Query("SELECT t FROM Trip t WHERE t.owner = :user OR :user MEMBER OF t.participants")
    List<Trip> findByOwnerOrParticipants(@Param("user") User user);

    @Query(value = "SELECT * FROM trip ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<Trip> findRandomTrips();
}
