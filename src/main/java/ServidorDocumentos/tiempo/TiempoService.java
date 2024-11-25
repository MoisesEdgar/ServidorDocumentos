package ServidorDocumentos.tiempo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TiempoService {
    @Autowired
    private TiempoRepostitory repoTiempo;

    public Tiempo guardar(Tiempo tiempo){
        if(tiempo.getTiempo() == null){
            throw new RuntimeException("No se especifico el tiempo");
        }

        Integer valorTiempo = tiempo.getTiempo();
        if(valorTiempo < 60000){
            throw new RuntimeException("Valor del tiempo invalido. Ingresar un valor mas alto");
        }
         return repoTiempo.save(tiempo);
    }

    public Tiempo updateTiempo(Tiempo tiempo, Long id){
        Tiempo tiempoActual = repoTiempo.findById(id).orElseThrow(()-> new RuntimeException("Tiempo no encontrado"));

        if(tiempo.getTiempo()== null){
            throw new RuntimeException("No se espesifico el tiempo");
        }

        Integer valorTiempo = tiempo.getTiempo();
        if(valorTiempo < 60000){
            throw new RuntimeException("Valor del tiempo invalido. Ingresar un valor mas alto");
        }
        tiempoActual.setTiempo(tiempo.getTiempo());

        return repoTiempo.save(tiempoActual);
    }

}












