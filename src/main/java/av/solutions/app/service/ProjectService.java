package av.solutions.app.service;

import av.solutions.app.entity.ProjectEntity;
import av.solutions.app.entity.ProjectsBannerEntity;
import av.solutions.app.model.ProjectModel;
import av.solutions.app.model.ProjectPageModel;
import av.solutions.app.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectImageService projectImageService;
    private final ProjectsBannerService projectsBannerService;
    private final CacheManager cacheManager;

    @Cacheable(value = "data", key = "#root.methodName")
    public ProjectPageModel getPorductPage() {
        ProjectsBannerEntity projectsBannerEntity = projectsBannerService.getProjectsBanner();
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        return ProjectPageModel.entityToModel(projectsBannerEntity, projectEntities);
    }

    @Cacheable(value = "data", key = "#root.methodName + '_' + #id")
    public ProjectModel getById(UUID id) {
        ProjectEntity projectEntity = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        return ProjectModel.entityToModel(projectEntity);
    }

    public UUID addProject(ProjectModel projectModel) {
        ProjectEntity projectEntity = projectRepository.save(projectModel.modelToEntity());
        projectImageService.addImageToProject(projectEntity.getId(), projectModel.imageIds());
        cacheManager.getCache("data").clear();
        return projectEntity.getId();
    }

    public void deleteProject(UUID id) {
        ProjectEntity projectEntity = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        cacheManager.getCache("data").clear();
        projectRepository.delete(projectEntity);
    }

    public void updateProject(ProjectModel projectModel) {
        projectRepository.save(projectModel.modelToEntity());
        projectImageService.addImageToProject(projectModel.id(), projectModel.imageIds());
        cacheManager.getCache("data").clear();
    }

    public void deleteProjectImage(UUID imageId) {
        cacheManager.getCache("data").clear();
        projectImageService.deleteByImageId(imageId);
    }
}
