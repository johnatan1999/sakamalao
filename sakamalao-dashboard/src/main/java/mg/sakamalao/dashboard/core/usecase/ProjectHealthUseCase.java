package mg.sakamalao.dashboard.core.usecase;

import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.dashboard.core.domain.ProjectHealth;
import mg.sakamalao.dashboard.core.domain.enums.BudgetStatus;
import mg.sakamalao.dashboard.core.repository.ExpenseDashboardPort;
import mg.sakamalao.dashboard.core.repository.IncomeDashboardPort;
import mg.sakamalao.dashboard.core.repository.ProjectDashboardPort;

import java.util.UUID;

public class ProjectHealthUseCase {

    private final IncomeDashboardPort incomeDashboardPort;
    private final ExpenseDashboardPort expenseRepository;
    private final ProjectDashboardPort projectRepository;
    private final ProjectAccessPort projectAccess;

    public ProjectHealthUseCase(
            IncomeDashboardPort incomeDashboardPort,
            ExpenseDashboardPort expenseRepository,
            ProjectDashboardPort projectRepository,
            ProjectAccessPort projectAccess
    ) {
        this.incomeDashboardPort = incomeDashboardPort;
        this.expenseRepository = expenseRepository;
        this.projectRepository = projectRepository;
        this.projectAccess = projectAccess;
    }

    public ProjectHealth execute(UUID projectId, UUID userId) {

        var hasAccess = projectAccess.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        double budget = projectRepository.getBudget(projectId);
        double expenses = expenseRepository.sumByProject(projectId);
        double incomes = incomeDashboardPort.sumByProject(projectId);

        double total = incomes + budget + expenses;
        double totalRemaining = (incomes + budget) - expenses;
        double percentage = (totalRemaining / total) * 100;

        BudgetStatus status = BudgetStatus.SAFE;
        if (percentage < 0) {
            status = BudgetStatus.CRITICAL;
        }  else if (percentage > 50) {
            status = BudgetStatus.GOOD;
        }

        return new ProjectHealth(percentage, status, budget, expenses, incomes, totalRemaining);
    }
}
