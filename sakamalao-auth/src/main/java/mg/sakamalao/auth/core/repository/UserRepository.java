package mg.sakamalao.auth.core.repository;

import mg.sakamalao.common.core.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User save(User user);
}
