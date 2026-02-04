package av.solutions.app.service;

import av.solutions.app.entity.LabelsEntity;
import av.solutions.app.repository.LabelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LabelsService {
    private final LabelsRepository labelsRepository;
    private final CacheManager cacheManager;

    @Cacheable(value = "data", key = "#root.methodName")
    public Map<String, Map<String, String>> getLabels() {
        List<LabelsEntity> labels = labelsRepository.findAll();
        Map<String, Map<String, String>> labelStructure = new HashMap<>();
        for (LabelsEntity label : labels) {
            labelStructure.computeIfAbsent(label.getPageId(), k -> new HashMap<>())
                    .put(label.getLabelId(), label.getDescription());
        }
        return labelStructure;
    }

    public Map<String, Map<String, String>> putLabels(Map<String, Map<String, String>> labels) {
        List<LabelsEntity> labelsToSave = labels.entrySet().stream()
                .flatMap(pageEntry ->
                        pageEntry.getValue().entrySet().stream()
                                .map(labelEntry -> {
                                    LabelsEntity entity = new LabelsEntity();
                                    entity.setPageId(pageEntry.getKey());
                                    entity.setLabelId(labelEntry.getKey());
                                    entity.setDescription(labelEntry.getValue());
                                    return entity;
                                })
                )
                .toList();
        labelsToSave = labelsRepository.saveAll(labelsToSave);
        Map<String, Map<String, String>> labelStructure = new HashMap<>();
        for (LabelsEntity label : labelsToSave) {
            labelStructure.computeIfAbsent(label.getPageId(), k -> new HashMap<>())
                    .put(label.getLabelId(), label.getDescription());
        }
        cacheManager.getCache("data").clear();
        return labelStructure;

    }
}
