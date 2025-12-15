package mg.sakamalao.project.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.core.domain.entity.Project;
import mg.sakamalao.core.domain.entity.User;
import mg.sakamalao.core.infrastructure.driver.BaseController;
import mg.sakamalao.project.core.usecase.CreateProjectUseCase;
import mg.sakamalao.project.core.usecase.DeleteProjectUseCase;
import mg.sakamalao.project.core.usecase.FindByIdProjectUseCase;
import mg.sakamalao.project.core.usecase.FindProjectUseCase;
import mg.sakamalao.project.infrastructure.driver.entity.request.CreateProjectRequest;
import mg.sakamalao.project.infrastructure.driver.entity.response.ProjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController extends BaseController {
    private final CreateProjectUseCase createProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final FindProjectUseCase findProjectUseCase;
    private final FindByIdProjectUseCase findByIdProjectUseCase;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @RequestBody CreateProjectRequest request
    ) {
        User user = getCurrentUser();
        Project created = createProjectUseCase.create(user.id(), request.toProjectInput());
        return ResponseEntity.status(201).body(ProjectResponse.from(created));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAll() {
        User user = getCurrentUser();
        return ResponseEntity.ok(findProjectUseCase.find(user.id(), null)
                .stream()
                .map(ProjectResponse::from)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable("id") String id) {
        User user = getCurrentUser();
        return findByIdProjectUseCase.findById(user.id(), id)
                .map(ProjectResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        User user = getCurrentUser();
        deleteProjectUseCase.delete(user.id(), id);
        return ResponseEntity.noContent().build();
    }
}
