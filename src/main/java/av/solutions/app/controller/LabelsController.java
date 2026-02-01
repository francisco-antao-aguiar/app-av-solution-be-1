package av.solutions.app.controller;

import av.solutions.app.model.ProjectModel;
import av.solutions.app.model.ProjectPageModel;
import av.solutions.app.service.LabelsService;
import av.solutions.app.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelsController {

    private final LabelsService labelsService;

    @GetMapping()
    public Map<String, Map<String, String>> getLabels() {
        return labelsService.getLabels();
    }

    @PutMapping()
    public Map<String, Map<String, String>> putLabels(@RequestBody Map<String, Map<String, String>> labels) {
        return labelsService.putLabels(labels);
    }

}
