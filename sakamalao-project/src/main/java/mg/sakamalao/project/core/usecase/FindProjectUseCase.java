package mg.sakamalao.project.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.core.domain.entity.Project;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.util.List;

@RequiredArgsConstructor
public class FindProjectUseCase {
    private final ProjectRepository repository;

    public List<Project> find(String criteria) {
        return repository.find(criteria);
    }
}
