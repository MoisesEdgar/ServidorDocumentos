package ServidorDocumentos.documento;

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


    public ResponseEntity<DocumentoDTO[]> autenticarYCargarDatos(UsuarioDTOConsulta usuario) {
        String token = getToken(usuario);
        return getDocumentosPendientes(token);
    }

    public String getToken(UsuarioDTOConsulta usuario) {
        try {
            HttpEntity<UsuarioDTOConsulta> request = new HttpEntity<>(usuario);
            ResponseEntity<UsuarioDTOConsulta> response = restTemplate.exchange(url + "/login", HttpMethod.POST, request, UsuarioDTOConsulta.class);
            HttpHeaders headers = response.getHeaders();
            return headers.getFirst("Authorization");
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener el token. Codigo de error: " + e.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al obtener el token: " + e);
        }
    }

    public ResponseEntity<DocumentoDTO[]> getDocumentosPendientes(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<DocumentoDTO[]> response = restTemplate.exchange(url + "/api/totales", HttpMethod.GET, request, DocumentoDTO[].class);
            return response;
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new RuntimeException("Error al consultar los datos. Codigo de error: " + e.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al consultar los datos: " + e);
        }
    }
}











