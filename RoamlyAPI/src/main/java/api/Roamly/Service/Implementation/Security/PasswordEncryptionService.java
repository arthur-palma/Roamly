package api.Roamly.Service.Implementation.Security;

import api.Roamly.Service.Interface.Security.IPasswordEncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PasswordEncryptionService implements IPasswordEncryptionService {
    private final PasswordEncoder passwordEncoder;

    public PasswordEncryptionService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encrypt(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
