package mg.sakamalao.io.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.User;
import mg.sakamalao.common.infrastructure.driver.domain.CurrentUser;
import mg.sakamalao.io.core.domain.ImportSession;
import mg.sakamalao.io.core.usecase.GetImportSessionListUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/transactions/import/sessions")
@RequiredArgsConstructor
public class ImportSessionController {

    private final GetImportSessionListUseCase importSessionListUseCase;

    @GetMapping
    public ResponseEntity<List<ImportSession>> findAll(
            @CurrentUser User user,
            @PathVariable UUID projectId
    ) {
       var result = importSessionListUseCase.findAll(user.id(), projectId);
       return ResponseEntity.ok(result);
    }
}
