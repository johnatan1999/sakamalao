package mg.sakamalao.expense.infrastructure.adapter.persistence.mapper;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.infrastructure.adapter.entity.TransactionCategoryDbEntity;
import mg.sakamalao.expense.infrastructure.adapter.persistence.entity.ExpenseDbEntity;

public final class ExpenseMapper {
    private ExpenseMapper() {}
    public static Expense mapToDomain(ExpenseDbEntity entity) {
        var expense = new Expense();
        expense.setId(entity.getId());
        expense.setProjectId(entity.getProjectId());
        expense.setName(entity.getName());
        expense.setDescription(entity.getDescription());
        expense.setCategory(
                TransactionCategory.builder()
                        .id(entity.getCategory().getId())
                        .type(entity.getCategory().getTransactionType())
                        .name(entity.getCategory().getName())
                        .projectId(entity.getCategory().getProjectId())
                        .createdAt(entity.getCategory().getCreatedAt())
                        .build()
        );
        expense.setImportId(entity.getImportId());
        expense.setAmount(entity.getAmount());
        expense.setDate(entity.getDate());
        expense.setCreatedDate(entity.getCreatedDate());
        expense.setUpdatedDate(entity.getUpdatedDate());
        expense.setCreatedByUserId(entity.getCreatedByUserId());
        expense.setUpdatedByUserId(entity.getUpdatedByUserId());
        return expense;
    }

    public static ExpenseDbEntity mapToDbEntity(Expense expense, TransactionCategoryDbEntity category) {
        ExpenseDbEntity entity = new ExpenseDbEntity();

        entity.setId(expense.getId());
        entity.setProjectId(expense.getProjectId());
        entity.setName(expense.getName());
        entity.setDescription(expense.getDescription());
        entity.setCategory(category);

        entity.setImportId(expense.getImportId());
        entity.setAmount(expense.getAmount());
        entity.setDate(expense.getDate());
        entity.setCreatedDate(expense.getCreatedDate());
        entity.setUpdatedDate(expense.getUpdatedDate());
        entity.setCreatedByUserId(expense.getCreatedByUserId());
        entity.setUpdatedByUserId(expense.getUpdatedByUserId());

        return entity;
    }
}
