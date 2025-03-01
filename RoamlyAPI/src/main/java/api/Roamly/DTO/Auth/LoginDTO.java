package api.Roamly.DTO.Auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    private String email;

    private String password;


}
