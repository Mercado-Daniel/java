package objetosDelJuego;

import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
import matematicas.Vector2D;

public class LaCaja extends Ladrillo{
    private BufferedImage[] texturaArray;
    private BufferedImage texturaFinal;
    private Cronometro cronometro;
    private Boolean golpeado = false;
   

    public LaCaja(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray, BufferedImage texturaFinal){
        super(posicion, textura, estadoDeJuego);
        this.texturaArray = texturaArray;
        this.texturaFinal = texturaFinal;
        cronometro = new Cronometro();
    }

    @Override
    public void actualizar(){
        if(!cronometro.estaCorriendo()){
            if(textura == texturaArray[0]){
                textura = texturaArray[1];
            }else if(textura == texturaArray[1]){
                textura = texturaArray[2];
            }else if(textura == texturaArray[2]){
                textura = texturaArray[3];
            }else if(textura == texturaArray[3]){
                textura = texturaArray[0];
            }
            if(golpeado){
                textura = texturaFinal;
            }
            cronometro.arranque(100);
        }
        cronometro.actualizar();
    }
    /*@Override
    public void destruir(){
        golpeado = true;
    }*/

    public boolean getGolpeado(){
        return golpeado;
    }
}
