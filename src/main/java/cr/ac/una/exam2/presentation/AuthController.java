package cr.ac.una.exam2.presentation;

import cr.ac.una.exam2.logic.entidades.Usuario;
import cr.ac.una.exam2.logic.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario, HttpSession session) {
        Usuario authenticatedUser = authService.authenticate(usuario.getUsuario(), usuario.getContrasena());
        session.setAttribute("user", authenticatedUser);
        return ResponseEntity.ok(authenticatedUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    // Esto obtiene el estado de la sesión actual
    // Gracias a este método, se puede saber si el usuario está autenticado o no, y por ende, lo deja hacer órdenes o no
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Usuario user = (Usuario) session.getAttribute("user");
        response.put("authenticated", user != null);
        if (user != null) {
            response.put("user", user);
        }
        return ResponseEntity.ok(response);
    }

}
