package av.solutions.app.entity;

import jakarta.persistence.Id;

public class LabelsIdEntity {
    @Id
    private String pageId;
    @Id
    private String labelId;
}
