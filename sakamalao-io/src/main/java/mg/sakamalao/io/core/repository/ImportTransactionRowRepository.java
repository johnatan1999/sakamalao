package mg.sakamalao.io.core.repository;

import mg.sakamalao.io.core.domain.ImportStatus;
import mg.sakamalao.io.core.domain.ImportTransactionRow;
import mg.sakamalao.io.core.domain.input.ImportTransactionRowInput;

import java.util.List;
import java.util.UUID;

public interface ImportTransactionRowRepository {
    void insert(List<ImportTransactionRowInput> transactions);
    void updateStatus(UUID id, ImportStatus importStatus);
    List<ImportTransactionRow> findBySession(UUID sessionId);
}
