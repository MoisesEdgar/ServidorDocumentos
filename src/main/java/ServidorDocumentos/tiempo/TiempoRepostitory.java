package ServidorDocumentos.tiempo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiempoRepostitory extends JpaRepository<Tiempo, Long>{
}
