package mg.sakamalao.project.infrastructure.driver.entity.request;

import mg.sakamalao.core.domain.input.ProjectInput;

import java.time.LocalDate;

public record CreateProjectRequest(
        String name,
        String description,
        double budget,
        LocalDate startDate,
        LocalDate endDate
) {
    public ProjectInput toProjectInput() {
        return new ProjectInput(
                name,
                description,
                budget,
                startDate,
                endDate
        );
    }
}