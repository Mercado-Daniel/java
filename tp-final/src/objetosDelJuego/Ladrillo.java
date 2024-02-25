package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;


public class Ladrillo extends ObjetoDelJuego{
    public Ladrillo(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego){
        super(posicion, textura, estadoDeJuego);

    }

    @Override
    public void actualizar(){

    }

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);
    }

    @Override
    public Rectangle rectangulito(){
        return new Rectangle((int)posicion.getEjeX() + 2, (int)posicion.getEjeY() + 4, ancho-2 , alto-4);
    }

    
}
