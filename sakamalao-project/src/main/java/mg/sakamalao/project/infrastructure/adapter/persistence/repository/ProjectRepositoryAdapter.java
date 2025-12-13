package mg.sakamalao.project.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.core.domain.entity.Project;
import mg.sakamalao.core.domain.input.ProjectInput;
import mg.sakamalao.project.core.repository.ProjectRepository;
import mg.sakamalao.project.infrastructure.adapter.persistence.jpa.ProjectJpaRepository;
import mg.sakamalao.project.infrastructure.adapter.persistence.mapper.ProjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectJpaRepository repository;

    @Override
    public Project save(ProjectInput project) {
        var p = ProjectMapper.toDbEntity(project);
        var newProject = repository.save(p);
        return ProjectMapper.fromDbEntity(newProject);
    }

    @Override
    public List<Project> find(String criteria) {
        return repository.findAll().stream().map(ProjectMapper::fromDbEntity).toList();
    }

    @Override
    public void delete(String projectId) {
        repository.deleteById(UUID.fromString(projectId));
    }

    @Override
    public Optional<Project> findById(String id) {
        try {
            return repository.findById(UUID.fromString(id)).map(ProjectMapper::fromDbEntity);
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
