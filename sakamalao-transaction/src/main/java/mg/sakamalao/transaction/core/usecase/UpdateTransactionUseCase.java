package mg.sakamalao.transaction.core.usecase;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.usecase.ProjectAccessCheckerUseCase;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.transaction.core.domain.input.UpdateTransactionInput;
import mg.sakamalao.transaction.core.repository.expense.ExpenseRepository;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateTransactionUseCase {
    private final ProjectAccessCheckerUseCase projectAccessChecker;
    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    public UpdateTransactionUseCase(
            ProjectAccessCheckerUseCase projectAccessCheckerUseCase,
            ExpenseRepository expenseRepository,
            IncomeRepository incomeRepository
    ) {
        this.projectAccessChecker = projectAccessCheckerUseCase;
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    /**
     * Handles updating a transaction, including the possibility of changing its type.
     *
     * If the type remains the same, the existing entity is updated.
     * If the type changes (Income ↔ Expense), a new entity of the target type is created,
     * the old entity is deleted, and audit fields are preserved.
     *
     * @param userId        ID of the user performing the update
     * @param transactionId ID of the transaction to update
     * @param input         Input data containing updated transaction fields and type
     * @throws EntityNotFoundException if the transaction does not exist
     */
    public void update(
            UUID userId,
            UUID transactionId,
            UpdateTransactionInput input
    ) {
        // Validate inputs
        FieldValidator.notNull("input", input);
        FieldValidator.notNull("userId", userId);
        FieldValidator.notNull("projectId", input.projectId());
        UUID projectId = input.projectId();
        projectAccessChecker.checkAccess(userId, input.projectId());

        // Try to find the transaction as Expense first, then Income
        Expense expense = expenseRepository.findById(transactionId).orElse(null);
        Income income = null;
        boolean isExpense = true;

        if (expense == null) {
            isExpense = false;
            income = incomeRepository.findById(transactionId)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Transaction with id=%s not found".formatted(transactionId)
                    ));
        }

        // Decide whether type has changed
        boolean typeChanged = (isExpense && input.type() == TransactionType.INCOME)
                || (!isExpense && input.type() == TransactionType.EXPENSE);

        if (typeChanged) {
            // Handle type change: create new entity of the target type, delete old
            if (isExpense) {
                migrateExpenseToIncome(expense, input, userId, projectId);
            } else {
                migrateIncomeToExpense(income, input, userId, projectId);
            }
        } else {
            // Update fields of the same type
            if (isExpense) {
                updateExpense(expense, input, userId);
            } else {
                updateIncome(income, input, userId);
            }
        }
    }

    private void updateExpense(Expense expense, UpdateTransactionInput input, UUID userId) {
        expense.setName(input.name());
        expense.setDescription(input.description());
        expense.setAmount(input.amount());
        expense.setCategory(input.category());
        expense.setDate(input.date());
        expense.setUpdatedByUserId(userId);
        expense.setUpdatedDate(LocalDateTime.now());
        expenseRepository.save(expense);
    }

    private void updateIncome(Income income, UpdateTransactionInput input, UUID userId) {
        income.setName(input.name());
        income.setDescription(input.description());
        income.setAmount(input.amount());
        income.setCategory(input.category());
        income.setDate(input.date());
        income.setUpdatedByUserId(userId);
        income.setUpdatedDate(LocalDateTime.now());
        incomeRepository.save(income);
    }

    private void migrateExpenseToIncome(Expense expense, UpdateTransactionInput input, UUID userId, UUID projectId) {
        Income newIncome = Income.builder()
                .createdByUserId(expense.getCreatedByUserId())
                .createdDate(expense.getCreatedDate())
                .updatedByUserId(userId)
                .updatedDate(LocalDateTime.now())
                .category(input.category())
                .amount(input.amount())
                .projectId(projectId)
                .name(input.name())
                .description(input.description())
                .importId(expense.getImportId())
                .date(input.date())
                .build();

        incomeRepository.save(newIncome);
        expenseRepository.deleteById(expense.getId());
    }

    private void migrateIncomeToExpense(Income income, UpdateTransactionInput input, UUID userId, UUID projectId) {
        Expense newExpense = Expense.builder()
                .createdByUserId(income.getCreatedByUserId())
                .createdDate(income.getCreatedDate())
                .updatedByUserId(userId)
                .updatedDate(LocalDateTime.now())
                .category(input.category())
                .amount(input.amount())
                .projectId(projectId)
                .name(input.name())
                .description(input.description())
                .importId(income.getImportId())
                .date(input.date())
                .build();

        expenseRepository.save(newExpense);
        incomeRepository.deleteById(income.getId());
    }
}
