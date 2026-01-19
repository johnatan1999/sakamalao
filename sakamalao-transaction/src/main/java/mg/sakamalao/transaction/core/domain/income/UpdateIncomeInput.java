package mg.sakamalao.transaction.core.domain.income;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateIncomeInput(
        UUID incomeId,
        String name,
        String description,
        double amount,
        UUID categoryId,
        UUID projectId,
        LocalDateTime date
) {
}
