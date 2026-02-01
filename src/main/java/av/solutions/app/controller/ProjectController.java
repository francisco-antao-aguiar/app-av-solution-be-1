package av.solutions.app.controller;

import av.solutions.app.model.ProjectModel;
import av.solutions.app.model.ProjectPageModel;
import av.solutions.app.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public ProjectPageModel getProjects() {
        return projectService.getPorductPage();
    }

    @GetMapping("/{id}")
    public ProjectModel getProjectById(@PathVariable UUID id) {
        return projectService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createProject(@RequestBody ProjectModel projectModel) {
        return projectService.addProject(projectModel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
    }

    @PatchMapping("/update/{id}")
    public void updateProject(@RequestBody ProjectModel projectModel) {
        projectService.updateProject(projectModel);
    }


    @DeleteMapping("/delete/image/{id}")
    public void deleteImageProject(@PathVariable UUID id) {
        projectService.deleteProjectImage(id);
    }
}
