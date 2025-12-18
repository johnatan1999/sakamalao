package mg.sakamalao.project.infrastructure.adapter.persistence;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.enums.ProjectRole;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.project.infrastructure.adapter.persistence.jpa.ProjectMemberJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectAccessAdapter implements ProjectAccessPort {

    private final ProjectMemberJpaRepository repository;

    @Override
    public boolean hasAccess(UUID userId, UUID projectId) {
        return repository.existsByProjectIdAndUserId(projectId, userId);
    }

    @Override
    public boolean isOwner(UUID userId, UUID projectId) {
        return repository.existsByProjectIdAndUserIdAndRole(
                projectId, userId, ProjectRole.OWNER
        );
    }
}
