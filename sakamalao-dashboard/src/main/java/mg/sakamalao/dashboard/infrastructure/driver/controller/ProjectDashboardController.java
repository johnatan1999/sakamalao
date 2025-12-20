package mg.sakamalao.dashboard.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.dashboard.core.domain.*;
import mg.sakamalao.dashboard.core.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/dashboard")
@RequiredArgsConstructor
public class ProjectDashboardController {

    private final ExpenseBreakdownByCategoryUseCase expenseBreakdownByCategoryUseCase;
    private final IncomeBreakdownByCategoryUseCase incomeBreakdownByCategoryUseCase;
    private final ProjectHealthUseCase projectBudgetHealthUseCase;
    private final ProjectDashboardSummaryUseCase projectDashboardSummaryUseCase;
    private final ProjectMonthlyCashFlowUseCase projectMonthlyCashFlowUseCase;

    @GetMapping("/expenses-by-category")
    public ResponseEntity<List<ExpenseCategoryAmount>> expenseCategoryAmountResponseEntity(
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        var result = expenseBreakdownByCategoryUseCase.execute(projectId, user.id());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/incomes-by-category")
    public ResponseEntity<List<IncomeCategoryAmount>> incomeCategoryAmountResponseEntity(
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        var result = incomeBreakdownByCategoryUseCase.execute(projectId, user.id());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/summary")
    public ResponseEntity<ProjectDashboardSummary> projectSummary(
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        var result = projectDashboardSummaryUseCase.execute(projectId, user.id());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/project-health")
    public ResponseEntity<ProjectHealth> getProjectBudgetHealth(
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        var result = projectBudgetHealthUseCase.execute(projectId, user.id());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/monthly-cashflow")
    public ResponseEntity<List<MonthlyCashFlow>> getProjectMonthlyCashFlow(
            @PathVariable UUID projectId,
            @CurrentUser User user
    ) {
        var result = projectMonthlyCashFlowUseCase.execute(projectId, user.id());
        return ResponseEntity.ok(result);
    }
}
