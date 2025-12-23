package mg.sakamalao.project.core.usecase;

import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.common.core.domain.enums.EntityStatus;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.util.Objects;
import java.util.UUID;

public class DeleteProjectUseCase {
    private final ProjectRepository repository;

    public DeleteProjectUseCase(ProjectRepository repository) {
        this.repository = repository;
    }

    public void delete(UUID userId, UUID projectId) {
        Objects.requireNonNull(projectId, "Id can not be null");
        Project project = repository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Project with id=%s not found".formatted(projectId))
        );

        if (!project.getOwerId().equals(userId)) {
            throw new EntityNotFoundException(
                    "Project with id=%s not found".formatted(projectId)
            );
        }

        if (project.getStatus() == EntityStatus.DELETED) {
            return;
        }

        project.setStatus(EntityStatus.DELETED);

        repository.save(project);
    }
}
