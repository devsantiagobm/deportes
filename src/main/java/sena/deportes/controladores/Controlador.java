package sena.deportes.controladores;

import sena.deportes.helpers.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sena.deportes.entidades.Deporte;
import sena.deportes.servicios.DeporteServicio;

@Controller
public class Controlador {

    @Autowired
    private DeporteServicio servicio;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

    @GetMapping("/deportes")
    public String deportes(Model model) {
        model.addAttribute("deportes", servicio.listarDeportes());
        model.addAttribute("deportesLongitud", servicio.listarDeportes().size());
        return "deportes/deportes";
    }

    @GetMapping("/deportes/{id}")
    public String mostrarDeporte(@PathVariable Long id, Model model) {
        model.addAttribute("deportes", servicio.buscarPorId(id));
        return "deportes/deporte";
    }

    @GetMapping("/deportes/agregar")
    public String formularioAgregarDeporte(Model model) {
        Deporte nuevoDeporte = new Deporte();
        nuevoDeporte.setEstado("activo");
        model.addAttribute("deporte", nuevoDeporte);
        return "deportes/agregar";
    }

    @PostMapping("/deportes/agregar")
    public String agregarDeporte(@RequestParam("archivo") MultipartFile archivo, @ModelAttribute("deporte") Deporte deporte) {
        String directorioImagen = Helpers.subirImagen(archivo);
        deporte.setImagen(directorioImagen);
        servicio.agregarDeporte(deporte);
        return "redirect:/deportes";
    }

    @GetMapping("/deportes/eliminar/{id}")
    public String eliminarDeporte(@PathVariable Long id, @ModelAttribute("deporte") Deporte deporte, Model model) {
        servicio.eliminarDeporte(id);
        return "redirect:/deportes";
    }

    @GetMapping("/deportes/editar/{id}")
    public String formularioEditarDeporte(@PathVariable Long id, Model model) {
        model.addAttribute("deporte", servicio.buscarPorId(id));
        return "deportes/editar";
    }

    @PostMapping("deportes/editar/{id}")
    public String editarDeporte(@RequestParam("archivo") MultipartFile archivo, @PathVariable Long id, @ModelAttribute("deporte") Deporte deporte) {
        Deporte deporteEditado = servicio.buscarPorId(id);

        deporteEditado.setId(id);
        deporteEditado.setNombre(deporte.getNombre());
        deporteEditado.setDescripcion(deporte.getDescripcion());
        deporteEditado.setEstado("activo");

        if (!archivo.isEmpty()) {
            deporteEditado.setImagen(Helpers.subirImagen(archivo));
        }

        servicio.agregarDeporte(deporteEditado);

        return "redirect:/deportes";
    }
}
