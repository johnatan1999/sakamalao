package mg.sakamalao.io.infrastructure.driver;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.io.core.domain.ImportTransactionRow;
import mg.sakamalao.io.core.usecase.CompleteImportTransactionUseCase;
import mg.sakamalao.io.core.usecase.GetImportSessionUseCase;
import mg.sakamalao.io.infrastructure.adapter.CreateImportSessionService;
import mg.sakamalao.io.infrastructure.driver.entities.request.ImportTransactionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/transactions/import")
@RequiredArgsConstructor
public class ImportTransactionController {

    private final CompleteImportTransactionUseCase completeImportTransactionUseCase;

    private final CreateImportSessionService importSessionService;

    private final GetImportSessionUseCase getImportSessionUseCase;

    @PostMapping
    public ResponseEntity<Void> importTransaction(
            @RequestBody List<ImportTransactionRequest> transactions,
            @CurrentUser User user,
            @PathVariable UUID projectId
            ) {
        importSessionService.importData(
                projectId,
                user.id(),
                transactions.stream().map(ImportTransactionRequest::mapToInput).toList()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<List<ImportTransactionRow>> findBySession(
            @CurrentUser User user,
            @PathVariable UUID projectId,
            @PathVariable UUID sessionId
    ) {
        var result = getImportSessionUseCase.findBySession(sessionId, projectId, user.id());
        return ResponseEntity.ok(result);
    }

}
