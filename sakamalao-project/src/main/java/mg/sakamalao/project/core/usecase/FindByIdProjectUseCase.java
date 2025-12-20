package mg.sakamalao.project.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class FindByIdProjectUseCase {
    private final ProjectRepository repository;

    public Optional<Project> findById(UUID userId, UUID id) {
        var result = repository.findById(id);
        if (result.isPresent() && !result.get().getOwerId().equals(userId)) {
            throw new EntityNotFoundException(
                    "Project with id=%s not found".formatted(id)
            );
        }
        return result;
    }
}
