package mg.sakamalao.project.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.project.infrastructure.adapter.persistence.entity.ProjectMemberDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectMemberJpaRepository extends JpaRepository<ProjectMemberDbEntity, UUID> {
    boolean existsByProjectIdAndUserId(UUID projectId, UUID userId);

    List<ProjectMemberDbEntity> findByProjectId(UUID projectId);
}
