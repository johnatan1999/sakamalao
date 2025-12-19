package mg.sakamalao.income.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.BaseController;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.income.core.domain.IncomeInput;
import mg.sakamalao.income.core.domain.UpdateIncomeInput;
import mg.sakamalao.income.core.usecase.CreateIncomeUseCase;
import mg.sakamalao.income.core.usecase.DeleteIncomeUseCase;
import mg.sakamalao.income.core.usecase.FindIncomeUseCase;
import mg.sakamalao.income.core.usecase.UpdateIncomeUseCase;
import mg.sakamalao.income.infrastructure.driver.entity.UpdateIncomeRequest;
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
    private final UpdateIncomeUseCase updateIncomeUseCase;
    private final DeleteIncomeUseCase deleteIncomeUseCase;
    private final FindIncomeUseCase findIncomeUseCase;

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
        return findIncomeUseCase.findByProject((projectId), user.id());
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<Income> update(
            @RequestBody UpdateIncomeRequest req,
            @PathVariable UUID incomeId,
            @CurrentUser User user
    ) {
        var income = updateIncomeUseCase.update(new UpdateIncomeInput(
                incomeId,
                req.name(),
                req.description(),
                req.amount(),
                req.category(),
                req.projectId(),
                req.date()
        ), user.id());
        return ResponseEntity.ok(income);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @CurrentUser User user) {
        deleteIncomeUseCase.delete(id, user.id());
        return ResponseEntity.noContent().build();
    }
}
