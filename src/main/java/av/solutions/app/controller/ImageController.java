package av.solutions.app.controller;

import av.solutions.app.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // Upload an image
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UUID uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return imageService.saveImage(file);
    }

    // Download an image by ID
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable UUID id) {
        return imageService.getById(id)
                .map(image -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + id + ".jpg\"")
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(image.getImageData()))
                .orElse(ResponseEntity.notFound().build());
    }
}
