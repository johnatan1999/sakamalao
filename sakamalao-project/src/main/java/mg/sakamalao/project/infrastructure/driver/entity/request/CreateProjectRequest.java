package mg.sakamalao.project.infrastructure.driver.entity.request;

import mg.sakamalao.common.core.domain.input.ProjectInput;

import java.time.LocalDate;

public record CreateProjectRequest(
        String name,
        String description,
        String currency,
        double budget,
        LocalDate startDate,
        LocalDate endDate
) {
    public ProjectInput toProjectInput() {
        return new ProjectInput(
                name,
                description,
                currency,
                null,
                budget,
                startDate,
                endDate
        );
    }
}