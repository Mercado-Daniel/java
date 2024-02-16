package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;

public abstract class ObjetoDelJuego {//es una clase abstracta
    protected BufferedImage textura;
    protected Vector2D posicion;
    protected int ancho;
    protected int alto;
    protected EstadoDeJuego estadoDeJuego;
    protected int caida;

    public ObjetoDelJuego(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego){
        this.posicion = posicion;//es un vector que guarda las cordenadas del asset en la ventana
        this.textura = textura;//es el sprite del objeto en el momento dado
        this.estadoDeJuego = estadoDeJuego;
        ancho = textura.getWidth();
        alto = textura.getHeight();
        caida = 0;
    }

    public abstract void actualizar();
    public abstract void dibujar(Graphics graficos);

    public Vector2D getPosicion(){
        return this.posicion;
    }

    public void setPosicion(Vector2D posicion){
        this.posicion = posicion;
    }


    public void setCaida(int caida){
        this.caida = caida;
    }


    public Rectangle rectangulo(){
        return new Rectangle((int)posicion.getEjeX(), (int)posicion.getEjeY(), ancho , alto);
    }

    protected void destruir(){
        estadoDeJuego.getObjetos().remove(this);//destruye este objeto
    }


}
