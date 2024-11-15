package cr.ac.una.exam2.logic.services;

import cr.ac.una.exam2.data.UsuarioRepository;
import cr.ac.una.exam2.logic.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository userRepository;

    @Autowired
    public AuthService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getContrasena().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
