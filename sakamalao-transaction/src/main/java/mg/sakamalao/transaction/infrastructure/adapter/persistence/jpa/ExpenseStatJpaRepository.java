package mg.sakamalao.transaction.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.common.infrastructure.adapter.entity.ExpenseDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseStatJpaRepository extends JpaRepository<ExpenseDbEntity, UUID> {
    // 🔹 Total Expense by project
    @Query("""
        SELECT COALESCE(SUM(i.amount), 0)
        FROM ExpenseDbEntity i
        WHERE i.projectId = :projectId
    """)
    double sumByProject(@Param("projectId") UUID projectId);

    // 🔹 Total Expense by project and period
    @Query("""
        SELECT COALESCE(SUM(i.amount), 0)
        FROM ExpenseDbEntity i
        WHERE i.projectId = :projectId
          AND i.date BETWEEN :start AND :end
    """)
    double sumByProjectAndPeriod(
            UUID projectId,
            LocalDate start,
            LocalDate end
    );

    // 🔹 Monthly Expense (YYYY-MM)
    @Query("""
        SELECT
            YEAR(i.date),
            MONTH(i.date),
            COALESCE(SUM(i.amount), 0)
        FROM ExpenseDbEntity i
        WHERE i.projectId = :projectId
        GROUP BY YEAR(i.date), MONTH(i.date)
        ORDER BY YEAR(i.date), MONTH(i.date)
    """)
    List<Object[]> sumGroupedByMonth(@Param("projectId") UUID projectId);

    // 🔹 Expense by category
    @Query("""
        SELECT i.category, COALESCE(SUM(i.amount), 0)
        FROM ExpenseDbEntity i
        WHERE i.projectId = :projectId
        GROUP BY i.category
    """)
    List<Object[]> sumByCategory(@Param("projectId") UUID projectId);
}
