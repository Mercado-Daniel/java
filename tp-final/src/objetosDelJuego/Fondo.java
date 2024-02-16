package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;

public class Fondo extends ObjetoDelJuego {
    
    public Fondo(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego){
        super(posicion, textura, estadoDeJuego);
    }

    @Override
    public void actualizar(){

    }

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);
    }
}
