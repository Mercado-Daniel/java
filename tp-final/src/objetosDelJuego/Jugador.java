package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.util.ArrayList;

import matematicas.Vector2D;
import nivel.Nivel;
//import nivel.Laberinto;
//import niveles.NivelUno;
import entrada.Teclado;
//import estados.EstadoDeJuego;
import estados.EstadoDeJuego;

public class Jugador extends ObjetoQueSemueve{

    private Cronometro cronometro = new Cronometro();
    private int contador = 0;
    private int monedas = 0;

    public Jugador(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray, Nivel nivel){
        super(posicion, textura, estadoDeJuego, texturaArray, nivel);
    }
    
    @Override
    public void actualizar(){
        if(Teclado.SALTAR && colisionAbajo() instanceof Ladrillo){
            if(!cronometro.estaCorriendo()){
                if(textura == texturaArray[15] || textura == texturaArray[21]){
                    textura = texturaArray[16];
                }
                if(textura == texturaArray[0] || textura == texturaArray [6]){
                    textura = texturaArray[1];
                }
            }
            cronometro.arranque(100);
            contador = 0;
            //caida = Constantes.GRAVEDAD;
        }
        if(colisionAbajo() instanceof Ladrillo) {
            setCaida(0);
        }else{
            setCaida(Constantes.GRAVEDAD);
        }

        if(contador <= 30 ){
            posicion.setEjeY(posicion.getEjeY() - 4);
            contador++;
            if(colisionArriba() instanceof Ladrillo){
                posicion.setEjeY(posicion.getEjeY() + caida);
                if (colisionDerecha() instanceof Ladrillo || colisionIzquierda() instanceof Ladrillo) {
                    posicion.setEjeX(posicion.getEjeX());
                }
            }
        }else{
            posicion.setEjeY(posicion.getEjeY() + caida);
        }
        if(Teclado.DERECHA){
            //animacion
            if(!cronometro.estaCorriendo()){
                if(textura == texturaArray[0]){
                    textura = texturaArray[6];
                }else{
                    textura = texturaArray[0];
                }
            }
            if(!(colisionDerecha() instanceof Ladrillo)){

                posicion.setEjeX(posicion.getEjeX() + derecha);//movimiento
            }
            cronometro.arranque(100);
            //fin animacion
        }
        if(Teclado.IZQUIERDA){
            if(!cronometro.estaCorriendo()){
                if(textura == texturaArray[15]){
                    textura = texturaArray[21];
                }else{
                    textura = texturaArray[15];
                }
            }
            if(!(colisionIzquierda() instanceof Ladrillo)){
                posicion.setEjeX(posicion.getEjeX() + izquierda);
            }
            cronometro.arranque(100);
        }
        if(colisionGeneral() instanceof Monedas){
            colisionGeneral().destruir();
            monedas += 1;
        }
        if(colisionDerecha() instanceof Enemigo || colisionArriba() instanceof Enemigo || colisionIzquierda() instanceof Enemigo){
            destruir();
        }
        cronometro.actualizar();
        System.out.println(monedas);
    }

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);//convierto la posicion a entero
    }

}
