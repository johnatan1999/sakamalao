package mg.sakamalao.dashboard.core.repository;

import mg.sakamalao.dashboard.core.domain.ProjectOverview;

import java.util.List;
import java.util.UUID;

public interface ProjectDashboardPort {
    double getBudget(UUID projectId);

    List<ProjectOverview> findAllByOwner(UUID ownerId);
}
