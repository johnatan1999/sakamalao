package mg.sakamalao.project.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class DeleteProjectUseCase {
    private final ProjectRepository repository;

    public void delete(UUID userId, UUID projectId) {
        Objects.requireNonNull(projectId, "Id can not be null");
        Optional<Project> project = repository.findById(projectId);
        if (project.isEmpty()) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }
        if (!project.get().getOwerId().equals(userId)) {
            throw new EntityNotFoundException(
                    "Project with id=%s not found".formatted(projectId)
            );
        }
        repository.delete(projectId);
    }
}
