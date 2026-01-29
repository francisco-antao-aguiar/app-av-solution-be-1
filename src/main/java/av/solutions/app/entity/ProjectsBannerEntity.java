package av.solutions.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "projects_banner")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProjectsBannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String subtitle;
}


