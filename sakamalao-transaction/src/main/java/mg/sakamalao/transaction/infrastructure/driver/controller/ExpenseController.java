package mg.sakamalao.transaction.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.BaseController;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.transaction.core.domain.expense.ExpenseInput;
import mg.sakamalao.transaction.core.domain.expense.UpdateExpenseInput;
import mg.sakamalao.transaction.core.usecase.expense.CreateExpenseUseCase;
import mg.sakamalao.transaction.core.usecase.expense.DeleteExpenseUseCase;
import mg.sakamalao.transaction.core.usecase.expense.ListExpensesUseCase;
import mg.sakamalao.transaction.core.usecase.expense.UpdateExpenseUseCase;
import mg.sakamalao.transaction.infrastructure.driver.entity.UpdateExpenseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController extends BaseController {

    private final CreateExpenseUseCase createExpenseUseCase;
    private final ListExpensesUseCase listExpensesUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;

    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody ExpenseInput input, @CurrentUser User user) {
        var expense = createExpenseUseCase.create(input, user.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @GetMapping("/project/{projectId}")
    public List<Expense> listByProject(@PathVariable UUID projectId, @CurrentUser User user) {
        return listExpensesUseCase.findByProject((projectId), user.id());
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> update(
            @RequestBody UpdateExpenseRequest req,
            @PathVariable UUID expenseId,
            @CurrentUser User user
    ) {
        var expense = updateExpenseUseCase.update(new UpdateExpenseInput(
                expenseId,
                req.name(),
                req.description(),
                req.amount(),
                req.categoryId(),
                req.projectId(),
                req.date()
        ), user.id());
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @CurrentUser User user) {
        deleteExpenseUseCase.deleteExpense(id, user.id());
        return ResponseEntity.noContent().build();
    }

}
