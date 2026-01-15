package mg.sakamalao.io.infrastructure.driver;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.io.core.usecase.ImportTransactionUseCase;
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

    private final ImportTransactionUseCase importTransactionUseCase;

    private final CreateImportSessionService importSessionService;

    @PostMapping
    public ResponseEntity<Void> importTransaction(
            @RequestBody List<ImportTransactionRequest> transactions,
            @CurrentUser User user,
            @PathVariable UUID projectId
            ) {
//        importTransactionUseCase.importTransactions(user.id(), projectId, transactions);
        importSessionService.importData(
                projectId,
                user.id(),
                transactions.stream().map(ImportTransactionRequest::mapToInput).toList()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
