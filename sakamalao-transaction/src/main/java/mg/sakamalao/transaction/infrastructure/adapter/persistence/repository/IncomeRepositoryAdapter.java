package mg.sakamalao.transaction.infrastructure.adapter.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.infrastructure.adapter.jpa.TransactionCategoryJpaRepository;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;
import mg.sakamalao.transaction.infrastructure.adapter.persistence.entity.IncomeDbEntity;
import mg.sakamalao.transaction.infrastructure.adapter.persistence.jpa.IncomeJpaRepository;
import mg.sakamalao.transaction.infrastructure.adapter.persistence.mapper.IncomeMapper;
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
    private final EntityManager entityManager;

    @Transactional
    @Override
    public Income save(Income income) {
        var category = transactionCategory.findById(income.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        var entity = IncomeMapper.mapToDbEntity(income, category);

        IncomeDbEntity saved = repository.save(entity);
        return IncomeMapper.mapToDomain(saved);
    }

    @Override
    @Transactional
    public void saveAll(List<Income> incomes) {

        int batchSize = 50;

        for (int i = 0; i < incomes.size(); i++) {
            Income income = incomes.get(i);
            var category = transactionCategory.findById(income.getCategory().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));

            IncomeDbEntity entity = IncomeMapper.mapToDbEntity(income, category);
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
    public Optional<Income> findById(UUID id) {
        return repository.findById(id).map(IncomeMapper::mapToDomain);
    }

    @Override
    public List<Income> findByProjectId(UUID projectId) {
        return repository.findByProjectId(projectId).stream()
                .map(IncomeMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
