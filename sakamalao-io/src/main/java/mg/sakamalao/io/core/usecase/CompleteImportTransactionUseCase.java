package mg.sakamalao.io.core.usecase;

import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.usecase.ProjectAccessCheckerUseCase;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.io.core.domain.ImportStatus;
import mg.sakamalao.io.core.domain.input.MappedTransactionInput;
import mg.sakamalao.io.core.repository.ImportSessionRepository;
import mg.sakamalao.io.core.repository.ImportTransactionRowRepository;
import mg.sakamalao.transaction.core.repository.expense.ExpenseRepository;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CompleteImportTransactionUseCase {
    private final ProjectAccessCheckerUseCase projectAccess;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final ImportSessionRepository importSessionRepository;
    private final ImportTransactionRowRepository importTransactionRepository;
    private final TransactionCategoryRepository categoryRepository;

    public CompleteImportTransactionUseCase(
            ProjectAccessCheckerUseCase projectAccess,
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            ImportSessionRepository importSessionRepository,
            ImportTransactionRowRepository importTransactionRowRepository,
            TransactionCategoryRepository categoryRepository
    ) {
        this.projectAccess = projectAccess;
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.importSessionRepository = importSessionRepository;
        this.importTransactionRepository = importTransactionRowRepository;
    }

    public void completeTransactionsImport(
            UUID userId,
            UUID projectId,
            UUID sessionId,
            List<MappedTransactionInput> transactions
    ) {
        // Validation
        FieldValidator.notNull("userId", userId);
        FieldValidator.notNull("projectId", projectId);
        FieldValidator.notNull("sessionId", sessionId);
        projectAccess.checkAccess(userId, projectId);
        this.validateTransactions(sessionId, transactions);

        // Processing
        Map<String, TransactionCategory> categoryCache = new HashMap<>();

        List<Income> incomes = transactions.stream()
                .filter(t -> t.getType().equals(TransactionType.INCOME))
                .map(t -> {
                    var category = findOrCreateCategory(projectId, t.getCategory(), t.getType(), categoryCache);
                    return (Income) Income.builder()
                            .name(t.getName())
                            .description(t.getDescription())
                            .category(category)
                            .amount(t.getAmount())
                            .projectId(projectId)
                            .importId(t.getId())
                            .createdByUserId(userId)
                            .date(t.getDate() != null ? t.getDate() : LocalDateTime.now())
                            .createdDate(LocalDateTime.now())
                            .build();
                    }
                ).toList();

        List<Expense> expenses = transactions.stream()
                .filter(t -> t.getType().equals(TransactionType.EXPENSE))
                .map(t -> {
                    var category = findOrCreateCategory(projectId, t.getCategory(), t.getType(), categoryCache);
                    return (Expense) Expense.builder()
                            .name(t.getName())
                            .description(t.getDescription())
                            .amount(t.getAmount())
                            .category(category)
                            .projectId(projectId)
                            .createdByUserId(userId)
                            .importId(t.getId())
                            .date(t.getDate() != null ? t.getDate() : LocalDateTime.now())
                            .createdDate(LocalDateTime.now())
                            .build();
                }).toList();

        // Persist
        incomeRepository.saveAll(incomes);
        expenseRepository.saveAll(expenses);
        endImportSession(sessionId);
    }

    private void validateTransactions(UUID sessionId, List<MappedTransactionInput> transactions) {
        Set<UUID> ids = transactions.stream()
                .map(MappedTransactionInput::getId)
                .collect(Collectors.toSet());

        int allBelongToSession =
                importTransactionRepository
                        .countByIdsAndSessionId(ids, sessionId);

        if (!(allBelongToSession == transactions.size())) {
            throw new EntityNotFoundException("Some transactions do not exist for the session");
        }

    }

    private String categoryKey(UUID projectId, String name, TransactionType type) {
        return projectId + ":" + type + ":" + Normalizer
                .normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .replaceAll("[^a-z0-9]", "");
    }

    private TransactionCategory findOrCreateCategory(
            UUID projectId,
            String name,
            TransactionType type,
            Map<String, TransactionCategory> cache
    ) {
        String key = categoryKey(projectId, name, type);

        return cache.computeIfAbsent(key, k ->
                categoryRepository
                        .findByProjectIdAndNameAndType(projectId, name, type)
                        .orElseGet(() ->
                                categoryRepository.save(
                                        TransactionCategory.builder()
                                                .createdAt(LocalDateTime.now())
                                                .type(type)
                                                .name(name)
                                                .projectId(projectId)
                                                .build()
                                )
                        )
        );
    }

    private void endImportSession(UUID sessionId) {
        importSessionRepository.updateStatus(sessionId, ImportStatus.COMPLETED);
    }
}
