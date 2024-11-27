package ServidorDocumentos.tiempo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tiempos")
public class TiempoController {

    @Autowired
    private TiempoRepostitory repoTiempo;

    @Autowired
    private TiempoService serviceTiempo;

    @GetMapping
    public List<TiempoDTO> getAll() {
        List<Tiempo> tiempos = repoTiempo.findAll();
        return tiempos.stream()
                .map(tiempo -> toDto(tiempo))
                .collect(Collectors.toList());
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

//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable Long id) {
//        repoTiempo.deleteById(id);
//        return "Se elimino el tiempo con el id_: " + id;
//    }

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
