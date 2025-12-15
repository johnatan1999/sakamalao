package mg.sakamalao.project.infrastructure.driver.entity.response;

import mg.sakamalao.core.domain.entity.Project;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectResponse(
        UUID id,
        String name,
        String description,
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
                project.getOwerId(),
                project.getBudget(),
                project.getStartDate(),
                project.getEndDate()
        );
    }
}
