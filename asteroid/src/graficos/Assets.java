package graficos;

import java.awt.image.*;

public class Assets {

    public static BufferedImage jugador;//almacena la imagen del jugador
    //efectos
    public static BufferedImage velocidad;
    //lasers
    public static BufferedImage laserAzul;
    public static BufferedImage laserVerde;
    public static BufferedImage laserRojo;

    public static void iniciar(){
        jugador = GraficosRen.cargarImagen("/naves/player.png");//paso la direccion de la imagen
        velocidad = GraficosRen.cargarImagen("/efectos/fire08.png");
        laserAzul = GraficosRen.cargarImagen("/lasers/laserBlue01.png");
        laserVerde = GraficosRen.cargarImagen("/lasers/laserGreen11.png");
        laserRojo = GraficosRen.cargarImagen("/lasers/laserRed01.png");
    }
}
