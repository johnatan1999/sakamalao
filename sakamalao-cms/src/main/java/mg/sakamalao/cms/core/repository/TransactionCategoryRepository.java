package mg.sakamalao.cms.core.repository;

import mg.sakamalao.cms.core.domain.TransactionCategory;

import java.util.List;
import java.util.UUID;

public interface TransactionCategoryRepository {
    List<TransactionCategory> getTransactionCategories(UUID projectId);
}
