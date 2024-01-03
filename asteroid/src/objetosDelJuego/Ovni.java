package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import estados.EstadoDeJuego;
import mat.Vector2D;

public class Ovni extends ObjetoMoviendose{
    public static final int ANCHO = 800;
	public static final int ALTO = 600;
    public static final int RADIO_DEL_NODO = 160;
    public static final double MASA_OBNI = 60;
    private ArrayList<Vector2D> camino;
    private Vector2D nodoActual;
    private int indice;
    private boolean siguiendo;
    
    public Ovni(Vector2D posicion, Vector2D velocidad, double velocidadMaxima, BufferedImage textura, ArrayList<Vector2D> camino, EstadoDeJuego estadoDeJuego){
        super(posicion, velocidad, velocidadMaxima, textura, estadoDeJuego);
        this.camino = camino;
        indice = 0;
        siguiendo = true;
    }

    public Vector2D caminoASeguir(){
        nodoActual = camino.get(indice);
        double distanciaAlNodo = nodoActual.resta(getCentro()).getMagnitud();

        if(distanciaAlNodo < RADIO_DEL_NODO){
            indice ++;
            if(indice >= camino.size()){
                siguiendo = false;
            }
        }
        return fuerzaSeek(nodoActual);
    }

    public Vector2D fuerzaSeek(Vector2D objetivo){
        Vector2D velocidadDeseada = objetivo.resta(getCentro());
        velocidadDeseada = velocidadDeseada.normalizar().mulPorEscalar(velocidadMaxima);
        return velocidadDeseada.resta(velocidad);
    }

    @Override
    public void actualizar(){
        Vector2D siguiendoCamino;

        if(siguiendo){
            siguiendoCamino = caminoASeguir();
        }else{
            siguiendoCamino = new Vector2D();
        }
        siguiendoCamino = siguiendoCamino.mulPorEscalar(1/MASA_OBNI);
        velocidad = velocidad.suma(siguiendoCamino);

        velocidad = velocidad.limite(velocidadMaxima);

        posicion = posicion.suma(velocidad);

        if(posicion.getX() > ANCHO || posicion.getY() > ALTO || posicion.getX() < 0 || posicion.getY() < 0){
            destruccuion();
        }

        angulo += 0.05;
        colicionaCon();
    }

    @Override
    public void dibujar(Graphics graficos){
        Graphics2D graficos2D = (Graphics2D)graficos;
        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angulo, ancho/2, alto/2);
        graficos2D.drawImage(textura, at, null);
    }
}
