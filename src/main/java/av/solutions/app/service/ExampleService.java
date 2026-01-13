package av.solutions.app.service;

import av.solutions.app.entity.ExampleEntity;
import av.solutions.app.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public List<ExampleEntity> getAllExamples() {
        return exampleRepository.findAll();
    }
}
