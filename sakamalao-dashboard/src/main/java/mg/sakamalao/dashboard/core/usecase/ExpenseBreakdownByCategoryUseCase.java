package mg.sakamalao.dashboard.core.usecase;

import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.dashboard.core.domain.ExpenseCategoryAmount;
import mg.sakamalao.dashboard.core.repository.ExpenseDashboardPort;

import java.util.List;
import java.util.UUID;

public class ExpenseBreakdownByCategoryUseCase {
    private final ExpenseDashboardPort expenseRepository;
    private final ProjectAccessPort projectAccess;

    public ExpenseBreakdownByCategoryUseCase(ExpenseDashboardPort expenseRepository, ProjectAccessPort projectAccess) {
        this.expenseRepository = expenseRepository;
        this.projectAccess = projectAccess;
    }

    public List<ExpenseCategoryAmount> execute(UUID projectId, UUID userId) {

        var hasAccess = projectAccess.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        return expenseRepository.sumByCategory(projectId);
    }
}
