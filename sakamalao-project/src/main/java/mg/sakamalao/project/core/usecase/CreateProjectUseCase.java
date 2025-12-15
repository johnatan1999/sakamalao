package mg.sakamalao.project.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.core.domain.entity.Project;
import mg.sakamalao.core.domain.enums.ProjectRole;
import mg.sakamalao.core.domain.exception.MissingFieldException;
import mg.sakamalao.core.domain.input.ProjectInput;
import mg.sakamalao.core.validator.FieldValidator;
import mg.sakamalao.project.core.domain.ProjectMemberInput;
import mg.sakamalao.project.core.repository.ProjectMemberRepository;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateProjectUseCase {
    private final ProjectRepository repository;
    private final ProjectMemberRepository pmRepository;

    public Project create(UUID ownerId, ProjectInput project) {
        FieldValidator.notNull("Owner id", ownerId);
        if (project.name() == null || project.name().isBlank()) {
            throw new MissingFieldException("Project", "name");
        }

        Project p = repository.save(new ProjectInput(
                project.name(),
                project.description(),
                ownerId,
                project.budget(),
                resolveStartDate(project.startDate()),
                null
        ));

        pmRepository.save(new ProjectMemberInput(
                p.getId(),
                ownerId,
                ProjectRole.OWNER
        ));
        return p;
    }

    private LocalDate resolveStartDate(LocalDate startDate) {
        return startDate != null ? startDate : LocalDate.now();
    }
}
