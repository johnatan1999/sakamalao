package mg.sakamalao.dashboard.core.domain;

import mg.sakamalao.common.core.domain.enums.ExpenseCategory;

public record ExpenseCategoryAmount(
        ExpenseCategory category,
        double amount
) {}
