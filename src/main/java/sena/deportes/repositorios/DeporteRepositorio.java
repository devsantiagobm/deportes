
package sena.deportes.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sena.deportes.entidades.Deporte;

public interface DeporteRepositorio extends JpaRepository<Deporte, Long>{
    List<Deporte> findByEstado(String estado);
}