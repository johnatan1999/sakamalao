package mg.sakamalao.auth.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.auth.core.repository.UserRepository;
import mg.sakamalao.auth.infrastructure.adapter.persistence.entity.UserDbEntity;
import mg.sakamalao.auth.infrastructure.adapter.persistence.jpa.UserJpaRepository;
import mg.sakamalao.core.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;

    @Override
    public Optional<User> findByUsername(String username) {
        var optionalUser =  repository.findOneByUsername(username);
        if (optionalUser.isEmpty())
            return Optional.empty();
        var user = optionalUser.get();
        return Optional.of(
                new User(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()
                )
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var optionalUser =  repository.findOneByEmail(email);
        if (optionalUser.isEmpty())
            return Optional.empty();
        var user = optionalUser.get();
        return Optional.of(
                new User(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()
                )
        );
    }

    @Override
    public User save(User user) {
        UserDbEntity entity = new UserDbEntity(
                null,
                user.username(),
                user.email(),
                user.password(),
                user.role()
        );
        var newUser = repository.save(entity);
        return new User(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getEmail(),
                null,
                newUser.getRole()
        );
    }
}
