package mg.sakamalao.income.infrastructure.driver.entity;

import mg.sakamalao.common.core.domain.enums.IncomeCategory;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateIncomeRequest(
        String name,
        String description,
        double amount,
        IncomeCategory category,
        UUID projectId,
        LocalDate date
) {
}
