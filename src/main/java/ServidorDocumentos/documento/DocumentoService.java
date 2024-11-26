package ServidorDocumentos.documento;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DocumentoService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "https://jsonplaceholder.typicode.com/posts";

//    public Usuario getToken(Usuario usuario){
//
//        HttpEntity <Usuario> request = new HttpEntity<>(usuario);
//        ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.POST, request, Usuario.class);
//
//        //COMO OBTENER EL TOKEN DE LA RESPUESTA
//
//        return token;
//    }
//
//    public List<DocumentoDTO> getDocumentosPendientes(String token){
//        List<DocumentoDTO> documentos = new ArrayList<>();
//        // COMO ENVIAR PETICION GET CON TOKEN INCLUIDO
//
//        return documentos;
//    }

        public ResponseEntity<DocumentoDTO[]> getDocumentosPendientes(){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity request = new HttpEntity(headers);

            ResponseEntity<DocumentoDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, request, DocumentoDTO[].class);

            System.out.print(response.getHeaders());

            return response;
        }
}



