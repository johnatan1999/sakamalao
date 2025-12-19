package mg.sakamalao.income.infrastructure.config;

import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.income.core.repository.IncomeRepository;
import mg.sakamalao.income.core.usecase.CreateIncomeUseCase;
import mg.sakamalao.income.core.usecase.DeleteIncomeUseCase;
import mg.sakamalao.income.core.usecase.FindIncomeUseCase;
import mg.sakamalao.income.core.usecase.UpdateIncomeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IncomeConfig {

    @Bean
    public CreateIncomeUseCase createIncomeUseCase(
            IncomeRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new CreateIncomeUseCase(repository, projectAccessPort);
    }

    @Bean
    public UpdateIncomeUseCase updateIncomeUseCase(
            IncomeRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new UpdateIncomeUseCase(repository, projectAccessPort);
    }

    @Bean
    public DeleteIncomeUseCase deleteIncomeUseCase(
            IncomeRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new DeleteIncomeUseCase(repository, projectAccessPort);
    }

    @Bean
    public FindIncomeUseCase findIncomeUseCase(
            IncomeRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new FindIncomeUseCase(repository, projectAccessPort);
    }
}
