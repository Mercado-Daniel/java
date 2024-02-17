package objetosDelJuego;

import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;

public class LadrilloIndestructible extends Ladrillo{
    public LadrilloIndestructible (Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego){
        super(posicion, textura, estadoDeJuego);
    }
}