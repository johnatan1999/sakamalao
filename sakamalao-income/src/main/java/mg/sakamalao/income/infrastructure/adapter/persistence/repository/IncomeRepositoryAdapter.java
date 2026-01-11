package mg.sakamalao.income.infrastructure.adapter.persistence.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.infrastructure.adapter.jpa.TransactionCategoryJpaRepository;
import mg.sakamalao.income.core.repository.IncomeRepository;
import mg.sakamalao.income.infrastructure.adapter.persistence.entity.IncomeDbEntity;
import mg.sakamalao.income.infrastructure.adapter.persistence.jpa.IncomeJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class IncomeRepositoryAdapter implements IncomeRepository {

    private final IncomeJpaRepository repository;
    private final TransactionCategoryJpaRepository transactionCategory;

    @Transactional
    @Override
    public Income save(Income income) {
        IncomeDbEntity entity = new IncomeDbEntity();
        entity.setId(income.getId());
        entity.setName(income.getName());
        entity.setAmount(income.getAmount());
        entity.setDate(income.getDate());
        entity.setCreatedDate(income.getCreatedDate());
        entity.setUpdatedDate(income.getUpdatedDate());

        var category = transactionCategory.findById(income.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        entity.setCategory(category);
        entity.setDescription(income.getDescription());
        entity.setProjectId(income.getProjectId());
        entity.setCreatedByUserId(income.getCreatedByUserId());
        entity.setUpdatedByUserId(income.getUpdatedByUserId());

        IncomeDbEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Income> findById(UUID id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Income> findByProjectId(UUID projectId) {
        return repository.findByProjectId(projectId).stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    private Income mapToDomain(IncomeDbEntity entity) {
        var income = new Income();
        income.setId(entity.getId());
        income.setProjectId(entity.getProjectId());
        income.setName(entity.getName());
        income.setDescription(entity.getDescription());
        income.setCategory(
                TransactionCategory.builder()
                        .id(entity.getCategory().getId())
                        .type(entity.getCategory().getTransactionType())
                        .name(entity.getCategory().getName())
                        .projectId(entity.getCategory().getProjectId())
                        .createdAt(entity.getCategory().getCreatedAt())
                        .build()
        );
        income.setAmount(entity.getAmount());
        income.setDate(entity.getDate());
        income.setCreatedDate(entity.getCreatedDate());
        income.setUpdatedDate(entity.getUpdatedDate());
        income.setCreatedByUserId(entity.getCreatedByUserId());
        income.setUpdatedByUserId(entity.getUpdatedByUserId());
        return income;
    }
}
