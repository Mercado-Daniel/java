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
        jugador = new Jugador(new Vector2D(96, 96), Assets.jugadorMario[0], this,Assets.jugadorMario, nivel);
        //nivelUno = new NivelUno(jugador);
        objetos.add(jugador);
        
    }

    public void actualizar(){
        for(int i = 0; i < objetos.size(); i++){
            objetos.get(i).actualizar();
        }
    }

    public void dibujar(Graphics graficos){
        
        //jugador.dibujar(graficos);//dibujo el jugador en el estado de juego 
        for(int i = 0; i < objetos.size(); i++){
            objetos.get(i).dibujar(graficos);
        }
        nivel.dibujar(graficos);
    }

    public ArrayList<ObjetoDelJuego> getObjetos(){
        return objetos;
    }

    public void addObjetos(ObjetoDelJuego objeto){
        objetos.add(objeto);
    }

   
}
