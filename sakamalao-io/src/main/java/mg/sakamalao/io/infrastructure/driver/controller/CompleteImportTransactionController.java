package mg.sakamalao.io.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.io.core.application.CompleteTransactionImportService;
import mg.sakamalao.io.infrastructure.driver.entities.request.MappedTransactionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/transactions/import")
@RequiredArgsConstructor
public class CompleteImportTransactionController {
    private final CompleteTransactionImportService completeTransactionImportService;

    @PostMapping("/{sessionId}/complete")
    public ResponseEntity<Void> completeImportTransaction(
            @RequestBody List<MappedTransactionRequest> transactions,
            @CurrentUser User user,
            @PathVariable UUID projectId
    ) {
        completeTransactionImportService.completeImportTransaction(
                user.id(),
                projectId,
                transactions.stream().map(MappedTransactionRequest::mapToInput).toList()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
