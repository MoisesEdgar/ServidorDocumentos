package ServidorDocumentos.tiempo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/tiempos")
public class TiempoController {

    @Autowired
    private TiempoRepostitory repoTiempo;

    @Autowired
    private TiempoService serviceTiempo;


    @GetMapping
    public TiempoDTO getAll() {
        Tiempo tiempo = repoTiempo.findById(2l).orElseThrow(() -> new RuntimeException("No se encontro el tiempo"));
        return toDto(tiempo);
    }

    @PostMapping
    public TiempoDTO save(@RequestBody TiempoDTO tiempoDTO) {
        Tiempo tiempo = toEntity(tiempoDTO);
        Tiempo guardado = serviceTiempo.guardar(tiempo);
        return toDto(guardado);
    }

    @PutMapping("/{id}")
    public TiempoDTO update(@RequestBody TiempoDTO tiempoDTO, @PathVariable Long id) {
        Tiempo tiempo = toEntity(tiempoDTO);
        Tiempo modificado = serviceTiempo.updateTiempo(tiempo, id);
        return toDto(modificado);
    }

    private TiempoDTO toDto(Tiempo tiempo) {
        TiempoDTO dto = new TiempoDTO();
        dto.id = tiempo.getId();
        dto.tiempo = tiempo.getTiempo();
        return dto;
    }

    private Tiempo toEntity(TiempoDTO dto) {
        Tiempo tiempo = new Tiempo();
        tiempo.setId(dto.id);
        tiempo.setTiempo(dto.tiempo);
        return tiempo;
    }
}
