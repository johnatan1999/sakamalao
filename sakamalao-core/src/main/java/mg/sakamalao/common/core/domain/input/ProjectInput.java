package mg.sakamalao.common.core.domain.input;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectInput(
        String name,
        String description,
        UUID ownerId,
        double budget,
        LocalDate startDate,
        LocalDate endDate
) {
}
