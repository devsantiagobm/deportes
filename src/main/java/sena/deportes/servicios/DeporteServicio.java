package sena.deportes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sena.deportes.repositorios.DeporteRepositorio;
import sena.deportes.entidades.Deporte;
import java.util.List;

@Service
public class DeporteServicio {

    @Autowired
    private DeporteRepositorio repositorio;

    public List<Deporte> listarDeportes() {
        return repositorio.findByEstado("activo");
    }

    public void agregarDeporte(Deporte deporte) {
        repositorio.save(deporte);
    }
    
    
    public Deporte buscarPorId(Long id){
        return repositorio.findById(id).get();
    }

    public void eliminarDeporte(Long id) {
        Deporte deporte = buscarPorId(id);
        deporte.setEstado("inactivo");
        repositorio.save(deporte);
    }
}
