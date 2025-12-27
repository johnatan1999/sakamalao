package mg.sakamalao.cms.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.cms.infrastructure.adapter.persistence.entity.TransactionCategoryDbEntity;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionCategoryJpaRepository extends JpaRepository<TransactionCategoryDbEntity, UUID> {
    List<TransactionCategoryDbEntity> findAllByTransactionType(TransactionType type);
    Optional<TransactionCategoryDbEntity> findByProjectIdAndNameAndTransactionType(UUID projectId, String name, TransactionType type);
}
