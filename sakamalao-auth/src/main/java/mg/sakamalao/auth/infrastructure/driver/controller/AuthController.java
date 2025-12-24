package mg.sakamalao.auth.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.auth.core.domain.security.PublicEndpoint;
import mg.sakamalao.auth.core.usecase.AuthenticateUserUseCase;
import mg.sakamalao.auth.core.usecase.RegisterUserUseCase;
import mg.sakamalao.auth.infrastructure.driver.entity.request.LoginRequest;
import mg.sakamalao.auth.infrastructure.driver.entity.request.RegistrationRequest;
import mg.sakamalao.auth.infrastructure.driver.entity.response.TokenResponse;
import mg.sakamalao.auth.infrastructure.driver.entity.response.UserResponse;
import mg.sakamalao.common.core.domain.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticateUserUseCase useCase;
    private final RegisterUserUseCase registerUserUseCase;

    @PublicEndpoint
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {
        String token = useCase.authenticate(req.username(), req.password());
        return new TokenResponse(token);
    }

    @PublicEndpoint
    @PostMapping("/register")
    public UserResponse register(@RequestBody RegistrationRequest req) {
        User newUser = registerUserUseCase.register(req.toUserInput());
        return new UserResponse(
                newUser.id(),
                newUser.username(),
                newUser.email(),
                newUser.role()
        );
    }
}
