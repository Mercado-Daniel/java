package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mat.Vector2D;

public class Jugador extends ObjetoDelJuego{
    
    public Jugador(Vector2D posicion, BufferedImage textura){
        super(posicion, textura);
    }

    @Override//el que borra el override 
    public void actualizar(){

    }

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura,(int)posicion.getX(), (int)posicion.getY(), null);
    }
}
