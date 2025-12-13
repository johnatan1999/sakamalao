package mg.sakamalao.auth.infrastructure.driver.controller;

import mg.sakamalao.auth.core.usecase.AuthenticateUserUseCase;
import mg.sakamalao.auth.infrastructure.driver.entity.LoginRequest;
import mg.sakamalao.auth.infrastructure.driver.entity.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticateUserUseCase useCase;

    public AuthController(AuthenticateUserUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {
        String token = useCase.authenticate(req.username(), req.password());
        return new TokenResponse(token);
    }
}
