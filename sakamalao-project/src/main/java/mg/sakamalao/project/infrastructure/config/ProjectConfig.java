package mg.sakamalao.project.infrastructure.config;

import mg.sakamalao.project.core.repository.ProjectMemberRepository;
import mg.sakamalao.project.core.repository.ProjectRepository;
import mg.sakamalao.project.core.usecase.CreateProjectUseCase;
import mg.sakamalao.project.core.usecase.DeleteProjectUseCase;
import mg.sakamalao.project.core.usecase.FindByIdProjectUseCase;
import mg.sakamalao.project.core.usecase.FindProjectUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public CreateProjectUseCase createProjectUseCase(
            ProjectRepository repository,
            ProjectMemberRepository projectMemberRepository
    ) {
        return new CreateProjectUseCase(repository, projectMemberRepository);
    }

    @Bean
    public DeleteProjectUseCase deleteProjectUseCase(ProjectRepository repository) {
        return new DeleteProjectUseCase(repository);
    }

    @Bean
    public FindByIdProjectUseCase findByIdProjectUseCase(ProjectRepository repository) {
        return new FindByIdProjectUseCase(repository);
    }

    @Bean
    public FindProjectUseCase findProjectUseCase(ProjectRepository repository) {
        return new FindProjectUseCase(repository);
    }

}
