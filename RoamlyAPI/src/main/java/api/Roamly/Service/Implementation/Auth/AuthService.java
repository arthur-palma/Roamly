package api.Roamly.Service.Implementation.Auth;

import api.Roamly.Config.JwtUtil;
import api.Roamly.DTO.Auth.LoginDTO;
import api.Roamly.DTO.User.UserDTO;
import api.Roamly.Domain.User;
import api.Roamly.Repository.UserRepository;
import api.Roamly.Service.Interface.Auth.IAuthService;
import api.Roamly.Service.Interface.Security.IPasswordEncryptionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import static api.Roamly.Mapper.UserMapper.toDTO;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final IPasswordEncryptionService passwordEncryptionService;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<UserDTO> login(LoginDTO loginDTO, HttpServletResponse response) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncryptionService.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        Cookie jwtCookie = new Cookie("jwt", token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge(24 * 60 * 60);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return ResponseEntity.ok(toDTO(user));
    }

    public String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }

    public User getAuthenticatedUser() {
        String username = getAuthenticatedUsername();
        if (username == null) {
            throw new RuntimeException("User not authenticated");
        }

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
