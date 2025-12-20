package mg.sakamalao.dashboard.core.usecase;

import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.dashboard.core.domain.ProjectDashboardSummary;
import mg.sakamalao.dashboard.core.repository.ExpenseDashboardPort;
import mg.sakamalao.dashboard.core.repository.IncomeDashboardPort;
import mg.sakamalao.dashboard.core.repository.ProjectDashboardPort;

import java.util.UUID;

public class ProjectDashboardSummaryUseCase {
    private final IncomeDashboardPort incomeRepository;
    private final ExpenseDashboardPort expenseRepository;
    private final ProjectDashboardPort projectRepository;
    private final ProjectAccessPort projectAccess;

    public ProjectDashboardSummaryUseCase(
            IncomeDashboardPort incomeRepository,
            ExpenseDashboardPort expenseRepository,
            ProjectDashboardPort projectRepository,
            ProjectAccessPort projectAccess
    ) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.projectAccess = projectAccess;
        this.projectRepository = projectRepository;
    }

    public ProjectDashboardSummary execute(UUID projectId, UUID userId) {
        var hasAccess = projectAccess.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        double budget = projectRepository.getBudget(projectId);
        double totalIncome = incomeRepository.sumByProject(projectId);
        double totalExpense = expenseRepository.sumByProject(projectId);

        return new ProjectDashboardSummary(
                totalIncome,
                totalExpense,
                totalIncome - totalExpense,
                budget,
                budget - totalExpense
        );
    }
}
