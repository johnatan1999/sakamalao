package mg.sakamalao.project.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.core.domain.entity.Project;
import mg.sakamalao.core.domain.exception.MissingFieldException;
import mg.sakamalao.core.domain.input.ProjectInput;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CreateProjectUseCase {
    private final ProjectRepository repository;

    public Project create(ProjectInput project) {
        if (project.name() == null || project.name().isBlank()) {
            throw new MissingFieldException("Project", "name");
        }

        return repository.save(new ProjectInput(
                project.name(),
                project.description(),
                project.budget(),
                resolveStartDate(project.startDate()),
                null
        ));
    }

    private LocalDate resolveStartDate(LocalDate startDate) {
        return startDate != null ? startDate : LocalDate.now();
    }
}
