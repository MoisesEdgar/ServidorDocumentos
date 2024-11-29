package ServidorDocumentos.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repoUsuario;

    @Autowired
    private UsuarioService serviceUsuario;

    @GetMapping
    public UsuarioDTO getAll() {
        Usuario usuario = repoUsuario.findById(1l).orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        return toDto(usuario);
    }

    @PostMapping
    public UsuarioDTO save(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = toEntity(usuarioDTO);
        Usuario guardado = serviceUsuario.guardar(usuario);
        return toDto(guardado);
    }

    @PutMapping("/{id}")
    public UsuarioDTO update(@RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
        Usuario usuario = toEntity(usuarioDTO);
        Usuario modificado = serviceUsuario.updateUsuario(usuario, id);
        return toDto(modificado);
    }

    private UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.id = usuario.getId();
        dto.user = usuario.getUser();
        dto.password = usuario.getPassword();
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.id);
        usuario.setUser(dto.user);
        usuario.setPassword(dto.password);
        return usuario;
    }
}


