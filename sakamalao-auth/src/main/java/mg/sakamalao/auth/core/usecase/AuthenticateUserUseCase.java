package mg.sakamalao.auth.core.usecase;

import mg.sakamalao.auth.core.repository.PasswordEncoderPort;
import mg.sakamalao.auth.core.repository.TokenGenerator;
import mg.sakamalao.auth.core.repository.UserRepository;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.core.domain.exception.AuthenticationException;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;

public class AuthenticateUserUseCase {
    private final UserRepository repository;
    private final PasswordEncoderPort passwordValidator;
    private final TokenGenerator tokenGenerator;

    public AuthenticateUserUseCase(UserRepository repository, PasswordEncoderPort passwordValidator, TokenGenerator tokenGenerator) {
        this.repository = repository;
        this.passwordValidator = passwordValidator;
        this.tokenGenerator = tokenGenerator;
    }

    public String authenticate(String username, String password) {
        User user = repository.findByUsername(username)
                .orElseGet(() -> repository.findByEmail(username)
                        .orElseThrow(() -> new EntityNotFoundException("User not found")));

        if (!passwordValidator.validate(password, user.password())) {
            throw new AuthenticationException("Invalid credentials");
        }
        return tokenGenerator.generate(user);
    }
}
