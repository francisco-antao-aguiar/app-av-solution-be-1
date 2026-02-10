package av.solutions.app.service;

import av.solutions.app.entity.ImageEntity;
import av.solutions.app.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Cacheable(value = "data", key = "#root.methodName + '_' + #id")
    public Optional<ImageEntity> getById(UUID id) {
        return imageRepository.findById(id);
    }

    public UUID saveImage(MultipartFile file) throws IOException {
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(originalImage)
                .size(1920, 1080)
                .outputQuality(0.8)
                .outputFormat("jpg")
                .toOutputStream(outputStream);
        ImageEntity image = new ImageEntity();
        image.setId(UUID.randomUUID());
        image.setImageData(outputStream.toByteArray());
        imageRepository.save(image);
        return image.getId();
    }
}
