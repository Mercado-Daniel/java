package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


import graficos.Assets;
import matematicas.Vector2D;
import nivel.Nivel;
import objetosDelJuego.Cronometro;
import objetosDelJuego.Jugador;
import objetosDelJuego.ObjetoDelJuego;

public class EstadoDeJuego {
    //creo un jugador en el estado de juego
    private Cronometro cronometro = new Cronometro();
    private Jugador jugador;
    private Nivel nivel = new Nivel(this);
    private ArrayList<ObjetoDelJuego> objetos = new ArrayList<ObjetoDelJuego>();//almacena todos los objetos
    //que heredan de objetodeljuego
    

    public EstadoDeJuego(){
        //instancio el jugador y le asigno su posicion y el asset a utilizar, en el futuro se debe implementar una animacion
        jugador = new Jugador(new Vector2D(96, 500), Assets.jugadorMario[0], this,Assets.jugadorMario, nivel);
        //nivelUno = new NivelUno(jugador);
        objetos = nivel.getLadrillos();
        objetos.add(jugador);
        //objetos.add(enemigo);
    }

    public void actualizar(){
        for(int i = 0; i < objetos.size(); i++){
            objetos.get(i).actualizar();
        }
        //System.out.println(colision());
    }

    public void reiniciar(){
       
        if(objetos.contains(jugador)){
            jugador.setPosicion(new Vector2D(96, 400));
        }else{
            jugador = new Jugador(new Vector2D(96, 500), Assets.jugadorMario[0], this,Assets.jugadorMario, nivel);
        }
        for(int i = 0; i < objetos.size(); i++){
            objetos.remove(objetos.get(i));
        }
        //instancio el jugador y le asigno su posicion y el asset a utilizar, en el futuro se debe implementar una animacion
        nivel = new Nivel(this);
        //nivelUno = new NivelUno(jugador);
        objetos = nivel.getLadrillos();
        objetos.add(jugador);
        //objetos.add(enemigo);
    }

    public void dibujar(Graphics graficos){
        
        
        for(int i = 0; i < objetos.size(); i++){
            objetos.get(i).dibujar(graficos);
            /*graficos.setColor(Color.BLACK);
            graficos.drawRect((int)objetos.get(i).rectangulito().getMinX(),(int)objetos.get(i).rectangulito().getMinY(), (int)objetos.get(i).rectangulito().getMaxX(), (int)objetos.get(i).rectangulito().getMaxY());

            graficos.setColor(Color.RED);
            graficos.drawRect((int)objetos.get(i).rectangulo().getMinX(),(int)objetos.get(i).rectangulo().getMinY(), (int)objetos.get(i).rectangulo().getMaxX(), (int)objetos.get(i).rectangulo().getMaxY());*/
        }
        dibujarPuntanje(graficos);
        dibujarMonedas(graficos);
        dibujarVidas(graficos);
    }

    public ArrayList<ObjetoDelJuego> getObjetos(){
        return objetos;
    }

    public void addObjetos(ObjetoDelJuego objeto){
        objetos.add(objeto);
    }

    public void moverCamaraDerecha(){
        for(int i=0; i< objetos.size(); i++){
            if(!(objetos.get(i) instanceof Jugador)){
                objetos.get(i).getPosicion().setEjeX(objetos.get(i).getPosicion().getEjeX() - (1*jugador.velocidad));
            }
        }
    }
    public void moverCamaraIzquierda(){
        for(int i=0; i< objetos.size(); i++){
            if(!(objetos.get(i) instanceof Jugador)){
                objetos.get(i).getPosicion().setEjeX(objetos.get(i).getPosicion().getEjeX() + (1*jugador.velocidad));
            }
        }
    }

    public void moverCamaraDerechaCorrer(){
        for(int i=0; i< objetos.size(); i++){
            if(!(objetos.get(i) instanceof Jugador)){
                objetos.get(i).getPosicion().setEjeX(objetos.get(i).getPosicion().getEjeX() - (2 *jugador.velocidad));
            }
        }
    }
    public void moverCamaraIzquierdaCorrer(){
        for(int i=0; i< objetos.size(); i++){
            if(!(objetos.get(i) instanceof Jugador)){
                objetos.get(i).getPosicion().setEjeX(objetos.get(i).getPosicion().getEjeX() + (2*jugador.velocidad));
            }
        }
    }

    private void dibujarPuntanje(Graphics graficos){
        Vector2D posicion = new Vector2D(650, 25);
        String puntajeAString = Integer.toString(jugador.getPuntaje());
        for(int i=0; i < puntajeAString.length(); i++){
            graficos.drawImage(Assets.numeros[Integer.parseInt(puntajeAString.substring(i, i + 1))], 
                (int)posicion.getEjeX(), 
                (int)posicion.getEjeY(), null);//de esta foma dibujo el asset correspondiente al numero
            posicion.setEjeX(posicion.getEjeX() + 20);//asi los numeros no se dibujan uno arriba del otro
        }
    }

    private void dibujarMonedas(Graphics graficos){
        Vector2D posicionMoneda = new Vector2D(350, 18);
        graficos.drawImage(Assets.moneda[0], (int)posicionMoneda.getEjeX(), (int)posicionMoneda.getEjeY() , null);
        Vector2D posicion = new Vector2D(380, 25);
        String monedaAString = Integer.toString(jugador.getMonedas());
        for(int i=0; i < monedaAString.length(); i++){
            graficos.drawImage(Assets.numeros[Integer.parseInt(monedaAString.substring(i, i + 1))], 
                (int)posicion.getEjeX(), 
                (int)posicion.getEjeY(), null);//de esta foma dibujo el asset correspondiente al numero
            posicion.setEjeX(posicion.getEjeX() + 20);//asi los numeros no se dibujan uno arriba del otro
        }
    }

    private void dibujarVidas(Graphics graficos){
        Vector2D posicionVida = new Vector2D(4, 25);
        graficos.drawImage(Assets.vida, (int)posicionVida.getEjeX(), (int)posicionVida.getEjeY() , null);
        Vector2D posicion = new Vector2D(20, 25);
        String vidaAString = Integer.toString(jugador.getVidas());
        for(int i=0; i < vidaAString.length(); i++){
            graficos.drawImage(Assets.numeros[Integer.parseInt(vidaAString.substring(i, i + 1))], 
                (int)posicion.getEjeX(), 
                (int)posicion.getEjeY(), null);//de esta foma dibujo el asset correspondiente al numero
            posicion.setEjeX(posicion.getEjeX() + 20);//asi los numeros no se dibujan uno arriba del otro
        }
    }

}
