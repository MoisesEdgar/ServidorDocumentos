package ServidorDocumentos.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repoUsuario;

    public Usuario guardar(Usuario usuario) {

        if (usuario.getUser().isEmpty() || usuario.getUser() == null) {
            throw new RuntimeException("No se especifico el nombre del usuario");
        }

        if (usuario.getPassword().isEmpty() || usuario.getPassword() == null) {
            throw new RuntimeException("No se especifico la contraseña del usuario");
        }

        return repoUsuario.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario, Long id) {
        Usuario usuarioActual = repoUsuario.findById(id).orElseThrow(() -> new RuntimeException("No se encontro al usuario"));

        if (usuario.getUser().isEmpty() || usuario.getUser() == null) {
            throw new RuntimeException("No se espesifico el usuario");
        }

        if (usuario.getPassword().isEmpty() || usuario.getPassword() == null) {
            throw new RuntimeException("No se espesifico la contraseña");
        }

        usuarioActual.setUser(usuario.getUser());
        usuarioActual.setPassword(usuario.getPassword());

        return repoUsuario.save(usuarioActual);
    }

}


