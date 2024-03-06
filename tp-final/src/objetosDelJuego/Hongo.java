package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import graficos.Assets;
import matematicas.Vector2D;
import nivel.Nivel;
import sonidos.ReproductorSonidos;

public class Hongo extends ObjetoQueSemueve {
    private int movimiento; 
    private int velocidad;
    private String tipoHongo;
    private ReproductorSonidos sonidoup1,sonidoDestruirCaja;
    public Hongo(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, Nivel nivel, String tipoHongo){
        super(posicion, textura, estadoDeJuego, null, nivel);
        movimiento = derecha;
        velocidad = 1;
        this.tipoHongo = tipoHongo;
        sonidoup1 = new ReproductorSonidos("assets/music/1-up.wav");
        sonidoDestruirCaja = new ReproductorSonidos("assets/music/crecer.wav");
    }

    @Override
    public void actualizar() {
        posicion.setEjeY(posicion.getEjeY() + caida);
        if(colisionAbajo() instanceof Ladrillo || colisionAbajo() instanceof CajaInvisible){
            setCaida(0);
        }else{
            setCaida(Constantes.GRAVEDAD);
        }

        posicion.setEjeX(posicion.getEjeX() + movimiento * velocidad);
        if(colisionIzquierda() instanceof Ladrillo ){
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
                sonidoup1.reproducir();

            }
            if(tipoHongo == "grande"){
                estadoDeJuego.getJugador().setTexturas(Assets.marioGrande[0], Assets.marioGrande);
                sonidoDestruirCaja.reproducir();
            }
            destruir();
        }

    }

    

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);//convierto la posicion a entero
    }
}

