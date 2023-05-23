package sena.deportes.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DeporteModelo {

    @Autowired
    private DeporteRepositorio repositorio;

    public List<DeporteEntidad> listarDeportes() {
        return repositorio.findByEstado("activo");
    }

    public void agregarDeporte(DeporteEntidad deporte) {
        repositorio.save(deporte);
    }

    public DeporteEntidad buscarPorId(Long id) {
        var deporteEncontrado = repositorio.findById(id);

        if (deporteEncontrado.isEmpty()) {
            return new DeporteEntidad();
        }

        return deporteEncontrado.get();
    }

    public void eliminarDeporte(Long id) {
        DeporteEntidad deporte = buscarPorId(id);

        if (deporteVacio(deporte)) {
            return;
        }

        deporte.setEstado("inactivo");
        repositorio.save(deporte);
    }

    public void editarDeporte(DeporteEntidad deporte, Long id, MultipartFile archivo) {
        DeporteEntidad deporteEditado = buscarPorId(id);

        if (deporteVacio(deporteEditado)) {
            return;
        }

        deporteEditado.setId(id);
        deporteEditado.setNombre(deporte.getNombre());
        deporteEditado.setDescripcion(deporte.getDescripcion());
        deporteEditado.setEstado("activo");

        if (!archivo.isEmpty()) {
            deporteEditado.setImagen(Helpers.subirImagen(archivo));
        }

        agregarDeporte(deporteEditado);
    }

    public boolean deporteVacio(DeporteEntidad deporte) {
        if (deporte.getId() == 0 && deporte.getNombre() == null && deporte.getDescripcion() == null && deporte.getEstado() == null && deporte.getImagen() == null) {
            return true;
        }
        
        return false;
    }
}
