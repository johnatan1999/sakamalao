package mg.sakamalao.transaction.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.BaseController;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.transaction.core.application.UpdateTransactionApplicationService;
import mg.sakamalao.transaction.core.domain.income.IncomeInput;
import mg.sakamalao.transaction.core.domain.input.UpdateTransactionInput;
import mg.sakamalao.transaction.core.usecase.income.CreateIncomeUseCase;
import mg.sakamalao.transaction.core.usecase.income.DeleteIncomeUseCase;
import mg.sakamalao.transaction.core.usecase.income.FindIncomesUseCase;
import mg.sakamalao.transaction.infrastructure.driver.entity.UpdateIncomeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController extends BaseController {
    private final CreateIncomeUseCase createIncomeUseCase;
    private final UpdateTransactionApplicationService updateIncomeService;
    private final DeleteIncomeUseCase deleteIncomeUseCase;
    private final FindIncomesUseCase findIncomesUseCase;

    @PostMapping
    public ResponseEntity<Income> create(@RequestBody IncomeInput input, @CurrentUser User user) {
        var income = createIncomeUseCase.create(input, user.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(income);
    }

    @GetMapping("/project/{projectId}")
    public List<Income> listByProject(
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        return findIncomesUseCase.findByProject((projectId), user.id());
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<Void> update(
            @RequestBody UpdateIncomeRequest req,
            @PathVariable UUID incomeId,
            @CurrentUser User user
    ) {
        updateIncomeService.update(
                user.id(),
                incomeId,
                new UpdateTransactionInput(
                        req.projectId(),
                        req.name(),
                        req.description(),
                        req.type(),
                        TransactionCategory.builder()
                                .id(req.categoryId())
                                .build(),
                        req.amount(),
                        req.date()
                )
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @CurrentUser User user) {
        deleteIncomeUseCase.delete(id, user.id());
        return ResponseEntity.noContent().build();
    }
}
