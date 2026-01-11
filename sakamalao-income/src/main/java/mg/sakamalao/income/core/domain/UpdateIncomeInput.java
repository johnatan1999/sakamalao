package mg.sakamalao.income.core.domain;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateIncomeInput(
        UUID incomeId,
        String name,
        String description,
        double amount,
        UUID categoryId,
        UUID projectId,
        LocalDate date
) {
}
