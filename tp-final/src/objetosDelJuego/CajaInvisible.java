package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;

public class CajaInvisible extends Ladrillo{
    private String contenido;

    public CajaInvisible(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, String contenido){
        super(posicion, textura,estadoDeJuego);
        this.contenido = contenido;
    }

    @Override
    public void actualizar() {
        
    }

    @Override
    public void dibujar(Graphics graficos) {
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);

    }
    public String getContenido(){
        return contenido;
    }

    
}
