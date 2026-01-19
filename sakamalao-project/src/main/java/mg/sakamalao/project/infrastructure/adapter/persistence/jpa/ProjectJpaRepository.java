package mg.sakamalao.project.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.common.core.domain.enums.EntityStatus;
import mg.sakamalao.common.infrastructure.adapter.entity.ProjectDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectDbEntity, UUID> {
    List<ProjectDbEntity> findAllByOwnerIdAndStatusNot(UUID ownerId, EntityStatus status);
}
