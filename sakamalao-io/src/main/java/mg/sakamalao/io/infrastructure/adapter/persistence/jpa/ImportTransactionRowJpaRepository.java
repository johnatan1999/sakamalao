package mg.sakamalao.io.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.io.infrastructure.adapter.persistence.entity.ImportTransactionRowDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImportTransactionRowJpaRepository extends JpaRepository<ImportTransactionRowDbEntity, UUID> {
    @Query("""
      SELECT r 
      FROM ImportTransactionRowDbEntity r
      WHERE r.session.id = :sessionId
    """)
    List<ImportTransactionRowDbEntity> findAllBySessionId(UUID sessionId);
}
