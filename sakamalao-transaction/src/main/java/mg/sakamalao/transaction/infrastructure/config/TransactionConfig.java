package mg.sakamalao.transaction.infrastructure.config;

import mg.sakamalao.common.core.usecase.ProjectAccessCheckerUseCase;
import mg.sakamalao.transaction.core.repository.expense.ExpenseRepository;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;
import mg.sakamalao.transaction.core.usecase.UpdateTransactionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {
    @Bean
    public UpdateTransactionUseCase updateTransactionUseCase(
            ProjectAccessCheckerUseCase projectAccessCheckerUseCase,
            ExpenseRepository expenseRepository,
            IncomeRepository incomeRepository
    ) {
        return new UpdateTransactionUseCase(
                projectAccessCheckerUseCase,
                expenseRepository,
                incomeRepository
        );
    }
}
