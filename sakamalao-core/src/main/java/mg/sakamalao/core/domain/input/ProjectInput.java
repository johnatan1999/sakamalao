package mg.sakamalao.core.domain.input;

import java.time.LocalDate;

public record ProjectInput(
        String name,
        String description,
        double budget,
        LocalDate startDate,
        LocalDate endDate
) {
}
