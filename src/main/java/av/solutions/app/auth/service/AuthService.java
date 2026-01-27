package av.solutions.app.auth.service;

import av.solutions.app.auth.entity.UserEntity;
import av.solutions.app.auth.model.RegisterDto;
import av.solutions.app.auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public ResponseEntity<?> registerUser(RegisterDto registerDto) {
        if(isUsernameIsAvailable(registerDto.username())) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
            UserEntity newUser = new UserEntity(registerDto.username(), encryptedPassword, registerDto.role());
            this.userRepository.save(newUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public boolean isUsernameIsAvailable(String username) {
        return null == userRepository.findByUsername(username);
    }
}
