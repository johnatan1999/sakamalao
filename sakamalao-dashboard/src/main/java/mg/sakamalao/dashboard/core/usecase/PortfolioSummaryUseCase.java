package mg.sakamalao.dashboard.core.usecase;

import mg.sakamalao.dashboard.core.domain.PortfolioSummary;
import mg.sakamalao.dashboard.core.domain.ProjectFinancialSummary;
import mg.sakamalao.dashboard.core.repository.ExpenseDashboardPort;
import mg.sakamalao.dashboard.core.repository.IncomeDashboardPort;
import mg.sakamalao.dashboard.core.repository.ProjectDashboardPort;

import java.util.List;
import java.util.UUID;

public class PortfolioSummaryUseCase {
    private final IncomeDashboardPort incomeRepository;
    private final ExpenseDashboardPort expenseRepository;
    private final ProjectDashboardPort projectRepository;

    public PortfolioSummaryUseCase(
            IncomeDashboardPort incomeRepository,
            ExpenseDashboardPort expenseRepository,
            ProjectDashboardPort projectRepository
    ) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.projectRepository = projectRepository;
    }

    public PortfolioSummary execute(UUID ownerId) {
        List<ProjectFinancialSummary> projects = projectRepository.findAllByOwner(ownerId).stream()
                .map(project -> {
                    double income = incomeRepository.sumByProject(project.id());
                    double expense = expenseRepository.sumByProject(project.id());
                    return new ProjectFinancialSummary(
                            project.id(),
                            project.name(),
                            income,
                            expense,
                            income - expense,
                            project.budget(),
                            project.budget() - expense
                    );
                })
                .toList();

        double totalIncome = projects.stream().mapToDouble(ProjectFinancialSummary::income).sum();
        double totalExpense = projects.stream().mapToDouble(ProjectFinancialSummary::expense).sum();
        double totalBudget = projects.stream().mapToDouble(ProjectFinancialSummary::budget).sum();

        return new PortfolioSummary(
                totalIncome,
                totalExpense,
                totalIncome - totalExpense,
                totalBudget,
                totalBudget - totalExpense,
                projects
        );
    }
}
