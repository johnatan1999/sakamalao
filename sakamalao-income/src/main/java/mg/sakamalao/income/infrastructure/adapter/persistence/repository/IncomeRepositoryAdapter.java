package mg.sakamalao.income.infrastructure.adapter.persistence.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Income;
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
        entity.setCategory(income.getCategory());
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
        income.setCategory(entity.getCategory());
        income.setAmount(entity.getAmount());
        income.setDate(entity.getDate());
        income.setCreatedDate(entity.getCreatedDate());
        income.setUpdatedDate(entity.getUpdatedDate());
        income.setCreatedByUserId(entity.getCreatedByUserId());
        income.setUpdatedByUserId(entity.getUpdatedByUserId());
        return income;
    }
}
