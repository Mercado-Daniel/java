package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import matematicas.Vector2D;
import nivel.Nivel;
import entrada.Teclado;
import estados.EstadoDeJuego;
import sonidos.ReproductorSonidos;

public class Jugador extends ObjetoQueSemueve{

    private Cronometro cronometro = new Cronometro();
    private Cronometro inputLag = new Cronometro();
    private int contador = 0;
    private int monedas = 0;
    private ReproductorSonidos sonidoSaltar,sonidoMoneda,sonidoMuerte,sonidoSaltoDoble,sonidoColisionLadrillo;
    
    public Jugador(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray, Nivel nivel){
        super(posicion, textura, estadoDeJuego, texturaArray, nivel);
        velocidad = 6;
        sonidoSaltar = new ReproductorSonidos("assets/music/salto_comun.wav");
        sonidoSaltoDoble = new ReproductorSonidos("assets/music/salto_doble.wav");
        sonidoMoneda = new ReproductorSonidos("assets/music/coin.wav");
        sonidoMuerte = new ReproductorSonidos("assets/music/mariodie.wav");
        sonidoColisionLadrillo = new ReproductorSonidos("assets/music/colision.wav");
    }
    
    @Override
    public void actualizar(){
        if(Teclado.SALTAR && colisionAbajo() instanceof Ladrillo){
            sonidoSaltar.reproducir();
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
                // sonidoSaltar.detener();
                //  sonidoSaltoDoble.reproducir();
                if (colisionDerecha() instanceof Ladrillo || colisionIzquierda() instanceof Ladrillo) {
                    posicion.setEjeX(posicion.getEjeX());
                }
            }
        }else{
            posicion.setEjeY(posicion.getEjeY() + caida);
        }
        if(Teclado.DERECHA && !inputLag.estaCorriendo()){
            //animacion
            inputLag.arranque(60);
            if(!cronometro.estaCorriendo()){
                if(textura == texturaArray[0]){
                    textura = texturaArray[6];
                }else{
                    textura = texturaArray[0];
                }
            }
            if(!(colisionDerecha() instanceof Ladrillo)){
                    if(Teclado.CORRER ){
                        if(colisionCentro() instanceof Ladrillo){
                            posicion.setEjeX(posicion.getEjeX() + izquierda * 16);
                        }else{
                            if(posicion.getEjeX() <= Constantes.ANCHO/2){
                                posicion.setEjeX(posicion.getEjeX() + derecha * 10);
                            }else{
                                estadoDeJuego.moverCamaraDerechaCorrer();
                            }
                        }
                    }else{
                        if(colisionCentro() instanceof Ladrillo){
                            posicion.setEjeX(posicion.getEjeX() + izquierda * 16);
                        }else{
                            if(posicion.getEjeX() <= Constantes.ANCHO/2){
                                posicion.setEjeX(posicion.getEjeX() + derecha * velocidad);
                            }else{
                                estadoDeJuego.moverCamaraDerecha();
                            }
                        }
                    }
                
            }else{
                sonidoColisionLadrillo.reproducir();}
            cronometro.arranque(100);
            //fin animacion
          
        }
        if(Teclado.IZQUIERDA && !inputLag.estaCorriendo()){
            if(!cronometro.estaCorriendo()){
                if(textura == texturaArray[15]){
                    textura = texturaArray[21];
                }else{
                    textura = texturaArray[15];
                }
            }
            inputLag.arranque(60);
            if(!(colisionIzquierda() instanceof Ladrillo)){
                if(Teclado.CORRER ){
                    //estadoDeJuego.moverCamaraIzquierdaCorrer();
                    if(colisionCentro() instanceof Ladrillo){
                        posicion.setEjeX(posicion.getEjeX() + derecha * 16);
                    }else{
                        posicion.setEjeX(posicion.getEjeX() + izquierda * 10);
                    }
                }else{
                    //estadoDeJuego.moverCamaraIzquierda();
                    if(posicion.getEjeX() > 0){
                        if(colisionCentro() instanceof Ladrillo){
                            posicion.setEjeX(posicion.getEjeX() + derecha * 16);
                        }else{
                            posicion.setEjeX(posicion.getEjeX() + izquierda * velocidad);
                        }
                    }
                }
            }else{
            sonidoColisionLadrillo.reproducir();}
            cronometro.arranque(100);
        }
        inputLag.actualizar();
        if(colisionGeneral() instanceof Monedas){
            sonidoMoneda.reproducir();
            colisionGeneral().destruir();
            monedas += 1;
        }
        if(colisionDerecha() instanceof Enemigo || colisionArriba() instanceof Enemigo || colisionIzquierda() instanceof Enemigo || colisionCentro() instanceof Enemigo){
            sonidoMuerte.reproducir(); 
            destruir();
        }
        if(posicion.getEjeY() >= Constantes.ALTO - 64){
            sonidoMuerte.reproducir(); 
            destruir();
        }
        if(posicion.getEjeX() <= 0){
            posicion.setEjeX(posicion.getEjeX() + derecha * 14);
        }

        cronometro.actualizar();
        System.out.println(monedas);
    }

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);//convierto la posicion a entero
    }

}
