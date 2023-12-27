package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import mat.Vector2D;
import main.Window;

public class Laser extends ObjetoMoviendose {
    
    public Laser(Vector2D posicion, Vector2D velocidad, double velocidadMaxima, double angulo, BufferedImage textura, EstadoDeJuego estadoDeJuego){
        super(posicion, velocidad, velocidadMaxima, textura, estadoDeJuego);
        this.angulo = angulo;
        this.velocidad = velocidad.mulPorEscalar(velocidadMaxima);
    }

    @Override
    public void actualizar(){
        posicion = posicion.suma(velocidad);//la velocidad siempro sera constante
        //por lo tanto no se agrega en este metodo
        if(posicion.getX() < 0 || posicion.getX() > Window.ANCHO || posicion.getY() < 0 || posicion.getY() > Window.ALTO){
            estadoDeJuego.getObjetosQueSeMueven().remove(this);//de esta forma elimino el laser del arrayList
            //para que estos no existan infinitamente y saturen la memoria
        }
    }

    @Override
    public void dibujar(Graphics graficos){
        Graphics2D graficos2D = (Graphics2D)graficos;
        
        at = AffineTransform.getTranslateInstance(posicion.getX() - ancho/2, posicion.getY());
        at.rotate(angulo, ancho/2, 0);

        graficos2D.drawImage(textura, at, null);

    }
}
