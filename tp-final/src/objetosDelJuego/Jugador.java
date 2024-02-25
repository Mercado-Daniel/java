package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import matematicas.Vector2D;
import nivel.Nivel;
import entrada.Teclado;
import estados.EstadoDeJuego;
import graficos.Assets;
import sonidos.ReproductorSonidos;

public class Jugador extends ObjetoQueSemueve{

    private Cronometro cronometro = new Cronometro();
    private LadrilloIndestructible ladrilloIndestructible;
    private int contador = 0;
    private int monedas = 0;
    private int puntaje = 0;
    private int vidas = 5;
    private boolean fin = false;
    private boolean chocaInvisible = false;
    private ReproductorSonidos sonidoSaltar,sonidoMoneda,sonidoMuerte,sonidoColisionLadrillo;
    
    public Jugador(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray, Nivel nivel){
        super(posicion, textura, estadoDeJuego, texturaArray, nivel);
        velocidad = 2;
        sonidoSaltar = new ReproductorSonidos("assets/music/salto_comun.wav");
        sonidoMoneda = new ReproductorSonidos("assets/music/coin.wav");
        sonidoMuerte = new ReproductorSonidos("assets/music/mariodie.wav");
        sonidoColisionLadrillo = new ReproductorSonidos("assets/music/colision.wav");
    }
    
    @Override
    public void actualizar(){
        if(Teclado.SALTAR && colisionAbajo() instanceof Ladrillo && !fin){
            animacionSaltar();
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
                if(colisionArriba() instanceof LaCaja ){
                    chocaCaja();
                }
                if(colisionArriba() instanceof CajaInvisible && !chocaInvisible){
                    chocaInvisible = true;
                    chocaInvisible();
                }
                posicion.setEjeY(posicion.getEjeY() + caida);
               
                if (colisionDerecha() instanceof Ladrillo || colisionIzquierda() instanceof Ladrillo) {
                    posicion.setEjeX(posicion.getEjeX());
                }
            }
        }else{
            posicion.setEjeY(posicion.getEjeY() + caida);
        }


        if(Teclado.DERECHA  && !fin){
            //animacion
            
            animacionDerecha();

            if(!(colisionDerecha() instanceof Ladrillo)){
                    if(Teclado.CORRER ){
                        if(colisionCentro() instanceof Ladrillo){
                            posicion.setEjeX(posicion.getEjeX() + izquierda * 8);
                        }else{
                            if(posicion.getEjeX() <= Constantes.ANCHO/2){
                                posicion.setEjeX(posicion.getEjeX() + derecha * 4);
                            }else{
                                estadoDeJuego.moverCamaraDerechaCorrer();
                            }
                        }
                    }else{
                        if(colisionCentro() instanceof Ladrillo){
                            posicion.setEjeX(posicion.getEjeX() + izquierda * 8);
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

        if(Teclado.IZQUIERDA && !fin){

            animacionIzquierda();

            if(!(colisionIzquierda() instanceof Ladrillo)){
                if(Teclado.CORRER ){
                   
                    if(colisionCentro() instanceof Ladrillo){
                        posicion.setEjeX(posicion.getEjeX() + derecha * 8);
                    }else{
                        posicion.setEjeX(posicion.getEjeX() + izquierda * 4);
                    }
                }else{
                   
                    if(posicion.getEjeX() > 0){
                        if(colisionCentro() instanceof Ladrillo){
                            posicion.setEjeX(posicion.getEjeX() + derecha * 8);
                        }else{
                            posicion.setEjeX(posicion.getEjeX() + izquierda * velocidad);
                        }
                    }
                }
            }else{
            sonidoColisionLadrillo.reproducir();}

            cronometro.arranque(100);
        }

        if(colisionGeneral() instanceof Monedas){
            sonidoMoneda.reproducir();
            colisionGeneral().destruir();
            monedas += 1;
        }

        if(colisionAbajo() instanceof Enemigo){
            puntaje += 10;
        }

        if(colisionDerecha() instanceof Enemigo || colisionArriba() instanceof Enemigo || colisionIzquierda() instanceof Enemigo || colisionCentro() instanceof Enemigo){
           muerte();
        }

        if(posicion.getEjeY() >= Constantes.ALTO - 64){
            muerte();
        }

        if(posicion.getEjeX() <= 0){
            posicion.setEjeX(posicion.getEjeX() + derecha * 14);
        }

        if(colisionCentro() instanceof Banderita){

            cronometro.arranque(100);

            while(cronometro.estaCorriendo()){
                textura = texturaArray[10];
                cronometro.actualizar();
            }

           fin = true;
            
        }

        if(colisionAbajo() instanceof Ladrillo && fin){
            animacionDerecha();
            cronometro.arranque(100);
            posicion.setEjeX(posicion.getEjeX() + derecha);
           }

        if(colisionCentro() instanceof Adornos && fin){
            cronometro.arranque(3000);
            while(cronometro.estaCorriendo()){
                cronometro.actualizar();
            }
            estadoDeJuego.pasarNivel();
            chocaInvisible = false;
            fin = false;
        }

        cronometro.actualizar();

        // System.out.println(monedas);
    }

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);//convierto la posicion a entero
    }

    private void chocaCaja(){
        sonidoMoneda.reproducir();
        ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(colisionArriba().posicion.getEjeX(), colisionArriba().posicion.getEjeY()), Assets.cajaVacia, estadoDeJuego);
        ladrilloIndestructible.Crear();
        colisionArriba().destruir();
        monedas += 1;
    }

    private void chocaInvisible(){
        Hongo hongo = new Hongo(
        new Vector2D(
            colisionArriba().posicion.getEjeX(), 
            colisionArriba().posicion.getEjeY() - 32),
        Assets.hongoVida, 
        estadoDeJuego,
        nivel,
        "vida");
        hongo.Crear();
        //colisionArriba().destruir();
        
    }

    public int getPuntaje(){
        return puntaje;
    }

    public int getMonedas(){
        return monedas;
    }

    public int getVidas(){
        return vidas;
    }

    public void setVidas(){
        vidas += 1;
    }

    private void muerte(){
        sonidoMuerte.reproducir();
        cronometro.arranque(3000);
        chocaInvisible = false;
        while(cronometro.estaCorriendo()){
            cronometro.actualizar();
        }

        if(vidas > 0){
            vidas -= 1;
            estadoDeJuego.reiniciar();
        }else{
            destruir();
            estadoDeJuego.reiniciar();
        } 

    }

    private void animacionDerecha(){

        if(!cronometro.estaCorriendo()){
            if(textura == texturaArray[0]){
                textura = texturaArray[6];
            }else{
                textura = texturaArray[0];
            }
        }

    }

    private void animacionIzquierda(){
        
        if(!cronometro.estaCorriendo()){
            if(textura == texturaArray[15]){
                textura = texturaArray[21];
            }else{
                textura = texturaArray[15];
            }
        }
    }

    private void animacionSaltar(){
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

}
