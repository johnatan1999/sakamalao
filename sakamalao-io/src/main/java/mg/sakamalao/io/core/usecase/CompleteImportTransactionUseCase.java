package mg.sakamalao.io.core.usecase;

import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.expense.core.repository.ExpenseRepository;
import mg.sakamalao.income.core.repository.IncomeRepository;
import mg.sakamalao.io.core.domain.input.MappedTransactionInput;
import mg.sakamalao.io.core.repository.ImportTransactionRowRepository;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CompleteImportTransactionUseCase {
    private final ProjectAccessPort projectAccessPort;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final ImportTransactionRowRepository transactionRowRepository;
    private final TransactionCategoryRepository categoryRepository;

    public CompleteImportTransactionUseCase(
            ProjectAccessPort projectAccessPort,
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            ImportTransactionRowRepository transactionRowRepository,
            TransactionCategoryRepository categoryRepository
    ) {
        this.projectAccessPort = projectAccessPort;
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.transactionRowRepository = transactionRowRepository;
    }

    public void completeTransactionsImport(UUID userId, UUID projectId, List<MappedTransactionInput> transactions) {
        var hasAccess = projectAccessPort.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

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
                            .createdByUserId(userId)
                            .date(t.getDate() != null ? t.getDate().toLocalDate() : LocalDate.now())
                            .createdDate(LocalDate.now())
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
                            .date(t.getDate() != null ? t.getDate().toLocalDate() : LocalDate.now())
                            .createdDate(LocalDate.now())
                            .build();
                }).toList();

        incomeRepository.saveAll(incomes);
        expenseRepository.saveAll(expenses);
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

}
