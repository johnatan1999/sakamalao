package mg.sakamalao.income.core.domain;

import java.time.LocalDate;
import java.util.UUID;

public record IncomeInput (
        String name,
        UUID categoryId,
        String description,
        double amount,
        LocalDate date,
        UUID projectId
) {
}
