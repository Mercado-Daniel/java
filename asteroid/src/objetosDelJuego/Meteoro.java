package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import main.Window;
import mat.Vector2D;

public class Meteoro extends ObjetoMoviendose {
    
    private Tamanio tamanio;//uso un enum para los distintos tamaños de los meteoros

    public Meteoro(Vector2D posicion, Vector2D velocidad, double velocidadMaxima, BufferedImage textura, EstadoDeJuego estadoDeJuego, Tamanio tamanio){
        super(posicion, velocidad, velocidadMaxima, textura, estadoDeJuego);
        this.tamanio = tamanio;
        this.velocidad = velocidad.mulPorEscalar(velocidadMaxima);
    }

    @Override
    public void actualizar(){
        posicion = posicion.suma(velocidad);

         //aparesca del otro lado como en el pacman
        if(posicion.getX() > Window.ANCHO){//si la nave sale por la parte derecha
            System.out.println(Window.ANCHO);//buscar mejor nombre para window en futuro
            posicion.setX(-ancho);//aparece por la parte izquierda
        }
        if(posicion.getY() > Window.ALTO){//si sale por arriba
            posicion.setY(-alto);//aparece por abajo
        }
        if(posicion.getX() < -ancho ){//si sale por la izquierda 
            posicion.setX(Window.ANCHO);//aparece por la derecha
        }
        if(posicion.getY() < -alto){//si sale por abajo 
            posicion.setY(Window.ALTO);//aparece por arriba
        }

        angulo += Math.PI/50;
    }

    @Override
    public void destruccuion(){
        estadoDeJuego.dividirMeteoro(this);
        estadoDeJuego.sumarPuntaje(20);
        super.destruccuion();
    }

    @Override
    public void dibujar(Graphics graficos){

        Graphics2D graficos2D = (Graphics2D)graficos;

        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());

        at.rotate(angulo, ancho/2, alto/2);

        graficos2D.drawImage(textura, at, null);
    }

    public Tamanio getTamanio(){
        return tamanio;
    }


}
