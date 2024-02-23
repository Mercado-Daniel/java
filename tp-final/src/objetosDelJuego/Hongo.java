package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;
import nivel.Nivel;

public class Hongo extends ObjetoQueSemueve {
    private int movimiento; 
    private int velocidad;
    private String tipoHongo;
    public Hongo(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, Nivel nivel, String tipoHongo){
        super(posicion, textura, estadoDeJuego, null, nivel);
        movimiento = derecha;
        velocidad = 1;
        this.tipoHongo = tipoHongo;
    }

    @Override
    public void actualizar() {
        posicion.setEjeY(posicion.getEjeY() + caida);
        if(colisionAbajo() instanceof Ladrillo){
            setCaida(0);
        }else{
            setCaida(Constantes.GRAVEDAD);
        }

        posicion.setEjeX(posicion.getEjeX() + movimiento * velocidad);
        if(colisionIzquierda() instanceof Ladrillo){
            if(!(colisionAbajo() instanceof Ladrillo)){
                movimiento = 0;
            }else{
                movimiento = derecha;
            }
        }
        else if (colisionDerecha() instanceof Ladrillo){
            if(!(colisionAbajo() instanceof Ladrillo)){
                movimiento = 0;
            }else{
                movimiento = izquierda ;
            }
        }
        if(colisionGeneral() instanceof Jugador || 
            colisionCentro() instanceof Jugador || 
            colisionAbajo() instanceof Jugador  ||
            colisionArriba() instanceof Jugador ||
            colisionDerecha() instanceof Jugador||
            colisionIzquierda() instanceof Jugador){
            if(tipoHongo == "vida"){
                estadoDeJuego.getJugador().setVidas();
            }
            destruir();
        }

    }

    

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);//convierto la posicion a entero
    }
}
