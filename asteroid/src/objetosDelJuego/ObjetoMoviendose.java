package objetosDelJuego;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import mat.Vector2D;

public abstract class ObjetoMoviendose extends ObjetoDelJuego {
    
    protected Vector2D velocidad;
    protected AffineTransform at;//es como las tranformaciones en en css
    protected double angulo;

    public ObjetoMoviendose(Vector2D posicion, Vector2D velocidad, BufferedImage textura){
        super(posicion, textura);
        this.velocidad = velocidad;
        angulo = 0;
    }

}
