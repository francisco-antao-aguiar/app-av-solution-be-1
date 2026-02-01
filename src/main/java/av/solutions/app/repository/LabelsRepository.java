package av.solutions.app.repository;

import av.solutions.app.entity.LabelsEntity;
import av.solutions.app.entity.LabelsIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelsRepository extends JpaRepository<LabelsEntity, LabelsIdEntity> {
}
