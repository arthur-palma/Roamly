package api.Roamly.DTO.Trip;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateTripDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String destination;

    @Nullable
    private LocalDate startDate;

    @Nullable
    private LocalDate endDate;

    @Nullable
    private BigDecimal budget;

}
