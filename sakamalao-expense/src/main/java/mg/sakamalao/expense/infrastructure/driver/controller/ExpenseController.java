package mg.sakamalao.expense.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.BaseController;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.expense.core.domain.ExpenseInput;
import mg.sakamalao.expense.core.usecase.CreateExpenseUseCase;
import mg.sakamalao.expense.core.usecase.ListExpensesUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController extends BaseController {

    private final CreateExpenseUseCase createExpenseUseCase;
    private final ListExpensesUseCase listExpensesUseCase;

    @PostMapping
    public Expense create(@RequestBody ExpenseInput input, @CurrentUser User user) {
        return createExpenseUseCase.create(input, user.id());
    }

    @GetMapping("/project/{projectId}")
    public List<Expense> listByProject(@PathVariable UUID projectId, @CurrentUser User user) {
        return listExpensesUseCase.findByProject((projectId), user.id());
    }

}
