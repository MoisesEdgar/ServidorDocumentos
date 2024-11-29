package ServidorDocumentos.documento;

import ServidorDocumentos.usuario.Usuario;
import ServidorDocumentos.usuario.UsuarioDTOConsulta;
import ServidorDocumentos.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService serviceDocumento;

    @Autowired
    private UsuarioRepository repoUsuario;

    @GetMapping
    public DocumentoDTO[] getAll() {
        UsuarioDTOConsulta usuario = toDto(repoUsuario.findById(1l).orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
        return serviceDocumento.autenticarYCargarDatos(usuario).getBody();
    }

    public UsuarioDTOConsulta toDto(Usuario usuario) {
        UsuarioDTOConsulta dto = new UsuarioDTOConsulta();
        dto.email = usuario.getUser();
        dto.password = usuario.getPassword();
        return dto;
    }
}










