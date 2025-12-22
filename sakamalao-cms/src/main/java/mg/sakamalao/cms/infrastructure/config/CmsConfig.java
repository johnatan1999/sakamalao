package mg.sakamalao.cms.infrastructure.config;

import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.cms.core.usecase.ListTransactionCategoriesUseCase;
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
}
