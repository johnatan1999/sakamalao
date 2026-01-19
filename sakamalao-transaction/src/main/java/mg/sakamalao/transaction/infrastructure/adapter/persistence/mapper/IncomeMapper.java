package mg.sakamalao.transaction.infrastructure.adapter.persistence.mapper;

import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.infrastructure.adapter.entity.TransactionCategoryDbEntity;
import mg.sakamalao.transaction.infrastructure.adapter.persistence.entity.IncomeDbEntity;


public final class IncomeMapper {
    private IncomeMapper() {}

    public static Income mapToDomain(IncomeDbEntity entity) {
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
        income.setImportId(entity.getImportId());
        income.setAmount(entity.getAmount());
        income.setDate(entity.getDate());
        income.setCreatedDate(entity.getCreatedDate());
        income.setUpdatedDate(entity.getUpdatedDate());
        income.setCreatedByUserId(entity.getCreatedByUserId());
        income.setUpdatedByUserId(entity.getUpdatedByUserId());
        return income;
    }

    public static IncomeDbEntity mapToDbEntity(Income income, TransactionCategoryDbEntity category) {
        IncomeDbEntity entity = new IncomeDbEntity();

        entity.setId(income.getId());
        entity.setProjectId(income.getProjectId());
        entity.setName(income.getName());
        entity.setDescription(income.getDescription());
        entity.setCategory(category);

        entity.setAmount(income.getAmount());
        entity.setImportId(income.getImportId());
        entity.setDate(income.getDate());
        entity.setCreatedDate(income.getCreatedDate());
        entity.setUpdatedDate(income.getUpdatedDate());
        entity.setCreatedByUserId(income.getCreatedByUserId());
        entity.setUpdatedByUserId(income.getUpdatedByUserId());

        return entity;
    }

}
