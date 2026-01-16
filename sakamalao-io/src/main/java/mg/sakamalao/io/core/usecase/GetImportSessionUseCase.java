package mg.sakamalao.io.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.io.core.domain.ImportTransactionRow;
import mg.sakamalao.io.core.repository.ImportTransactionRowRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetImportSessionUseCase {
    private final ProjectAccessPort projectAccess;
    private final ImportTransactionRowRepository importTransactionRowRepository;

    public List<ImportTransactionRow> findBySession(
            UUID sessionId,
            UUID projectId,
            UUID userId
    ) {
//        FieldValidator.notNull("sessionId", sessionId);
        FieldValidator.notNull("projectId", projectId);
        FieldValidator.notNull("userId", userId);

        var hasAccess = projectAccess.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found.");
        }

        // TODO: Session is not checked if it's belongs to the project or not
        return importTransactionRowRepository.findBySession(sessionId);
    }
}
