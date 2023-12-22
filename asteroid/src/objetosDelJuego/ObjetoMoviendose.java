package objetosDelJuego;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import mat.Vector2D;

public abstract class ObjetoMoviendose extends ObjetoDelJuego {
    
    protected Vector2D velocidad;
    protected AffineTransform at;//es como las tranformaciones en en css
    protected double angulo;
    protected double velocidadMaxima;
    protected int ancho;
    protected int alto;

    public ObjetoMoviendose(Vector2D posicion, Vector2D velocidad, double velocidadMaxima, BufferedImage textura){
        super(posicion, textura);
        this.velocidad = velocidad;
        this.velocidadMaxima = velocidadMaxima;
        ancho = textura.getWidth();
        alto = textura.getHeight();
        angulo = 0;
    }

}
