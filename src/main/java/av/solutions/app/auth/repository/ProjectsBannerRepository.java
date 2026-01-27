package av.solutions.app.auth.repository;

import av.solutions.app.entity.ProjectsBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectsBannerRepository extends JpaRepository<ProjectsBannerEntity, UUID> {
}
