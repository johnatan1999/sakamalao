package mg.sakamalao.auth.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.auth.infrastructure.adapter.persistence.entity.UserDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDbEntity, String> {
    Optional<UserDbEntity> findOneByUsername(String username);
    Optional<UserDbEntity> findOneByEmail(String email);
}
