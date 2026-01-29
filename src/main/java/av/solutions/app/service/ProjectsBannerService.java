package av.solutions.app.service;

import av.solutions.app.entity.ProjectsBannerEntity;
import av.solutions.app.repository.ProjectsBannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsBannerService {
    private final ProjectsBannerRepository projectsBannerRepository;

    public ProjectsBannerEntity getProjectsBanner() {
        List<ProjectsBannerEntity> projectsBannerEntities = projectsBannerRepository.findAll();
        if (projectsBannerEntities.isEmpty()) {
            throw new IllegalArgumentException("Project Banner not found");
        }
        return projectsBannerEntities.getFirst();
    }

}
