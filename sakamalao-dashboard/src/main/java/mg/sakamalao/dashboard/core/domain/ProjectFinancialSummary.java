package mg.sakamalao.dashboard.core.domain;

import java.util.UUID;

public record ProjectFinancialSummary(
        UUID projectId,
        String projectName,
        double income,
        double expense,
        double balance,
        double budget,
        double remainingBudget
) {
}
