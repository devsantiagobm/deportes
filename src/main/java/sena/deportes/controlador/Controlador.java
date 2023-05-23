package sena.deportes.controlador;

import sena.deportes.modelo.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sena.deportes.modelo.DeporteEntidad;
import sena.deportes.modelo.DeporteModelo;

@Controller
public class Controlador {

    @Autowired
    private DeporteModelo servicio;

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
        return "deportes";
    }

    @GetMapping("/deportes/{id}")
    public String mostrarDeporte(@PathVariable Long id, Model model) {
        model.addAttribute("deportes", servicio.buscarPorId(id));
        return "deporte";
    }

    @GetMapping("/deportes/agregar")
    public String formularioAgregarDeporte(Model model) {
        model.addAttribute("deporte", new DeporteEntidad());
        return "agregar";
    }

    @PostMapping("/deportes/agregar")
    public String agregarDeporte(@RequestParam("archivo") MultipartFile archivo, @ModelAttribute("deporte") DeporteEntidad deporte) {
        String directorioImagen = Helpers.subirImagen(archivo);
        deporte.setImagen(directorioImagen);
        deporte.setEstado("activo");
        servicio.agregarDeporte(deporte);
        return "redirect:/deportes";
    }
    

    @GetMapping("/deportes/eliminar/{id}")
    public String eliminarDeporte(@PathVariable Long id) {
        servicio.eliminarDeporte(id);
        return "redirect:/deportes";
    }

    @GetMapping("/deportes/editar/{id}")
    public String formularioEditarDeporte(@PathVariable Long id, Model model) {
        model.addAttribute("deporte", servicio.buscarPorId(id));
        return "editar";
    }

    @PostMapping("deportes/editar/{id}")
    public String editarDeporte(@RequestParam("archivo") MultipartFile archivo, @PathVariable Long id, @ModelAttribute("deporte") DeporteEntidad deporte) {
        servicio.editarDeporte(deporte, id, archivo);
        return "redirect:/deportes";
    }

}
