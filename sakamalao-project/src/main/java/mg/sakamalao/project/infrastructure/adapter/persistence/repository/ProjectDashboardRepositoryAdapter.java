package mg.sakamalao.project.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.dashboard.core.repository.ProjectDashboardPort;
import mg.sakamalao.project.core.repository.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProjectDashboardRepositoryAdapter implements ProjectDashboardPort {

    private final ProjectRepository repository;

    @Override
    public double getBudget(UUID projectId) {
        var project = repository.findById(projectId);
        return project.map(Project::getBudget).orElse(0.0);
    }
}
