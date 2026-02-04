package av.solutions.app.model;

import av.solutions.app.entity.ProjectEntity;

import java.util.List;
import java.util.UUID;

public record ProjectModel(
        UUID id,
        String title,
        String subtitle,
        String description,
        String location,
        Integer year,
        Integer totalArea,
        Integer duration,
        String durationUnit,
        List<UUID> imageIds
) {
    public static ProjectModel entityToModel(ProjectEntity projectEntity) {
        List<UUID> imageIds = projectEntity.getProjectImages().stream()
                .map(projectImageEntity ->
                        projectImageEntity.getImage().getId())
                .toList();
        return new ProjectModel(
                projectEntity.getId(),
                projectEntity.getTitle(),
                projectEntity.getSubtitle(),
                projectEntity.getDescription(),
                projectEntity.getLocation(),
                projectEntity.getYear(),
                projectEntity.getTotalArea(),
                projectEntity.getDuration(),
                projectEntity.getDurationUnit(),
                imageIds
        );
    }

    public ProjectEntity modelToEntity() {
        return new ProjectEntity(
                this.id,
                this.title,
                this.subtitle,
                this.description,
                this.location,
                this.year,
                this.totalArea,
                this.duration,
                this.durationUnit,
                null
        );
    }
}
