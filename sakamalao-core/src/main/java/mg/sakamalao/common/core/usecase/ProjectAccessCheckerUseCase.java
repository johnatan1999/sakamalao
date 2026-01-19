package mg.sakamalao.common.core.usecase;

import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;

import java.util.UUID;

public class ProjectAccessCheckerUseCase {
    private final ProjectAccessPort projectAccess;

    public ProjectAccessCheckerUseCase(ProjectAccessPort projectAccess) {
        this.projectAccess = projectAccess;
    }

    public void checkAccess(UUID userId, UUID projectId) {
        var hasAccess = projectAccess.hasAccess(userId, projectId);
        if (!hasAccess) {
            // Throw a not found exception to avoid telling
            // the user that the project exists but that they cannot access it
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId.toString()));
        }
    }
}
