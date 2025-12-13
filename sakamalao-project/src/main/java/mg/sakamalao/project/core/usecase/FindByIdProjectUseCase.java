package mg.sakamalao.project.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.core.domain.entity.Project;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class FindByIdProjectUseCase {
    private final ProjectRepository repository;

    public Optional<Project> findById(String id) {
        return repository.findById(id);
    }
}
