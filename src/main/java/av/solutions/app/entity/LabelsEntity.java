package av.solutions.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "labels")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "labelId")
@IdClass(LabelsIdEntity.class)
public class LabelsEntity {
    @Id
    private String pageId;
    @Id
    private String labelId;
    private String description;
}


