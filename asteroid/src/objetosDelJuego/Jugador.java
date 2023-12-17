package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import entrada.Teclado;
import graficos.Assets;
import mat.Vector2D;

public class Jugador extends /*ObjetoDelJuego*/ ObjetoMoviendose{
    
    private Vector2D puntaNave;//es hacia donde esta mirando la nave

    public Jugador(Vector2D posicion,Vector2D velocidad ,BufferedImage textura){
        super(posicion, velocidad, textura);
        puntaNave = new Vector2D(0, 1);
    }

    @Override//el que borra el override 
    public void actualizar(){
        /*if(Teclado.DERECHA){
            posicion.setX(posicion.getX() + 1);//muevo uno a la derecha el jugador
        }
        if(Teclado.IZQUIERDA){
            posicion.setX(posicion.getX() - 1);//muevo uno a la derecha el jugador 
        }*/
        if(Teclado.DERECHA){
            angulo += Math.PI/20;
        }
        if(Teclado.IZQUIERDA){
            angulo -= Math.PI/20;
        }


        puntaNave = puntaNave.setDireccion(angulo - (Math.PI/2));//la hubicaion inicial de la punta de la nave
        //es 90° funciona en radianes

    }

    @Override
    public void dibujar(Graphics graficos){
        //graficos.drawImage(textura,(int)posicion.getX(), (int)posicion.getY(), null);
        Graphics2D graficos2D = (Graphics2D)graficos;

        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angulo, Assets.jugador.getWidth()/2, Assets.jugador.getHeight()/2);//obtengo el ancho y el alto de la imagen en el buffer 
        //y centro el punto de rotacion en el medio de esta por medio de la division
        graficos2D.drawImage(Assets.jugador, at, null);//dibujo la nave
    }
}
