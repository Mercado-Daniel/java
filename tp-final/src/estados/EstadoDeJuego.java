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

    public void dibujar(Graphics graficos){
        
        
        for(int i = 0; i < objetos.size(); i++){
            objetos.get(i).dibujar(graficos);
        }
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


    //public boolean colision(){}

}
