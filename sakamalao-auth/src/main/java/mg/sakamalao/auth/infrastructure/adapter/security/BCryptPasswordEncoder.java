package mg.sakamalao.auth.infrastructure.adapter.security;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.auth.core.repository.PasswordEncoderPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptPasswordEncoder implements PasswordEncoderPort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean validate(String password, String passwordHash) {
        return passwordEncoder.matches(password, passwordHash);
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
