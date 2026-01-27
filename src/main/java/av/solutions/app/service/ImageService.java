package av.solutions.app.service;

import av.solutions.app.auth.repository.ImageRepository;
import av.solutions.app.entity.ImageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Optional<ImageEntity> getById(UUID id) {
        return imageRepository.findById(id);
    }
}
