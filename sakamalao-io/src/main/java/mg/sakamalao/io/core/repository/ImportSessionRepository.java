package mg.sakamalao.io.core.repository;

import mg.sakamalao.io.core.domain.ImportSession;
import mg.sakamalao.io.core.domain.input.ImportSessionInput;

import java.util.List;

public interface ImportSessionRepository {
    ImportSession create(ImportSessionInput input);
    List<ImportSession> findPendingImports();
    void update(ImportSession importSession);
}
