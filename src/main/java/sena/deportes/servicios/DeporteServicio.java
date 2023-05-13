package sena.deportes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sena.deportes.repositorios.DeporteRepositorio;
import sena.deportes.entidades.Deporte;
import java.util.List;
import java.util.Objects;
import org.springframework.web.multipart.MultipartFile;
import sena.deportes.helpers.Helpers;

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

    public Deporte buscarPorId(Long id) {
        var deporteEncontrado = repositorio.findById(id);

        if (deporteEncontrado.isEmpty()) {
            return new Deporte();
        }

        return deporteEncontrado.get();
    }

    public void eliminarDeporte(Long id) {
        Deporte deporte = buscarPorId(id);

        if (deporteVacio(deporte)) {
            return;
        }

        deporte.setEstado("inactivo");
        repositorio.save(deporte);
    }

    public void editarDeporte(Deporte deporte, Long id, MultipartFile archivo) {
        Deporte deporteEditado = buscarPorId(id);

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

    public boolean deporteVacio(Deporte deporte) {
        if (deporte.getId() == 0 && deporte.getNombre() == null && deporte.getDescripcion() == null && deporte.getEstado() == null && deporte.getImagen() == null) {
            return true;
        }
        
        return false;
    }
}
