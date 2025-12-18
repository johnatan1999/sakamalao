package mg.sakamalao.expense.infrastructure.config;

import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.expense.core.repository.ExpenseRepository;
import mg.sakamalao.expense.core.usecase.CreateExpenseUseCase;
import mg.sakamalao.expense.core.usecase.ListExpensesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfig {

    @Bean
    public CreateExpenseUseCase createExpenseUseCase(
            ExpenseRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new CreateExpenseUseCase(repository, projectAccessPort);
    }

    @Bean
    public ListExpensesUseCase listExpensesUseCase(
            ExpenseRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new ListExpensesUseCase(repository, projectAccessPort);
    }
}
