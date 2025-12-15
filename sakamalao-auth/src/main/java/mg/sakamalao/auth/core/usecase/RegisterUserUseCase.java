package mg.sakamalao.auth.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.auth.core.domain.User;
import mg.sakamalao.auth.core.domain.UserRoleEnum;
import mg.sakamalao.auth.core.domain.input.UserInput;
import mg.sakamalao.auth.core.repository.PasswordEncoderPort;
import mg.sakamalao.auth.core.repository.UserRepository;
import mg.sakamalao.core.domain.exception.UserAlreadyExistsException;
import mg.sakamalao.core.validator.FieldValidator;

@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserRepository repository;
    private final PasswordEncoderPort encoder;

    public User register(UserInput userInput) {
        FieldValidator.required("username", userInput.username());
        FieldValidator.required("password", userInput.password());
        FieldValidator.required("email", userInput.email());
        FieldValidator.validateEmail(userInput.email());

        var user = repository.findByUsername(userInput.username());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Username '%s' is already taken".formatted(userInput.username()));
        } else if (repository.findByEmail(userInput.email()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "Email '" + userInput.email() + "' is already registered"
            );
        }
        String hashedPassword = encoder.encode(userInput.password());
        return repository.save(new User(
                null,
                userInput.username(),
                userInput.email(),
                hashedPassword,
                UserRoleEnum.USER
        ));
    }
}
