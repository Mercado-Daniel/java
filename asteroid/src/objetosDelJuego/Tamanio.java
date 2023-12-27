package objetosDelJuego;

import java.awt.image.BufferedImage;
import graficos.Assets;

public enum Tamanio {
    GRANDE(2, Assets.meteorosMedianos),
    MEDIANO(2, Assets.meteorosChicos), 
    CHICO(2, Assets.meteorosMuyChicos), 
    MUY_CHICO(0, null);

    public int cantidad;

    public BufferedImage[] texturas;

    private Tamanio(int cantidad, BufferedImage[] texturas){
        this.cantidad = cantidad;
        this.texturas = texturas;
    }
}
