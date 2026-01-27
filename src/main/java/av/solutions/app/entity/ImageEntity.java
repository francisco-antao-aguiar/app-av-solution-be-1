package av.solutions.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;
@Table(name = "image")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ImageEntity {
    @Id
    private UUID id;

    @Lob
    private byte[] imageData;
}
