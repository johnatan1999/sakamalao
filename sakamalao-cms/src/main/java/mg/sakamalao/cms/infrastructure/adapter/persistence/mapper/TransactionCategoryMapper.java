package mg.sakamalao.cms.infrastructure.adapter.persistence.mapper;

import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.infrastructure.adapter.entity.TransactionCategoryDbEntity;

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
