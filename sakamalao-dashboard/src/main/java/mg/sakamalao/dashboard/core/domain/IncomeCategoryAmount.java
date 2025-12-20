package mg.sakamalao.dashboard.core.domain;

import mg.sakamalao.common.core.domain.enums.IncomeCategory;

public record IncomeCategoryAmount(
        IncomeCategory category,
        double amount
) {
}
