package mg.sakamalao.income.core.domain;

import mg.sakamalao.common.core.domain.enums.IncomeCategory;

import java.time.LocalDate;
import java.util.UUID;

public record IncomeInput (
        String name,
        IncomeCategory category,
        String description,
        double amount,
        LocalDate date,
        UUID projectId
) {
}
