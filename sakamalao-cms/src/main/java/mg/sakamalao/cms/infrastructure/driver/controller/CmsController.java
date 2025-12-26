package mg.sakamalao.cms.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.cms.core.domain.TransactionCategory;
import mg.sakamalao.cms.core.domain.input.TransactionCategoryInput;
import mg.sakamalao.cms.core.usecase.CreateTransactionCategoryUseCase;
import mg.sakamalao.cms.core.usecase.DeleteTransactionCategoryUseCase;
import mg.sakamalao.cms.core.usecase.ListTransactionCategoriesUseCase;
import mg.sakamalao.cms.core.usecase.UpdateTransactionCategoryUseCase;
import mg.sakamalao.cms.infrastructure.driver.entity.response.ListTransactionCategoryResponse;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cms")
@RequiredArgsConstructor
public class CmsController {

    private final ListTransactionCategoriesUseCase transactionCategories;
    private final CreateTransactionCategoryUseCase createTransactionCategory;
    private final UpdateTransactionCategoryUseCase updateTransactionCategory;
    private final DeleteTransactionCategoryUseCase deleteTransactionCategory;

    @PostMapping("/project/{projectId}/transaction-categories")
    public ResponseEntity<TransactionCategory> create(
            @RequestBody TransactionCategoryInput input,
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        var result = createTransactionCategory.create(
                user.id(),
                projectId,
                input
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/project/{projectId}/transaction-categories/{categoryId}")
    public ResponseEntity<TransactionCategory> update(
            @RequestBody TransactionCategoryInput input,
            @PathVariable UUID projectId,
            @PathVariable UUID categoryId,
            @CurrentUser User user
    ) {
        var result = updateTransactionCategory.update(
                user.id(),
                projectId,
                categoryId,
                input
        );
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/project/{projectId}/transaction-categories/{categoryId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID projectId,
            @PathVariable UUID categoryId,
            @CurrentUser User user
    ) {
        deleteTransactionCategory.delete(
                user.id(),
                projectId,
                categoryId
        );
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}/transaction-categories")
    public ResponseEntity<ListTransactionCategoryResponse> getTransactionsCategories(
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        var result = transactionCategories.findByProject(projectId, user.id());
        return ResponseEntity.ok(new ListTransactionCategoryResponse(
                result.get(TransactionType.INCOME),
                result.get(TransactionType.EXPENSE)
        ));
    }
}
