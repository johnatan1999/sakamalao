package mg.sakamalao.io.infrastructure.config;

import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.core.usecase.ProjectAccessCheckerUseCase;
import mg.sakamalao.expense.core.repository.ExpenseRepository;
import mg.sakamalao.income.core.repository.IncomeRepository;
import mg.sakamalao.io.core.repository.ImportSessionRepository;
import mg.sakamalao.io.core.repository.ImportTransactionRowRepository;
import mg.sakamalao.io.core.usecase.CompleteImportTransactionUseCase;
import mg.sakamalao.io.core.usecase.CreateImportSessionUseCase;
import mg.sakamalao.io.core.usecase.GetImportSessionListUseCase;
import mg.sakamalao.io.core.usecase.GetImportSessionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOConfig {

    @Bean
    public GetImportSessionListUseCase importSessionListUseCase(
            ImportSessionRepository importSessionRepository,
            ProjectAccessCheckerUseCase projectAccessCheckerUseCase
    ) {
        return new GetImportSessionListUseCase(
                projectAccessCheckerUseCase,
                importSessionRepository
        );
    }

    @Bean
    public CompleteImportTransactionUseCase importTransactionUseCase(
            ProjectAccessCheckerUseCase projectAccessChecker,
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            ImportSessionRepository importSessionRepository,
            ImportTransactionRowRepository importTransactionRowRepository,
            TransactionCategoryRepository categoryRepository
    ) {
        return new CompleteImportTransactionUseCase(
                projectAccessChecker,
                incomeRepository,
                expenseRepository,
                importSessionRepository,
                importTransactionRowRepository,
                categoryRepository
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

    @Bean
    public GetImportSessionUseCase getImportSessionUseCase(
            ProjectAccessPort projectAccess,
            ImportTransactionRowRepository importTransactionRowRepository
    ) {
        return new GetImportSessionUseCase(
                projectAccess,
                importTransactionRowRepository
        );
    }
}
