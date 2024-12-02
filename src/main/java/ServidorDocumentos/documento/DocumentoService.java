package ServidorDocumentos.documento;

import ServidorDocumentos.usuario.Usuario;
import ServidorDocumentos.usuario.UsuarioDTOConsulta;
import ServidorDocumentos.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private UsuarioRepository repoUsuario;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "https://beneficial-reprieve-production.up.railway.app";
    private String token = null;

    public ResponseEntity<DocumentoDTO[]> autenticarYCargarDatos(UsuarioDTOConsulta usuario) {
        try {
            if (token == null) {
                token = getToken(usuario);
            }
            getDocumentosPendientes(token);
        } catch (Exception e) {
            try {
                usuario = toDto(repoUsuario.findById(1l).orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
                token = getToken(usuario);
                getDocumentosPendientes(token);
            } catch (HttpServerErrorException | HttpClientErrorException er) {
                throw new RuntimeException("Error al consultar los datos. Codigo de error: " + er.getStatusCode());
            } catch (Exception er) {
                throw new RuntimeException("Error inesperado al consultar los datos: " + er);
            }
        }

        return getDocumentosPendientes(token);
    }

    public String getToken(UsuarioDTOConsulta usuario) {

        HttpEntity<UsuarioDTOConsulta> request = new HttpEntity<>(usuario);
        ResponseEntity<UsuarioDTOConsulta> response = restTemplate.exchange(url + "/login", HttpMethod.POST, request, UsuarioDTOConsulta.class);
        HttpHeaders headers = response.getHeaders();
        return headers.getFirst("Authorization");

    }

    public ResponseEntity<DocumentoDTO[]> getDocumentosPendientes(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<DocumentoDTO[]> response = restTemplate.exchange(url + "/api/totales", HttpMethod.GET, request, DocumentoDTO[].class);
        return response;
    }

    public UsuarioDTOConsulta toDto(Usuario usuario) {
        UsuarioDTOConsulta dto = new UsuarioDTOConsulta();
        dto.email = usuario.getUser();
        dto.password = usuario.getPassword();
        return dto;
    }
}





