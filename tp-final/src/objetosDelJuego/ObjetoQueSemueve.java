package objetosDelJuego;

import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;
import nivel.Nivel;

public abstract class ObjetoQueSemueve extends ObjetoDelJuego {
    
    protected BufferedImage[] texturaArray;
    protected Cronometro cronometro;
    protected Nivel nivel;
    int izquierda;
    int derecha;
    
    public ObjetoQueSemueve(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray, Nivel nivel){
        super(posicion, textura, estadoDeJuego);
        this.texturaArray = texturaArray;
        this.nivel = nivel;
        //this.estadoDeJuego = estadoDeJuego;
        setCaida(Constantes.GRAVEDAD);
        izquierda = -1;
        derecha = + 1;
    }

    
}
