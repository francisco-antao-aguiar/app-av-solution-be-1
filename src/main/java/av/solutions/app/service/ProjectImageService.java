package av.solutions.app.service;

import av.solutions.app.entity.ImageEntity;
import av.solutions.app.entity.ProjectEntity;
import av.solutions.app.entity.ProjectImageEntity;
import av.solutions.app.repository.ImageRepository;
import av.solutions.app.repository.ProjectImageRepository;
import av.solutions.app.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectImageService {
    private final ProjectImageRepository projectImageRepository;
    private final ProjectRepository projectRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public void addImageToProject(UUID projectId, List<UUID> imageIds) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        List<ImageEntity> imageEntities = imageIds.stream()
                .map(imageRepository::getReferenceById)
                .toList();

        List<ProjectImageEntity> projectImageEntities = imageEntities.stream()
                .map(imageEntity -> {
                    ProjectImageEntity projectImageEntity = new ProjectImageEntity();
                    projectImageEntity.setProject(projectEntity);
                    projectImageEntity.setImage(imageEntity);
                    return projectImageEntity;
                })
                .toList();

        projectImageRepository.saveAll(projectImageEntities);
    }

    public void deleteByImageId(UUID id) {
        projectImageRepository.deleteByImage_Id(id);
    }
}
