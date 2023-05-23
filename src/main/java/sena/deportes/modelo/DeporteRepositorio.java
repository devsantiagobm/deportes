
package sena.deportes.modelo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sena.deportes.modelo.DeporteEntidad;

public interface DeporteRepositorio extends JpaRepository<DeporteEntidad, Long>{
    List<DeporteEntidad> findByEstado(String estado);
}