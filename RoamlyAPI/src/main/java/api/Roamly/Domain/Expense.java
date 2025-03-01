package api.Roamly.Domain;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;
    private BigDecimal amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
