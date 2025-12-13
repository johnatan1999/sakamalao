package mg.sakamalao.project.infrastructure.driver.controller;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.core.domain.entity.Project;
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
public class ProjectController {
    private final CreateProjectUseCase createProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final FindProjectUseCase findProjectUseCase;
    private final FindByIdProjectUseCase findByIdProjectUseCase;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody CreateProjectRequest request) {
        Project created = createProjectUseCase.create(request.toProjectInput());
        return ResponseEntity.status(201).body(ProjectResponse.from(created));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAll() {
        return ResponseEntity.ok(findProjectUseCase.find(null)
                .stream()
                .map(ProjectResponse::from)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable("id") String id) {
        return findByIdProjectUseCase.findById(id)
                .map(ProjectResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        deleteProjectUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
