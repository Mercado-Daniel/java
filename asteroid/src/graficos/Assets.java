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
    //meteoros
    public static BufferedImage[] meteorosGrandes = new BufferedImage[4];
    public static BufferedImage[] meteorosMedianos = new BufferedImage[2];
    public static BufferedImage[] meteorosChicos = new BufferedImage[2];
    public static BufferedImage[] meteorosMuyChicos = new BufferedImage[2];

    public static void iniciar(){
        //jugador
        jugador = GraficosRen.cargarImagen("/naves/player.png");//paso la direccion de la imagen
        //efectos
        velocidad = GraficosRen.cargarImagen("/efectos/fire08.png");
        //lasers
        laserAzul = GraficosRen.cargarImagen("/lasers/laserBlue01.png");
        laserVerde = GraficosRen.cargarImagen("/lasers/laserGreen11.png");
        laserRojo = GraficosRen.cargarImagen("/lasers/laserRed01.png");
        //meteros
        for(int i = 0; i < meteorosGrandes.length; i++){
            meteorosGrandes[i] = GraficosRen.cargarImagen("/meteoros/meteorGrey_big" + (i+1) + ".png");
        }

        for(int i = 0; i < meteorosMedianos.length; i++){
            meteorosMedianos[i] = GraficosRen.cargarImagen("/meteoros/meteorGrey_med" + (i+1) + ".png");
        }

        for(int i = 0; i < meteorosChicos.length; i++){
            meteorosChicos[i] = GraficosRen.cargarImagen("/meteoros/meteorGrey_small" + (i+1) + ".png");
        }

        for(int i = 0; i < meteorosMuyChicos.length; i++){
            meteorosMuyChicos[i] = GraficosRen.cargarImagen("/meteoros/meteorGrey_tiny" + (i+1) + ".png");
        }
    }
}
