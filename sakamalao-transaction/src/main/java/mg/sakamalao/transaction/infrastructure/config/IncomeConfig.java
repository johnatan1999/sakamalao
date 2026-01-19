package mg.sakamalao.transaction.infrastructure.config;

import mg.sakamalao.common.core.port.CategoryAccessPort;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;
import mg.sakamalao.transaction.core.usecase.income.CreateIncomeUseCase;
import mg.sakamalao.transaction.core.usecase.income.DeleteIncomeUseCase;
import mg.sakamalao.transaction.core.usecase.income.FindIncomeUseCase;
import mg.sakamalao.transaction.core.usecase.income.UpdateIncomeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IncomeConfig {

    @Bean
    public CreateIncomeUseCase createIncomeUseCase(
            IncomeRepository repository,
            ProjectAccessPort projectAccessPort,
            CategoryAccessPort categoryAccessPort
    ) {
        return new CreateIncomeUseCase(repository, projectAccessPort, categoryAccessPort);
    }

    @Bean
    public UpdateIncomeUseCase updateIncomeUseCase(
            IncomeRepository repository,
            ProjectAccessPort projectAccessPort,
            CategoryAccessPort categoryAccessPort
    ) {
        return new UpdateIncomeUseCase(repository, projectAccessPort, categoryAccessPort);
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
