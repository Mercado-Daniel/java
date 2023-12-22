package estados;

import java.awt.Graphics;
import java.util.ArrayList;

import graficos.Assets;
import mat.Vector2D;
import objetosDelJuego.Jugador;
import objetosDelJuego.ObjetoMoviendose;

//importo la clase Graficos

public class EstadoDeJuego {
    private Jugador jugador;
    private ArrayList<ObjetoMoviendose> objetosQueSeMueven = new ArrayList<ObjetoMoviendose>();//se encarga de almacenar
    //todos los objetos que hereden objetosmoviendose que se dibujaran en la pantalla

    public EstadoDeJuego() {
        //jugador = new Jugador(new Vector2D(100, 500), Assets.jugador);      
        jugador = new Jugador(new Vector2D(400, 300), new Vector2D(), 5, Assets.jugador, this);
        //this hace referencia a la clase EstadoDeJuego  
        objetosQueSeMueven.add(jugador);//añado jugador al arraylist
    }

    public void actualizar() {
        //jugador.actualizar();
        //uso un for para recorrer el arraylist
        for(int i = 0; i < objetosQueSeMueven.size(); i++){
            objetosQueSeMueven.get(i).actualizar();//llamo el metodo actualizar correspondiente a cada objeto
        }
    }

    public void dibujar(Graphics graficos) {//recibe un objeto graficos y lo dibuja en el buffer
        //jugador.dibujar(graficos);
        for(int i = 0; i < objetosQueSeMueven.size(); i++){
            objetosQueSeMueven.get(i).dibujar(graficos);//llamo el metodo dibujar correcspondiente a cada objeto
        }

    }

    public ArrayList<ObjetoMoviendose> getObjetosQueSeMueven(){
        return objetosQueSeMueven;
    } 
}
