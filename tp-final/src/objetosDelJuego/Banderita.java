package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;


public class Banderita extends ObjetoDelJuego{
    public Banderita(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego){
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
        return new Rectangle((int)posicion.getEjeX() + (ancho/2), (int)posicion.getEjeY() -32, ancho/4 , alto - 32);
    }

    
}
