package api.Roamly.Domain;


import api.Roamly.Domain.Enum.InvitationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TripInvite {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "trip_id", nullable = false)
        private Trip trip;

        @ManyToOne
        @JoinColumn(name = "invited_user_id", nullable = false)
        private User invitedUser;

        @Enumerated(EnumType.STRING)
        private InvitationStatus status;


    }
