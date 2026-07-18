package mg.sakamalao.dashboard.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.dashboard.core.domain.PortfolioSummary;
import mg.sakamalao.dashboard.core.usecase.PortfolioSummaryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard/portfolio")
@RequiredArgsConstructor
public class PortfolioDashboardController {

    private final PortfolioSummaryUseCase portfolioSummaryUseCase;

    @GetMapping("/summary")
    public ResponseEntity<PortfolioSummary> portfolioSummary(
            @CurrentUser User user
    ) {
        var result = portfolioSummaryUseCase.execute(user.id());
        return ResponseEntity.ok(result);
    }
}
