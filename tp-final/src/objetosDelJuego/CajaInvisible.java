package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import graficos.Assets;
import matematicas.Vector2D;
import nivel.Nivel;

public class CajaInvisible extends ObjetoQueSemueve {
    private String contenido;
    private boolean chocado = false;

    public CajaInvisible(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, Nivel nivel, String contenido){
        super(posicion, textura,estadoDeJuego, null, nivel);
        this.contenido = contenido;
    }

    @Override
    public void actualizar() {
        if(colisionAbajo() == estadoDeJuego.getJugador()){
            estadoDeJuego.getJugador().posicion.setEjeY(estadoDeJuego.getJugador().posicion.getEjeY() + caida);
            if(!chocado){
                chocado = true;
                choca();
            }
        }
    }

    @Override
    public void dibujar(Graphics graficos) {
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);

    }
    public String getContenido(){
        return contenido;
    }

    private void choca(){
        Hongo hongo = new Hongo(
        new Vector2D(
           this.posicion.getEjeX(), 
           this.posicion.getEjeY() - 32),
        Assets.hongoVida, 
        estadoDeJuego,
        nivel,
        this.contenido);
        hongo.Crear();
        //colisionArriba().destruir();
        
    }
    
}
