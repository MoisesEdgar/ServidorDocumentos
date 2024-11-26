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
    public List<UsuarioDTO> getAll(){
        List<Usuario> usuarios = repoUsuario.findAll();
        return usuarios.stream()
                .map(usuario -> toDto(usuario))
                .collect(Collectors.toList());
    }

    @PostMapping
    public UsuarioDTO save(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = toEntity(usuarioDTO);
        Usuario guardado = serviceUsuario.guardar(usuario);
        return toDto(guardado);
    }

    @PutMapping("/{id}")
    public UsuarioDTO update(@RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id){
        Usuario usuario = toEntity(usuarioDTO);
        Usuario modificado = serviceUsuario.updateUsuario(usuario, id);
        return toDto(modificado);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        repoUsuario.deleteById(id);
        return "Se elimino el usuario con id " + id;
    }

    private UsuarioDTO toDto(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.id = usuario.getId();
        dto.usuario = usuario.getUsuario();
        dto.contraseña = usuario.getContraseña();
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setId(dto.id);
        usuario.setUsuario(dto.usuario);
        usuario.setContraseña(dto.contraseña);
        return usuario;
    }
}


