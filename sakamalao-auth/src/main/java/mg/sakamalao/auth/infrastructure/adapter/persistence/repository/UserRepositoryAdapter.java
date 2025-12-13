package mg.sakamalao.auth.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.auth.core.domain.RoleEnum;
import mg.sakamalao.auth.core.domain.User;
import mg.sakamalao.auth.core.repository.UserRepository;
import mg.sakamalao.auth.infrastructure.adapter.persistence.entity.UserDbEntity;
import mg.sakamalao.auth.infrastructure.adapter.persistence.jpa.UserJpaRepository;
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
                        user.getId().toString(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()
                )
        );
    }

    @Override
    public void save(User user) {
        UserDbEntity entity = new UserDbEntity(
                null,
                user.username(),
                user.password(),
                user.password(),
                RoleEnum.ADMIN
        );
        repository.save(entity);
    }
}
