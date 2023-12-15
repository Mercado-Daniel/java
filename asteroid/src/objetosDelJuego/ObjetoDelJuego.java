package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mat.Vector2D;
//es una clase abstracta usada como molde para los distintos objetos del juego
public abstract class ObjetoDelJuego {//es una clase abstracta
    protected BufferedImage textura;
    protected Vector2D posicion;

    public ObjetoDelJuego(Vector2D posicion, BufferedImage textura){
        this.posicion = posicion;
        this.textura = textura;
    }

    public abstract void actualizar();//es un metodo abstracto por eso no hay {}
    public abstract void dibujar(Graphics graficos);

    public Vector2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2D posicion) {
        this.posicion = posicion;
    }

}
