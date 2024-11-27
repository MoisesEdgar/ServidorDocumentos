package ServidorDocumentos.documento;

import ServidorDocumentos.usuario.Usuario;
import ServidorDocumentos.usuario.UsuarioDTOConsulta;
import ServidorDocumentos.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DocumentoDTO[]> getAll() {
        UsuarioDTOConsulta usuario = toDto(repoUsuario.findById(1l).orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
        String token = serviceDocumento.getToken(usuario);

        ResponseEntity<DocumentoDTO[]> documentos = serviceDocumento.getDocumentosPendientes(token);
        return documentos;
    }

    private UsuarioDTOConsulta toDto(Usuario usuario) {
        UsuarioDTOConsulta dto = new UsuarioDTOConsulta();
        dto.user = usuario.getUser();
        dto.password = usuario.getPassword();
        return dto;
    }

}










