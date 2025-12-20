package mg.sakamalao.dashboard.core.domain;

public record ProjectDashboardSummary(
        double totalIncome,
        double totalExpense,
        double balance,
        double projectBudget,
        double remainingBudget
) {
}
