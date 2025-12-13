package mg.sakamalao.auth.core.repository;

import mg.sakamalao.auth.core.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    void save(User user);
}
