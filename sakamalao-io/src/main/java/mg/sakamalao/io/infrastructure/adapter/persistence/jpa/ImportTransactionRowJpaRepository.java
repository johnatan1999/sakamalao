package mg.sakamalao.io.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.io.core.domain.ImportStatus;
import mg.sakamalao.io.infrastructure.adapter.persistence.entity.ImportTransactionRowDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ImportTransactionRowJpaRepository extends JpaRepository<ImportTransactionRowDbEntity, UUID> {
    @Query("""
      SELECT r 
      FROM ImportTransactionRowDbEntity r
      WHERE r.session.id = :sessionId
    """)
    List<ImportTransactionRowDbEntity> findAllBySessionId(@Param("sessionId") UUID sessionId);

    List<ImportTransactionRowDbEntity> findAllByStatus(ImportStatus status);

    /*@Query("""
        select count(t.id)
        from ImportTransactionRowDbEntity t
        where t.id in :ids
          and t.sessionId = :sessionId
    """)
    int countByIdsAndSessionId(
            @Param("ids") Set<UUID> ids,
            @Param("sessionId") UUID sessionId
    );*/
    int countByIdInAndSessionId(Set<UUID> ids, UUID sessionId);

}
