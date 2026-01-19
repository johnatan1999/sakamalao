package mg.sakamalao.transaction.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.transaction.infrastructure.adapter.persistence.entity.IncomeDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IncomeJpaRepository extends JpaRepository<IncomeDbEntity, UUID> {
    List<IncomeDbEntity> findByProjectId(UUID projectId);
}
