package mg.sakamalao.cms.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.cms.infrastructure.adapter.persistence.mapper.TransactionCategoryMapper;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.common.infrastructure.adapter.entity.TransactionCategoryDbEntity;
import mg.sakamalao.common.infrastructure.adapter.jpa.TransactionCategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class TransactionCategoryRepositoryAdapter implements TransactionCategoryRepository {

    private final TransactionCategoryJpaRepository repository;

    @Override
    public TransactionCategory save(TransactionCategory input) {
        var entity = new TransactionCategoryDbEntity();
        entity.setId(input.getId());
        entity.setName(input.getName());
        entity.setCreatedAt(input.getCreatedAt());
        entity.setTransactionType(input.getType());
        entity.setProjectId(input.getProjectId());
        var newEntity = repository.save(entity);
        return TransactionCategoryMapper.fromDbEntity(newEntity);
    }

    @Override
    public Optional<TransactionCategory> findById(UUID id) {
        return repository.findById(id).map(TransactionCategoryMapper::fromDbEntity);
    }

    @Override
    public Optional<TransactionCategory> findByProjectIdAndNameAndType(UUID projectId, String name, TransactionType type) {
        return repository.findByProjectIdAndNameAndTransactionType(projectId, name, type).map(TransactionCategoryMapper::fromDbEntity);
    }

    @Override
    public Map<TransactionType, List<TransactionCategory>> getTransactionCategories(UUID projectId) {
        var incomes = repository.findAllByProjectIdAndTransactionType(projectId, TransactionType.INCOME)
                .stream().map(TransactionCategoryMapper::fromDbEntity).toList();
        var expenses = repository.findAllByProjectIdAndTransactionType(projectId, TransactionType.EXPENSE)
                .stream().map(TransactionCategoryMapper::fromDbEntity).toList();
        var result = new HashMap<TransactionType, List<TransactionCategory>>();
        result.put(TransactionType.INCOME, incomes);
        result.put(TransactionType.EXPENSE, expenses);
        return result;
    }

    @Override
    public void deleteById(UUID categoryId) {
        repository.deleteById(categoryId);
    }
}
