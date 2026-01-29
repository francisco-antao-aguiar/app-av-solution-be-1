package av.solutions.app.service;

import av.solutions.app.entity.ImageEntity;
import av.solutions.app.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Optional<ImageEntity> getById(UUID id) {
        return imageRepository.findById(id);
    }

    public UUID saveImage(MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setId(UUID.randomUUID());
        image.setImageData(file.getBytes()); // store file bytes
        imageRepository.save(image);
        return image.getId();
    }
}
