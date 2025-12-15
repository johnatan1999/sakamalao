package mg.sakamalao.auth.infrastructure.config;

import mg.sakamalao.auth.core.repository.PasswordEncoderPort;
import mg.sakamalao.auth.core.repository.TokenGenerator;
import mg.sakamalao.auth.core.repository.UserRepository;
import mg.sakamalao.auth.core.usecase.AuthenticateUserUseCase;
import mg.sakamalao.auth.core.usecase.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public AuthenticateUserUseCase authenticateUserUseCase(
            UserRepository repository,
            PasswordEncoderPort passwordEncoderPort,
            TokenGenerator tokenGenerator
    ) {
        return new AuthenticateUserUseCase(
                repository,
                passwordEncoderPort,
                tokenGenerator
        );
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            UserRepository repository,
            PasswordEncoderPort passwordEncoderPort
    ) {
        return new RegisterUserUseCase(repository, passwordEncoderPort);
    }
}
