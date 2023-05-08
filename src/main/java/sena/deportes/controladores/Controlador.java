package sena.deportes.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "index";
    }

    @GetMapping("/deportes")
    public String deportes(Model model) {
        model.addAttribute("deportes", servicio.listarDeportes());
        return "deportes/deportes";
    }

    @GetMapping("/deportes/agregar")
    public String formularioAgregarDeporte(Model model) {
        Deporte nuevoDeporte = new Deporte();
        nuevoDeporte.setEstado("activo");
        model.addAttribute("deporte", nuevoDeporte);
        return "deportes/agregar";
    }

    @PostMapping("/deportes/agregar")
    public String agregarDeporte(@ModelAttribute("deporte") Deporte deporte) {
        servicio.agregarDeporte(deporte);
        return "redirect:/deportes";
    }
    
    
    
    @GetMapping("/deportes/eliminar/{id}")
    public String eliminarDeporte(@PathVariable Long id , @ModelAttribute("deporte") Deporte deporte, Model model){
        servicio.eliminarDeporte(id);
        return "redirect:/deportes";
    }
}
