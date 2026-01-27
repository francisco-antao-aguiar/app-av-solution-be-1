package av.solutions.app.service;

import av.solutions.app.auth.repository.ProjectsBannerRepository;
import av.solutions.app.entity.ProjectsBannerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsBannerService {
    private final ProjectsBannerRepository projectsBannerRepository;

    public List<ProjectsBannerEntity> getAll() {
        return projectsBannerRepository.findAll();
    }

}
