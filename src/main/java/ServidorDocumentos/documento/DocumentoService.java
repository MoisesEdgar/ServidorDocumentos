package ServidorDocumentos.documento;

import ServidorDocumentos.usuario.UsuarioDTOConsulta;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Service;

@Service
public class DocumentoService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "https://beneficial-reprieve-production.up.railway.app/login";
    private final String url2 = "https://beneficial-reprieve-production.up.railway.app/api/totales/usuario?usuarioId=6";
    public String getToken(UsuarioDTOConsulta usuario) {
        try {

            HttpEntity<UsuarioDTOConsulta> request = new HttpEntity<>(usuario);
            ResponseEntity<UsuarioDTOConsulta> response = restTemplate.exchange(url, HttpMethod.POST, request, UsuarioDTOConsulta.class);
            HttpHeaders headers = response.getHeaders();
            String token = headers.getFirst("Authorization");
            return token;

        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Error en la consulta de los documentos: " + e.getStatusCode());
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error en la consulta de los documentos: " + e.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException("Error en la consulta de los documentos: " + e);
        }
    }

    public ResponseEntity<DocumentoDTO[]> getDocumentosPendientes(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<DocumentoDTO[]> response = restTemplate.exchange(url2, HttpMethod.GET, request, DocumentoDTO[].class);
            return response;

        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Error en la consulta de los documentos: " + e.getStatusCode());
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error en la consulta de los documentos: " + e.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException("Error en la consulta de los documentos: " + e);
        }
    }

}


