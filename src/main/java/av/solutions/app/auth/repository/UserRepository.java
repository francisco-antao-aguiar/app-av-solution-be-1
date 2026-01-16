package av.solutions.app.auth.repository;

import av.solutions.app.auth.entity.User;
import av.solutions.app.entity.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByUsername(String username);
}
