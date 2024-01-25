package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CargarGraficos {
    public static BufferedImage cargarImagen(String ruta){
        try {
            System.out.println(ruta);
            return ImageIO.read(CargarGraficos.class.getResource(ruta));//devuelve la imagen de la direccion
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
