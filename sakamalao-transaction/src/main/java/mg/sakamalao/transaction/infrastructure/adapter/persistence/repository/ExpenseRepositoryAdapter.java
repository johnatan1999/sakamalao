package mg.sakamalao.transaction.infrastructure.adapter.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.infrastructure.adapter.entity.ExpenseDbEntity;
import mg.sakamalao.common.infrastructure.adapter.jpa.TransactionCategoryJpaRepository;
import mg.sakamalao.transaction.core.repository.expense.ExpenseRepository;
import mg.sakamalao.transaction.infrastructure.adapter.persistence.jpa.ExpenseJpaRepository;
import mg.sakamalao.transaction.infrastructure.adapter.persistence.mapper.ExpenseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExpenseRepositoryAdapter implements ExpenseRepository {

    private final ExpenseJpaRepository repository;
    private final TransactionCategoryJpaRepository transactionCategory;
    private final EntityManager entityManager;

    @Override
    public Expense save(Expense expense) {
        ExpenseDbEntity entity = new ExpenseDbEntity();
        entity.setId(expense.getId());
        entity.setName(expense.getName());
        entity.setAmount(expense.getAmount());
        entity.setDate(expense.getDate());
        entity.setCreatedDate(expense.getCreatedDate());
        entity.setUpdatedDate(expense.getUpdatedDate());

        var category = transactionCategory.findById(expense.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        entity.setCategory(category);
        entity.setDescription(expense.getDescription());
        entity.setProjectId(expense.getProjectId());
        entity.setCreatedByUserId(expense.getCreatedByUserId());
        entity.setUpdatedByUserId(expense.getUpdatedByUserId());

        ExpenseDbEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    @Transactional
    public void saveAll(List<Expense> incomes) {

        int batchSize = 50;

        for (int i = 0; i < incomes.size(); i++) {
            Expense expense = incomes.get(i);
            var category = transactionCategory.findById(expense.getCategory().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));

            ExpenseDbEntity entity = ExpenseMapper.mapToDbEntity(expense, category);
            entityManager.persist(entity);

            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public Optional<Expense> findById(UUID id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Expense> findByProjectId(UUID projectId) {
        return repository.findByProjectId(projectId).stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    private Expense mapToDomain(ExpenseDbEntity e) {
        if (e == null) {
            return null;
        }
        return Expense.builder()
                .id(e.getId())
                .projectId(e.getProjectId())
                .name(e.getName())
                .description(e.getDescription())
                .category(
                        TransactionCategory.builder()
                                .id(e.getCategory().getId())
                                .type(e.getCategory().getTransactionType())
                                .name(e.getCategory().getName())
                                .projectId(e.getCategory().getProjectId())
                                .createdAt(e.getCategory().getCreatedAt())
                                .build()
                )
                .amount(e.getAmount())
                .date(e.getDate())
                .createdDate(e.getCreatedDate())
                .createdByUserId(e.getCreatedByUserId())
                .updatedDate(e.getUpdatedDate())
                .updatedByUserId(e.getUpdatedByUserId())
                .build();
    }
}
