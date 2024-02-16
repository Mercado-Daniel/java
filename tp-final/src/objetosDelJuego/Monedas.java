package objetosDelJuego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import estados.EstadoDeJuego;
//import graficos.Assets;
import matematicas.Vector2D;


public class Monedas extends ObjetoDelJuego{

    private BufferedImage[] texturaArray;
    private Cronometro cronometro;
   

    public Monedas(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray){
        super(posicion, textura, estadoDeJuego);
        this.texturaArray = texturaArray;
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
            
            cronometro.arranque(100);
        }
        
        cronometro.actualizar();
    }

    @Override
    public void dibujar(Graphics graficos){
        graficos.drawImage(textura, (int)posicion.getEjeX(), (int)posicion.getEjeY(), null);
    }
}
