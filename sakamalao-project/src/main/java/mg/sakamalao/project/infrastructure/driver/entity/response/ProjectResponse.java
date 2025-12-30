package mg.sakamalao.project.infrastructure.driver.entity.response;

import mg.sakamalao.common.core.domain.entity.Project;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectResponse(
        UUID id,
        String name,
        String description,
        String currency,
        UUID ownerId,
        double budget,
        LocalDate startDate,
        LocalDate endDate
) {
    public static ProjectResponse from(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getCurrency(),
                project.getOwerId(),
                project.getBudget(),
                project.getStartDate(),
                project.getEndDate()
        );
    }
}
