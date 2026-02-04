package av.solutions.app.repository;

import av.solutions.app.entity.ProjectImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImageEntity, UUID> {

    @Transactional
    void deleteByImage_Id(UUID uuid);
}
