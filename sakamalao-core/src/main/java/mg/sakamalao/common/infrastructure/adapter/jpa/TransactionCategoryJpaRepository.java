package mg.sakamalao.common.infrastructure.adapter.jpa;

import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.common.infrastructure.adapter.entity.TransactionCategoryDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionCategoryJpaRepository extends JpaRepository<TransactionCategoryDbEntity, UUID> {
    List<TransactionCategoryDbEntity> findAllByProjectIdAndTransactionType(UUID projectId, TransactionType type);
    Optional<TransactionCategoryDbEntity> findByProjectIdAndNameAndTransactionType(UUID projectId, String name, TransactionType type);
}
