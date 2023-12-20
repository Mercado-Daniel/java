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
    private Vector2D aceleracion;//es el cambio en la velocidad con respecto al tiempo
    private final double ACC = 0.02;

    public Jugador(Vector2D posicion,Vector2D velocidad , double velocidadMaxima,BufferedImage textura){
        super(posicion, velocidad, velocidadMaxima,textura);
        puntaNave = new Vector2D(0, 1);
        aceleracion = new Vector2D();//incializo la aceleracion de la nave
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
        if(Teclado.ARRIBA){
           aceleracion = puntaNave.mulPorEscalar(ACC);//multiplico la punta de la nave
        //por el macro acc que es la tasa de incremento de la aceleracion para mover la 
        //nave
        }else{
            
        }

        velocidad = velocidad.suma(aceleracion);//es la velocidad con la que la nave
        // //se mueve hacia donde apunta la nave
        
        velocidad.limite(velocidadMaxima);


        puntaNave = puntaNave.setDireccion(angulo - (Math.PI/2));//la hubicaion inicial de la punta de la nave
        //es 90° funciona en radianes

        posicion = posicion.suma(velocidad);


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
