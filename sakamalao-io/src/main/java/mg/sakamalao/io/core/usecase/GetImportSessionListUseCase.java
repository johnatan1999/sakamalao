package mg.sakamalao.io.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.usecase.ProjectAccessCheckerUseCase;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.io.core.domain.ImportSession;
import mg.sakamalao.io.core.repository.ImportSessionRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetImportSessionListUseCase {
    private final ProjectAccessCheckerUseCase projectAccess;
    private final ImportSessionRepository importSessionRepository;

    public List<ImportSession> findAll(UUID userId, UUID projectId) {
        FieldValidator.notNull("userId", userId);
        FieldValidator.notNull("projectId", projectId);

        projectAccess.checkAccess(userId, projectId);
        return importSessionRepository.findPendingImportsByProjectId(projectId);
    }
}
