package mg.sakamalao.dashboard.infrastructure.config;

import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.dashboard.core.repository.ExpenseDashboardPort;
import mg.sakamalao.dashboard.core.repository.IncomeDashboardPort;
import mg.sakamalao.dashboard.core.repository.ProjectDashboardPort;
import mg.sakamalao.dashboard.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DashboardConfig {
    @Bean
    public ExpenseBreakdownByCategoryUseCase expenseBreakdownByCategoryUseCase(
            ExpenseDashboardPort expenseDashboardPort,
            ProjectAccessPort projectAccessPort
    ) {
        return new ExpenseBreakdownByCategoryUseCase(
                expenseDashboardPort,
                projectAccessPort
        );
    }

    @Bean
    public IncomeBreakdownByCategoryUseCase incomeBreakdownByCategoryUseCase(
            IncomeDashboardPort incomeDashboardPort,
            ProjectAccessPort projectAccessPort
    ) {
        return new IncomeBreakdownByCategoryUseCase(
                incomeDashboardPort,
                projectAccessPort
        );
    }

    @Bean
    public ProjectHealthUseCase projectBudgetHealthUseCase(
            IncomeDashboardPort incomeDashboardPort,
            ExpenseDashboardPort expenseDashboardPort,
            ProjectDashboardPort projectDashboardPort,
            ProjectAccessPort projectAccessPort
    ) {
        return new ProjectHealthUseCase(
                incomeDashboardPort,
                expenseDashboardPort,
                projectDashboardPort,
                projectAccessPort
        );
    }

    @Bean
    public ProjectDashboardSummaryUseCase projectDashboardSummaryUseCase(
            IncomeDashboardPort incomeDashboardPort,
            ExpenseDashboardPort expenseDashboardPort,
            ProjectDashboardPort projectDashboardPort,
            ProjectAccessPort projectAccessPort
    ) {
        return new ProjectDashboardSummaryUseCase(
                incomeDashboardPort,
                expenseDashboardPort,
                projectDashboardPort,
                projectAccessPort
        );
    }

    @Bean
    public ProjectMonthlyCashFlowUseCase projectMonthlyCashFlowUseCase(
            IncomeDashboardPort incomeDashboardPort,
            ExpenseDashboardPort expenseDashboardPort,
            ProjectAccessPort projectAccessPort
    ) {
        return new ProjectMonthlyCashFlowUseCase(
                incomeDashboardPort,
                expenseDashboardPort,
                projectAccessPort
        );
    }
}
