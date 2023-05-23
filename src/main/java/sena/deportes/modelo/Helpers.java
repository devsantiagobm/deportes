package sena.deportes.modelo;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

public class Helpers {

    public static String subirImagen(MultipartFile archivo) {
        try {
            if (archivo.isEmpty()) {
                return "images\\deportes\\default.png";
            }
            
            
            String nombreArchivo = Math.random() + ".jpg";
            String directorio = "C:\\Users\\USUARIO CAB\\Desktop\\Sena\\Spring\\Deportes\\src\\main\\resources\\static\\images\\deportes\\" + nombreArchivo;
            String directorioBasesDeDatos = "images\\deportes\\" + nombreArchivo;


            byte[] imagen = archivo.getBytes();
            Files.write(Paths.get(directorio), imagen);

            return directorioBasesDeDatos;
        } catch (Exception ex) {
            return "images\\deportes\\default.png";
        }
    }

}
