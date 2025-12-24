package mg.sakamalao.io.infrastructure.config;

import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.expense.core.repository.ExpenseRepository;
import mg.sakamalao.income.core.repository.IncomeRepository;
import mg.sakamalao.io.core.usecase.ImportTransactionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOConfig {

    @Bean
    public ImportTransactionUseCase importTransactionUseCase(
            ProjectAccessPort projectAccessPort,
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository
    ) {
        return new ImportTransactionUseCase(
                projectAccessPort,
                incomeRepository,
                expenseRepository
        );
    }
}
