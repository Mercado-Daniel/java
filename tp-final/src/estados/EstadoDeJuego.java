package estados;


import java.awt.Graphics;
import java.util.ArrayList;


import graficos.Assets;
import matematicas.Vector2D;
import nivel.Nivel;

import objetosDelJuego.Jugador;
import objetosDelJuego.ObjetoDelJuego;

public class EstadoDeJuego {
    //creo un jugador en el estado de juego
    
    private Jugador jugador;
    private Nivel nivel ;
    private int numeroNivel = 1;
    private int niveles = 2;
    private String archivoNivel = "assets/niveles/nivel-1.txt" ;
    //almacena todos los objetos que heredan de objetodeljuego
    private ArrayList<ObjetoDelJuego> objetos;
    

    public EstadoDeJuego(){
        nivel = new Nivel(this, archivoNivel);
        objetos = new ArrayList<ObjetoDelJuego>();
        //instancio el jugador y le asigno su posicion y el asset a utilizar, en el futuro se debe implementar una animacion
        jugador = new Jugador(new Vector2D(96, 500), Assets.jugadorMario[0], this,Assets.jugadorMario, nivel);
        objetos = nivel.getLadrillos();
        objetos.add(jugador);
       
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
        nivel = new Nivel(this, archivoNivel);
        objetos = nivel.getLadrillos();
        objetos.add(jugador);
        
    }

    public void pasarNivel(){
        if(numeroNivel < niveles){
            numeroNivel += 1; 
        }else{
            numeroNivel = 1;
        }
        archivoNivel = "assets/niveles/nivel-"+ numeroNivel +".txt";
        jugador.setPosicion(new Vector2D(96, 400));
        for(int i = 0; i < objetos.size(); i++){
            objetos.remove(objetos.get(i));
        }
        //instancio el jugador y le asigno su posicion y el asset a utilizar, en el futuro se debe implementar una animacion
        nivel = new Nivel(this, archivoNivel);
        objetos = nivel.getLadrillos();
        objetos.add(jugador);
    }

    public void dibujar(Graphics graficos){
        
        
        for(int i = 0; i < objetos.size(); i++){
            objetos.get(i).dibujar(graficos);   
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

    private void moverse(int num){
        for(int i=0; i< objetos.size(); i++){
            if(!(objetos.get(i) instanceof Jugador)){
                objetos.get(i).getPosicion().setEjeX(objetos.get(i).getPosicion().getEjeX() - (num*jugador.velocidad));
            }
        }
    }
    public void moverCamaraDerecha(){
        moverse(1);
    }


    public void moverCamaraDerechaCorrer(){
        moverse(2);
    }
    

    private void dibujadoPosicion(Graphics graficos, int num, int ejex, int ejey ){
        Vector2D posicion = new Vector2D(ejex,ejey);
        String puntajeAString = Integer.toString(num);
        for(int i=0; i < puntajeAString.length(); i++){
            graficos.drawImage(Assets.numeros[Integer.parseInt(puntajeAString.substring(i, i + 1))], 
                (int)posicion.getEjeX(), 
                (int)posicion.getEjeY(), null);//de esta foma dibujo el asset correspondiente al numero
            posicion.setEjeX(posicion.getEjeX() + 20);//asi los numeros no se dibujan uno arriba del otro
        }
    }



    private void dibujarPuntanje(Graphics graficos){
        dibujadoPosicion(graficos,jugador.getPuntaje(),650, 25);
        
        
    }

    private void dibujarMonedas(Graphics graficos){
        Vector2D posicionMoneda = new Vector2D(350, 18);
        graficos.drawImage(Assets.moneda[0], (int)posicionMoneda.getEjeX(), (int)posicionMoneda.getEjeY() , null);
        dibujadoPosicion(graficos,jugador.getMonedas(),380, 25);
    }

    private void dibujarVidas(Graphics graficos){
        Vector2D posicionVida = new Vector2D(4, 25);
        graficos.drawImage(Assets.vida, (int)posicionVida.getEjeX(), (int)posicionVida.getEjeY() , null);
        dibujadoPosicion(graficos,jugador.getVidas(),20, 25);
    }

    public Jugador getJugador(){
        return jugador;
    }

}
