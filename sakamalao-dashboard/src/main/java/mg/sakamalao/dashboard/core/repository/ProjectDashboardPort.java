package mg.sakamalao.dashboard.core.repository;

import java.util.UUID;

public interface ProjectDashboardPort {
    double getBudget(UUID projectId);
}
