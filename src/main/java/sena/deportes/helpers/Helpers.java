package sena.deportes.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

public class Helpers {

    public static String subirImagen(MultipartFile archivo) {
        try {
            if(archivo == null || archivo.isEmpty()){
                return "images\\deportes\\default.png";
            }
            String nombreArchivo = Math.random() + ".png";
            String directorio = "C:\\Users\\USUARIO CAB\\Desktop\\Sena\\Spring\\Deportes\\src\\main\\resources\\static\\images\\deportes\\" + nombreArchivo;
            String directorioBasesDeDatos = "images\\deportes\\" + nombreArchivo;

            byte[] imagen = archivo.getBytes();
            Files.write(Paths.get(directorio), imagen);

            return directorioBasesDeDatos;
        } catch (Exception ex) {
            return "";
        }
    }
}
