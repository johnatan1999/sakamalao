package mg.sakamalao.cms.core.repository;

import mg.sakamalao.cms.core.domain.TransactionCategory;
import mg.sakamalao.common.core.domain.enums.TransactionType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface TransactionCategoryRepository {
    TransactionCategory save(TransactionCategory input);
    Optional<TransactionCategory> findById(UUID uuid);
    Optional<TransactionCategory> findByProjectIdAndName(UUID projectId, String name);
    Map<TransactionType, List<TransactionCategory>> getTransactionCategories(UUID projectId);

    void deleteById(UUID categoryId);
}
