package estados;

import java.awt.Graphics;
import java.util.ArrayList;
//import java.util.List;

import graficos.Assets;
import matematicas.Vector2D;
import nivel.Nivel;
import objetosDelJuego.Enemigo;
import objetosDelJuego.Jugador;
import objetosDelJuego.ObjetoDelJuego;

public class EstadoDeJuego {
    //creo un jugador en el estado de juego
    private Jugador jugador;
    private Nivel nivel = new Nivel(this);
    private ArrayList<ObjetoDelJuego> objetos = new ArrayList<ObjetoDelJuego>();//almacena todos los objetos
    private Enemigo enemigo;
    //que heredan de objetodeljuego
    

    public EstadoDeJuego(){
        //instancio el jugador y le asigno su posicion y el asset a utilizar, en el futuro se debe implementar una animacion
        jugador = new Jugador(new Vector2D(96, 96), Assets.jugadorMario[0], this,Assets.jugadorMario, nivel);
        enemigo = new Enemigo(new Vector2D(128, 320), Assets.enemigo[0], this, Assets.enemigo, nivel);
        //nivelUno = new NivelUno(jugador);
        objetos = nivel.getLadrillos();
        objetos.add(jugador);
        objetos.add(enemigo);
        //objetos.add(enemigo);
    }

    public void actualizar(){
        for(int i = 0; i < objetos.size(); i++){
            objetos.get(i).actualizar();
        }
        //System.out.println(colision());
        /*if (colision()) {
            jugador.setCaida(0);
        }else{
            jugador.setCaida(Constantes.GRAVEDAD);
        }*/
    }

    public void dibujar(Graphics graficos){
        
        //jugador.dibujar(graficos);//dibujo el jugador en el estado de juego 
        for(int i = 0; i < objetos.size(); i++){
            objetos.get(i).dibujar(graficos);
        }
        //nivel.dibujar(graficos);
        
        //for(int i = 0; i < nivel.getLadrillos().size(); i++){
            //nivel.getLadrillos().get(i).dibujar(graficos);
            //System.out.println(i);
        //}
    }

    public ArrayList<ObjetoDelJuego> getObjetos(){
        return objetos;
    }

    public void addObjetos(ObjetoDelJuego objeto){
        objetos.add(objeto);
    }


    //public boolean colision(){}

}
