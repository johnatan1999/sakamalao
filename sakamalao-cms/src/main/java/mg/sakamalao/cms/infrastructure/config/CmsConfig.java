package mg.sakamalao.cms.infrastructure.config;

import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.cms.core.usecase.CreateTransactionCategoryUseCase;
import mg.sakamalao.cms.core.usecase.DeleteTransactionCategoryUseCase;
import mg.sakamalao.cms.core.usecase.ListTransactionCategoriesUseCase;
import mg.sakamalao.cms.core.usecase.UpdateTransactionCategoryUseCase;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CmsConfig {

    @Bean
    public ListTransactionCategoriesUseCase listTransactionCategories(
            TransactionCategoryRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new ListTransactionCategoriesUseCase(
                repository,
                projectAccessPort
        );
    }

    @Bean
    public CreateTransactionCategoryUseCase createTransactionCategoryUseCase(
            TransactionCategoryRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new CreateTransactionCategoryUseCase(
                repository,
                projectAccessPort
        );
    }

    @Bean
    public UpdateTransactionCategoryUseCase updateTransactionCategoryUseCase(
            TransactionCategoryRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new UpdateTransactionCategoryUseCase(
                repository,
                projectAccessPort
        );
    }

    @Bean
    public DeleteTransactionCategoryUseCase deleteTransactionCategoryUseCase(
            TransactionCategoryRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new DeleteTransactionCategoryUseCase(
                repository,
                projectAccessPort
        );
    }
}
