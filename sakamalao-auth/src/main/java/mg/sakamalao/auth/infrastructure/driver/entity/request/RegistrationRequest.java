package mg.sakamalao.auth.infrastructure.driver.entity.request;

import mg.sakamalao.auth.core.domain.input.UserInput;

public record RegistrationRequest(
        String username,
        String password,
        String email
) {
    public UserInput toUserInput() {
        return new UserInput(
                username,
                password,
                email
        );
    }
}
