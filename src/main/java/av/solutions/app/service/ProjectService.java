package av.solutions.app.service;

import av.solutions.app.entity.ProjectEntity;
import av.solutions.app.entity.ProjectsBannerEntity;
import av.solutions.app.model.ProjectModel;
import av.solutions.app.model.ProjectPageModel;
import av.solutions.app.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectImageService projectImageService;
    private final ProjectsBannerService projectsBannerService;

    public ProjectPageModel getPorductPage() {
        ProjectsBannerEntity projectsBannerEntity = projectsBannerService.getProjectsBanner();
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        return ProjectPageModel.entityToModel(projectsBannerEntity, projectEntities);
    }

    public ProjectModel getById(UUID id) {
        ProjectEntity projectEntity = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        return ProjectModel.entityToModel(projectEntity);
    }

    public void addProject(ProjectModel projectModel) {
        ProjectEntity projectEntity = projectRepository.save(projectModel.modelToEntity());
        projectImageService.addImageToProject(projectEntity.getId(), projectModel.imageIds());
    }

    public void deleteProject(UUID id) {
        projectRepository.deleteById(id);
    }
}
