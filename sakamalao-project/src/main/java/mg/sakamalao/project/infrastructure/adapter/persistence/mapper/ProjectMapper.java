package mg.sakamalao.project.infrastructure.adapter.persistence.mapper;

import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.common.core.domain.enums.EntityStatus;
import mg.sakamalao.project.infrastructure.adapter.persistence.entity.ProjectDbEntity;

public class ProjectMapper {

    public static ProjectDbEntity toDbEntity(Project project) {
        return  new ProjectDbEntity(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getBudget(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus() != null ? project.getStatus() : EntityStatus.CREATED,
                project.getOwerId()
        );
    }

    public static Project fromDbEntity(ProjectDbEntity project) {
        var p = new Project(
                project.getName(),
                project.getDescription(),
                project.getOwnerId(),
                project.getBudget(),
                project.getStartDate(),
                project.getEndDate()
        );
        p.setId(project.getId());
        p.setStatus(project.getStatus());
        return p;
    }
}
