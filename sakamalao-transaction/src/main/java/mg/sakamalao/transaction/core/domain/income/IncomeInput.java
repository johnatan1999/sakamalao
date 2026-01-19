package mg.sakamalao.transaction.core.domain.income;

import java.time.LocalDateTime;
import java.util.UUID;

public record IncomeInput (
        String name,
        UUID categoryId,
        String description,
        double amount,
        LocalDateTime date,
        UUID projectId
) {
}
