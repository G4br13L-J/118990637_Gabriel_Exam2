package cr.ac.una.exam2.data;

import cr.ac.una.exam2.logic.entidades.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepository() {
        usuarios.add(new Usuario(1L, "admin", "admin"));
        usuarios.add(new Usuario(2L, "user", "password"));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuarioById(Long id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarios.stream()
                .filter(usuario -> usuario.getUsuario().equals(username))
                .findFirst();
    }

}
