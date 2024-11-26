package ServidorDocumentos.documento;

import ServidorDocumentos.usuario.Usuario;
import ServidorDocumentos.usuario.UsuarioDTO;
import ServidorDocumentos.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService serviceDocumento;

    @Autowired
    private UsuarioRepository repoUsuario;

//    @GetMapping
//    public List<DocumentoDTO> getAll(){
//        Usuario usuario = repoUsuario.findById(1l).orElseThrow(()-> new RuntimeException("No se encontro el usuario"));
//        String token = serviceDocumento.getToken(usuario);
//
//        List<DocumentoDTO> documentos = serviceDocumento.getDocumentosPendientes(token);
//        return documentos;
//    }

    @GetMapping
    public ResponseEntity<DocumentoDTO[]> getAll(){
        ResponseEntity<DocumentoDTO[]> documentos = serviceDocumento.getDocumentosPendientes();
        return documentos;
    }




}
