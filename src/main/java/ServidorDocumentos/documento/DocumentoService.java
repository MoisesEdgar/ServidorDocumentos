package ServidorDocumentos.documento;

import ServidorDocumentos.usuario.Usuario;
import ServidorDocumentos.usuario.UsuarioDTOConsulta;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Service;

@Service
public class DocumentoService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "";

    public String getToken(UsuarioDTOConsulta usuario) {
        HttpEntity<UsuarioDTOConsulta> request = new HttpEntity<>(usuario);
        ResponseEntity<UsuarioDTOConsulta> response = restTemplate.exchange(url, HttpMethod.POST, request, UsuarioDTOConsulta.class);
        HttpHeaders headers = response.getHeaders();
        String token = headers.getFirst("Authorization");
        return token;
    }

    public ResponseEntity<DocumentoDTO[]> getDocumentosPendientes(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<DocumentoDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, request, DocumentoDTO[].class);
        System.out.print(response.getHeaders());

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Respuesta recibida: " + response.getBody());
        } else {
            System.out.println("Error en la petición GET: " + response.getStatusCode());
        }

        return response;
    }
}


