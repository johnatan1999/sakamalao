package mg.sakamalao.expense.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.expense.core.repository.ExpenseRepository;
import mg.sakamalao.expense.infrastructure.adapter.persistence.entity.ExpenseDbEntity;
import mg.sakamalao.expense.infrastructure.adapter.persistence.jpa.ExpenseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExpenseRepositoryAdapter implements ExpenseRepository {

    private final ExpenseJpaRepository repository;

    @Override
    public Expense save(Expense expense) {
        ExpenseDbEntity entity = new ExpenseDbEntity();
        entity.setId(expense.getId());
        entity.setName(expense.getName());
        entity.setAmount(expense.getAmount());
        entity.setDate(expense.getDate());
        entity.setCreatedDate(expense.getCreatedDate());
        entity.setUpdatedDate(expense.getUpdatedDate());
        entity.setCategory(expense.getCategory());
        entity.setDescription(expense.getDescription());
        entity.setProjectId(expense.getProjectId());
        entity.setCreatedByUserId(expense.getCreatedByUserId());
        entity.setUpdatedByUserId(expense.getUpdatedByUserId());

        ExpenseDbEntity saved = repository.save(entity);
        return mapToDomain(saved);
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

    private Expense mapToDomain(ExpenseDbEntity entity) {
        return new Expense(
                entity.getId(),
                entity.getProjectId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCategory(),
                entity.getAmount(),
                entity.getDate(),
                entity.getCreatedDate(),
                entity.getUpdatedDate(),
                entity.getCreatedByUserId(),
                entity.getUpdatedByUserId()
        );
    }
}
