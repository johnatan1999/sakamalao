package mg.sakamalao.project.infrastructure.driver.entity.response;

import mg.sakamalao.core.domain.entity.Project;

import java.time.LocalDate;

public record ProjectResponse(
        String id,
        String name,
        String description,
        double budget,
        LocalDate startDate,
        LocalDate endDate
) {
    public static ProjectResponse from(Project project) {
        return new ProjectResponse(project.getId(), project.getName(), project.getDescription(), project.getBudget(), project.getStartDate(), project.getEndDate());
    }
}
