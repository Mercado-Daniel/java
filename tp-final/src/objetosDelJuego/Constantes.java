package objetosDelJuego;

public class Constantes {

    public final static String NOMBRE = "Mario";

    //tamaño pantalla
    public final static int ANCHO = 800;
    public final static int ALTO = 600;



    //establecer fps
    public final static int NANOSEGUNDOS_POR_SEGUNDO = 1000000000;
    public final static byte FPS_OBJETIVO = 60;
    public final static double NANOSEGUNDOS_POR_FPS = NANOSEGUNDOS_POR_SEGUNDO / FPS_OBJETIVO; 

    //caida
    public final static int GRAVEDAD = 4;

    //tiles
    public final static int ANCHO_TILE = 32;
    public final static int ALTO_TILE = 32;

}
