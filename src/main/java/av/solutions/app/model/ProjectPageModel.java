package av.solutions.app.model;

import av.solutions.app.entity.ProjectEntity;
import av.solutions.app.entity.ProjectsBannerEntity;

import java.util.List;
import java.util.UUID;

public record ProjectPageModel(
        UUID id,
        String title,
        String subtitle,
        List<ProjectModel> project
) {
    public static ProjectPageModel entityToModel(ProjectsBannerEntity projectsBannerEntity, List<ProjectEntity> projectEntities) {
        List<ProjectModel> projectModels = projectEntities.stream().map(ProjectModel::entityToModel).toList();
        return new ProjectPageModel(
                projectsBannerEntity.getId(),
                projectsBannerEntity.getTitle(),
                projectsBannerEntity.getSubtitle(),
                projectModels

        );
    }
}
