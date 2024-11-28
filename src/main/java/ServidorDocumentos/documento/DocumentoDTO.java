package ServidorDocumentos.documento;

import java.io.Serializable;

public class DocumentoDTO implements Serializable {
    public Long id;
    public Integer total;
    public Integer usuarioId;
    public Integer grupoId;
}