package mg.sakamalao.cms.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.cms.core.domain.TransactionCategory;
import mg.sakamalao.cms.core.usecase.ListTransactionCategoriesUseCase;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cms")
@RequiredArgsConstructor
public class CmsController {

    private final ListTransactionCategoriesUseCase transactionCategories;

    @GetMapping("/project/{projectId}/transaction-categories")
    public ResponseEntity<List<TransactionCategory>> getTransactionsCategories(
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        var result = transactionCategories.findByProject(projectId, user.id());
        return ResponseEntity.ok(result);
    }
}
