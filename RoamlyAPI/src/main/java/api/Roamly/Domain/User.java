package api.Roamly.Domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String profilePicture;

    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL)
    private List<Trip> trips = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "requester_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_id")
    )
    private List<User> friends = new ArrayList<>();

    public void addTrip(Trip trip) {
        if(!trips.contains(trip) && !trip.getParticipants().contains(this)){
            trips.add(trip);
            trip.getParticipants().add(this);
        }
    }

    public void addFriend(User friend) {
        if (!friends.contains(friend) && !friend.getFriends().contains(this)) {
            friends.add(friend);
            friend.getFriends().add(this);
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void unfriend(User friend) {
        if (friends.contains(friend) && friend.getFriends().contains(this)) {
            friends.remove(friend);
            friend.getFriends().remove(this);
        }
    }
}
