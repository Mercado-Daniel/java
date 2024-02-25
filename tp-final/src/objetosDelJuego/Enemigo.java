package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import estados.EstadoDeJuego;
import matematicas.Vector2D;
import nivel.Nivel;
import sonidos.ReproductorSonidos;


public class Enemigo extends ObjetoQueSemueve{
    private Cronometro cronometro = new Cronometro();
    private int movimiento ; 
    private ReproductorSonidos sonidoAplastar;

    public Enemigo(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray, Nivel nivel){
        super(posicion, textura, estadoDeJuego, texturaArray, nivel);
        setCaida(Constantes.GRAVEDAD);
        this.velocidad = 1;
        movimiento = izquierda;
        sonidoAplastar = new ReproductorSonidos("assets/music/aplastar.wav"); 
    }
    
    @Override
    public void actualizar(){

        if(movimiento < 1){
            if(!cronometro.estaCorriendo() ){
                if(textura == texturaArray[0]){
                    textura = texturaArray[1];
                }else{
                    textura = texturaArray[0];
                }
            }
            cronometro.arranque(100);
        }else{
            if(!cronometro.estaCorriendo() ){
                if(textura == texturaArray[2]){
                    textura = texturaArray[3];
                }else{
                    textura = texturaArray[2];
                }
            }
            cronometro.arranque(100);
        }

        posicion.setEjeY(posicion.getEjeY() + caida);

        if(colisionAbajo() instanceof Ladrillo) {
            setCaida(0);
        }else{
            setCaida(Constantes.GRAVEDAD);
        }
        
        if(posicion.getEjeX() <= Constantes.ANCHO){
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
        }
        
        if(colisionArriba() instanceof Jugador){
            sonidoAplastar.reproducir();
            destruir();
        }
        
        
        cronometro.actualizar();
    }

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);//convierto la posicion a entero
    }

    
}
