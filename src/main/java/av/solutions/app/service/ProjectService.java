package av.solutions.app.service;

import av.solutions.app.auth.repository.ProjectRepository;
import av.solutions.app.entity.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<ProjectEntity> getAll() {
        return projectRepository.findAll();
    }

    public Optional<ProjectEntity> getById(UUID id) {
        return projectRepository.findById(id);
    }
}
