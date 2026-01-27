package av.solutions.app.service;

import av.solutions.app.auth.repository.ProjectImageRepository;
import av.solutions.app.entity.ProjectImageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectImageService {
    private final ProjectImageRepository projectImageRepository;
    public Optional<ProjectImageEntity> getById(UUID id) {
        return projectImageRepository.findById(id);
    }
}
