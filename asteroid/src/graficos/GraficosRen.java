package graficos;

import java.awt.image.*;
import java.io.IOException;

import javax.imageio.*;

public class GraficosRen {
    public static BufferedImage cargarImagen(String path){//recibe una direccion de imagen
        try {
            System.out.println(path);
            return ImageIO.read(GraficosRen.class.getResource(path));//devuelve la imagen de la direccion
            
        } catch (IOException e) {//en caaso de error lo imprime
            e.printStackTrace();
        }
        return null;//en caso de que no entre en el try catch devuelve null

    }
}
