package ServidorDocumentos.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repoUsuario;

    public Usuario guardar(Usuario usuario){

        if(usuario.getUsuario().isEmpty() || usuario.getUsuario() == null){
            throw new RuntimeException("No se especifico el nombre del usuario");
        }

        if(usuario.getContraseña().isEmpty() || usuario.getContraseña() == null){
            throw new RuntimeException("No se especifico la contraseña del usuario");
        }

        return repoUsuario.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario, Long id){
        Usuario usuarioActual = repoUsuario.findById(id).orElseThrow(()-> new RuntimeException("No se encontro al usuario"));

        if(usuario.getUsuario().isEmpty() || usuario.getUsuario() == null){
            throw new RuntimeException("No se espesifico el usuario");
        }

        if(usuario.getContraseña().isEmpty() || usuario.getContraseña() == null){
            throw new RuntimeException("No se espesifico la contraseña");
        }

        usuarioActual.setUsuario(usuario.getUsuario());
        usuarioActual.setContraseña(usuario.getContraseña());

        return repoUsuario.save(usuarioActual);
    }

}


