package estados;

import java.awt.Graphics;

import graficos.Assets;
import mat.Vector2D;
import objetosDelJuego.Jugador;

//importo la clase Graficos

public class EstadoDeJuego {
    private Jugador jugador;

    public EstadoDeJuego() {
        jugador = new Jugador(new Vector2D(100, 500), Assets.jugador);        
    }

    public void actualizar() {

    }

    public void dibujar(Graphics graficos) {//recibe un objeto graficos y lo dibuja en el buffer
        jugador.dibujar(graficos);
    }
}
