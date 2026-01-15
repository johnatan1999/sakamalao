package mg.sakamalao.io.infrastructure.config;

import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.expense.core.repository.ExpenseRepository;
import mg.sakamalao.income.core.repository.IncomeRepository;
import mg.sakamalao.io.core.repository.ImportSessionRepository;
import mg.sakamalao.io.core.repository.ImportTransactionRowRepository;
import mg.sakamalao.io.core.usecase.CreateImportSessionUseCase;
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

    @Bean
    public CreateImportSessionUseCase importSessionUseCase(
            ProjectAccessPort projectAccess,
            ImportSessionRepository importSessionRepository,
            ImportTransactionRowRepository importTransactionRowRepository
    ) {
        return new CreateImportSessionUseCase(
                projectAccess,
                importSessionRepository,
                importTransactionRowRepository
        );
    }
}
