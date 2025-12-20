package mg.sakamalao.dashboard.core.usecase;

import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.dashboard.core.domain.IncomeCategoryAmount;
import mg.sakamalao.dashboard.core.repository.IncomeDashboardPort;

import java.util.List;
import java.util.UUID;

public class IncomeBreakdownByCategoryUseCase {
    private final IncomeDashboardPort incomeRepository;
    private final ProjectAccessPort projectAccess;

    public IncomeBreakdownByCategoryUseCase(IncomeDashboardPort incomeRepository, ProjectAccessPort projectAccess) {
        this.incomeRepository = incomeRepository;
        this.projectAccess = projectAccess;
    }

    public List<IncomeCategoryAmount> execute(UUID projectId, UUID userId) {

        var hasAccess = projectAccess.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        return incomeRepository.sumByCategory(projectId);
    }
}
