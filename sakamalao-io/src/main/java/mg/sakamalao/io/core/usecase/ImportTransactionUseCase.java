package mg.sakamalao.io.core.usecase;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.enums.ExpenseCategory;
import mg.sakamalao.common.core.domain.enums.IncomeCategory;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.expense.core.repository.ExpenseRepository;
import mg.sakamalao.income.core.repository.IncomeRepository;
import mg.sakamalao.io.core.domain.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ImportTransactionUseCase {
    private final ProjectAccessPort projectAccessPort;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public ImportTransactionUseCase(
            ProjectAccessPort projectAccessPort,
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository
    ) {
        this.projectAccessPort = projectAccessPort;
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    public void importTransactions(UUID userId, UUID projectId, List<Transaction> transactions) {
        var hasAccess = projectAccessPort.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        List<Income> incomes = transactions.stream()
                .filter(t -> t.getType().equals(TransactionType.INCOME))
                .map(t -> (Income) Income.builder()
                        .category(IncomeCategory.valueOf(t.getCategory()))
                        .name(t.getName())
                        .date(t.getDate())
                        .description(t.getDescription())
                        .amount(t.getAmount())
                        .projectId(projectId)
                        .createdByUserId(userId)
                        .createdDate(t.getDate() != null ? t.getDate() : LocalDate.now())
                        .build()
                ).toList();
        List<Expense> expenses = transactions.stream()
                .filter(t -> t.getType().equals(TransactionType.EXPENSE))
                .map(t -> (Expense) Expense.builder()
                        .category(ExpenseCategory.valueOf(t.getCategory()))
                        .name(t.getName())
                        .date(t.getDate())
                        .description(t.getDescription())
                        .amount(t.getAmount())
                        .projectId(projectId)
                        .createdByUserId(userId)
                        .createdDate(t.getDate() != null ? t.getDate() : LocalDate.now())
                        .build()
                ).toList();
        for (var income : incomes) {
            incomeRepository.save(income);
        }
        for (var expense : expenses) {
            expenseRepository.save(expense);
        }
    }
}
