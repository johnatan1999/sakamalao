package mg.sakamalao.dashboard.core.domain;

import mg.sakamalao.dashboard.core.domain.enums.BudgetStatus;

public record ProjectHealth(
        double roiPercentage, // Return of investment
        BudgetStatus status,
        double budget,
        double expenses,
        double incomes,
        double remaining
) {
}
