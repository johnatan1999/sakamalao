package mg.sakamalao.project.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.core.domain.entity.Project;
import mg.sakamalao.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class DeleteProjectUseCase {
    private final ProjectRepository repository;

    public void delete(String projectId) {
        Objects.requireNonNull(projectId, "Id can not be null");
        Optional<Project> project = repository.findById(projectId);
        if (project.isEmpty()) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }
        repository.delete(projectId);
    }
}
