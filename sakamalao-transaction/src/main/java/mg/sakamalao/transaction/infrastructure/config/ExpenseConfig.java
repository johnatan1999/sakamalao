package mg.sakamalao.transaction.infrastructure.config;

import mg.sakamalao.common.core.port.CategoryAccessPort;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.transaction.core.repository.expense.ExpenseRepository;
import mg.sakamalao.transaction.core.usecase.expense.CreateExpenseUseCase;
import mg.sakamalao.transaction.core.usecase.expense.DeleteExpenseUseCase;
import mg.sakamalao.transaction.core.usecase.expense.ListExpensesUseCase;
import mg.sakamalao.transaction.core.usecase.expense.UpdateExpenseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfig {

    @Bean
    public CreateExpenseUseCase createExpenseUseCase(
            ExpenseRepository repository,
            ProjectAccessPort projectAccessPort,
            CategoryAccessPort categoryAccess
    ) {
        return new CreateExpenseUseCase(repository, projectAccessPort, categoryAccess);
    }

    @Bean
    public ListExpensesUseCase listExpensesUseCase(
            ExpenseRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new ListExpensesUseCase(repository, projectAccessPort);
    }

    @Bean
    public DeleteExpenseUseCase deleteExpenseUseCase(
            ExpenseRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        return new DeleteExpenseUseCase(repository, projectAccessPort);
    }

    @Bean
    public UpdateExpenseUseCase updateExpenseUseCase(
            ExpenseRepository repository,
            ProjectAccessPort projectAccessPort,
            CategoryAccessPort categoryAccess
    ) {
        return new UpdateExpenseUseCase(repository, projectAccessPort, categoryAccess);
    }
}
