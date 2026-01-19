package mg.sakamalao.transaction.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.transaction.infrastructure.adapter.persistence.entity.ExpenseDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseJpaRepository extends JpaRepository<ExpenseDbEntity, UUID> {
    List<ExpenseDbEntity> findByProjectId(UUID projectId);
}
