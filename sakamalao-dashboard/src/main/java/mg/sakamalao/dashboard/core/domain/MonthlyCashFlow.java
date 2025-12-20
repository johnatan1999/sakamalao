package mg.sakamalao.dashboard.core.domain;

import java.time.YearMonth;

public record MonthlyCashFlow(
        YearMonth month,
        double income,
        double expense
) {
}
