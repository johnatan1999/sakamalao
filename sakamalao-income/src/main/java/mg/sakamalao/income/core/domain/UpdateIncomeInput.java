package mg.sakamalao.income.core.domain;

import mg.sakamalao.common.core.domain.enums.IncomeCategory;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateIncomeInput(
        UUID incomeId,
        String name,
        String description,
        double amount,
        IncomeCategory category,
        UUID projectId,
        LocalDate date
) {
}
