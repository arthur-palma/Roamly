package api.Roamly.Exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {
    private String title;
    private String message;
    private int status;
    private List<String> fields;
    private LocalDateTime timestamp;
}