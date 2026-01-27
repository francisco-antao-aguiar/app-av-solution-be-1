package av.solutions.app.auth.repository;

import av.solutions.app.entity.ProjectImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImageEntity, UUID> {
}
