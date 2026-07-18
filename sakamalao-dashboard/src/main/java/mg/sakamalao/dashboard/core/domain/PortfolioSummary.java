package mg.sakamalao.dashboard.core.domain;

import java.util.List;

public record PortfolioSummary(
        double totalIncome,
        double totalExpense,
        double balance,
        double totalBudget,
        double remainingBudget,
        List<ProjectFinancialSummary> projects
) {
}
