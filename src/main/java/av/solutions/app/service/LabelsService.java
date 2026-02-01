package av.solutions.app.service;

import av.solutions.app.entity.LabelsEntity;
import av.solutions.app.repository.LabelsRepository;
import av.solutions.app.repository.ProjectImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LabelsService {
    private final LabelsRepository labelsRepository;

    public Map<String, Map<String, String>> getLabels() {
        List<LabelsEntity> labels = labelsRepository.findAll();
        Map<String, Map<String, String>> labelStructure = new HashMap<>();
        for (LabelsEntity label : labels) {
            labelStructure.computeIfAbsent(label.getPageId(), k -> new HashMap<>())
                    .put(label.getLabelId(), label.getDesc());
        }
        return labelStructure;
    }
}
