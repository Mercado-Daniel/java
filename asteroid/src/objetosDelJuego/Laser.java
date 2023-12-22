package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import mat.Vector2D;

public class Laser extends ObjetoMoviendose {
    
    public Laser(Vector2D posicion, Vector2D velocidad, double velocidadMaxima, double angulo, BufferedImage textura){
        super(posicion, velocidad, velocidadMaxima, textura);
        this.angulo = angulo;
        this.velocidad = velocidad.mulPorEscalar(velocidadMaxima);
    }

    @Override
    public void actualizar(){
        posicion = posicion.suma(velocidad);//la velocidad siempro sera constante
        //por lo tanto no se agrega en este metodo
    }

    @Override
    public void dibujar(Graphics graficos){
        Graphics2D graficos2D = (Graphics2D)graficos;
        
        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angulo);

        graficos2D.drawImage(textura, at, null);

    }
}
