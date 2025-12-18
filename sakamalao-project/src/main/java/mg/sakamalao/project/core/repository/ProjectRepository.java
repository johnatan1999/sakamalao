package mg.sakamalao.project.core.repository;

import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.common.core.domain.input.ProjectInput;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {
    Project save(ProjectInput project);
    List<Project> find(UUID ownerId, String criteria);
    void delete(String projectId);
    Optional<Project> findById(String id);
}
