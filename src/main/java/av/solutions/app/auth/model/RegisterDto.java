package av.solutions.app.auth.model;

public record RegisterDto(String username, String password, UserRole role) {
}
