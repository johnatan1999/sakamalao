package mg.sakamalao.income.infrastructure.driver.entity;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateIncomeRequest(
        String name,
        String description,
        double amount,
        UUID categoryId,
        UUID projectId,
        LocalDate date
) {
}
