package mg.sakamalao.project.infrastructure.adapter.persistence.mapper;

import mg.sakamalao.core.domain.entity.Project;
import mg.sakamalao.core.domain.enums.EntityStatus;
import mg.sakamalao.core.domain.input.ProjectInput;
import mg.sakamalao.project.infrastructure.adapter.persistence.entity.ProjectDbEntity;

public class ProjectMapper {

    public static ProjectDbEntity toDbEntity(ProjectInput project) {
        return  new ProjectDbEntity(
                null,
                project.name(),
                project.description(),
                project.budget(),
                project.startDate(),
                project.endDate(),
                EntityStatus.CREATED
        );
    }

    public static Project fromDbEntity(ProjectDbEntity project) {
        var p = new Project(
                project.getName(),
                project.getDescription(),
                project.getBudget(),
                project.getStartDate(),
                project.getEndDate()
        );
        p.setId(project.getId().toString());
        p.setStatus(project.getStatus());
        return p;
    }
}
