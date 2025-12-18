package mg.sakamalao.project.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class FindProjectUseCase {
    private final ProjectRepository repository;

    public List<Project> find(UUID ownerId, String criteria) {
        return repository.find(ownerId, criteria);
    }
}
