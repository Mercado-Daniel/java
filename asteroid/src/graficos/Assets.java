package graficos;

import java.awt.image.*;

public class Assets {

    public static BufferedImage jugador;//almacena la imagen del jugador

    public static void iniciar(){
        jugador = GraficosRen.cargarImagen("/naves/player.png");//paso la direccion de la imagen
    }
}
