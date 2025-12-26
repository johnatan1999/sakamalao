package mg.sakamalao.cms.infrastructure.adapter.persistence.mapper;

import mg.sakamalao.cms.core.domain.TransactionCategory;
import mg.sakamalao.cms.infrastructure.adapter.persistence.entity.TransactionCategoryDbEntity;

public final class TransactionCategoryMapper {

    private TransactionCategoryMapper() {}

    public static TransactionCategory fromDbEntity(TransactionCategoryDbEntity e) {
        return new TransactionCategory(
                e.getId(),
                e.getProjectId(),
                e.getName(),
                e.getTransactionType(),
                e.getCreatedAt()
        );
    }
}
