package mg.sakamalao.project.infrastructure.driver.entity.request;

import mg.sakamalao.common.core.domain.input.ProjectInput;

import java.time.LocalDate;

public record UpdateProjectRequest(
        String name,
        String description,
        double budget,
        LocalDate startDate
) {
    public ProjectInput toProjectInput() {
        return new ProjectInput(
                name,
                description,
                null,
                budget,
                startDate,
                null
        );
    }
}
