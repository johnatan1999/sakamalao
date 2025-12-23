package mg.sakamalao.project.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.common.core.domain.enums.EntityStatus;
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
    public Project save(Project project) {
        var p = ProjectMapper.toDbEntity(project);
        var newProject = repository.save(p);
        return ProjectMapper.fromDbEntity(newProject);
    }

    @Override
    public List<Project> find(UUID ownerId, String criteria) {
        return repository.findAllByOwnerIdAndStatusNot(ownerId, EntityStatus.DELETED).stream().map(ProjectMapper::fromDbEntity).toList();
    }

    @Override
    public void delete(UUID projectId) {
        repository.deleteById(projectId);
    }

    @Override
    public Optional<Project> findById(UUID id) {
        try {
            return repository.findById(id).map(ProjectMapper::fromDbEntity);
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
