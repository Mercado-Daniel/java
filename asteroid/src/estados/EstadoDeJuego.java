package estados;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graficos.Assets;
import main.Window;
import mat.Vector2D;
import objetosDelJuego.Jugador;
import objetosDelJuego.Meteoro;
import objetosDelJuego.ObjetoMoviendose;
import objetosDelJuego.Tamanio;

//importo la clase Graficos

public class EstadoDeJuego {
    private Jugador jugador;
    private ArrayList<ObjetoMoviendose> objetosQueSeMueven = new ArrayList<ObjetoMoviendose>();//se encarga de almacenar
    //todos los objetos que hereden objetosmoviendose que se dibujaran en la pantalla

    private int meteoros;//la cantidad de metoros en la pantalla

    public EstadoDeJuego() {
        //jugador = new Jugador(new Vector2D(100, 500), Assets.jugador);      
        jugador = new Jugador(new Vector2D(400, 300), new Vector2D(), 5, Assets.jugador, this);
        //this hace referencia a la clase EstadoDeJuego  
        objetosQueSeMueven.add(jugador);//añado jugador al arraylist

        meteoros = 1;

        iniciarOleada();
    }

    private void iniciarOleada(){
        double x, y;//son las posiciones donde se van a crear los meteoros

        for(int i = 0; i < meteoros; i++){
            x = i % 2 == 0 ? Math.random()*Window.ANCHO : 0;//le doy aleatoriedad a donde se crea el meteoreo
            y = i % 2 != 0 ? Math.random()*Window.ALTO : 0;
            
            BufferedImage textura = Assets.meteorosGrandes[(int)Math.random()*Assets.meteorosGrandes.length];

            objetosQueSeMueven.add(
                new Meteoro(new Vector2D(x, y),
                    new Vector2D(0,1).setDireccion(Math.random()* Math.PI*2), 
                    2.0 * Math.random() + 1, 
                    textura, 
                    this, 
                    Tamanio.GRANDE));
        }

        meteoros ++;
    }



    public void actualizar() {
        //jugador.actualizar();
        //uso un for para recorrer el arraylist
        for(int i = 0; i < objetosQueSeMueven.size(); i++){
            objetosQueSeMueven.get(i).actualizar();//llamo el metodo actualizar correspondiente a cada objeto
        }

        for(int i = 0; i < objetosQueSeMueven.size(); i++){
            if(objetosQueSeMueven.get(i) instanceof Meteoro){//instanceof me dice si se trata de una instancia de la clase meteoro
                return;
            }
        }
        iniciarOleada();
    }

    public void dibujar(Graphics graficos) {//recibe un objeto graficos y lo dibuja en el buffer
        //jugador.dibujar(graficos);
        Graphics2D graficos2D = (Graphics2D)graficos;
        //antializing
        graficos2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);//renderiza al presionar una tecla, evitando que la nave se vea borrosa
        for(int i = 0; i < objetosQueSeMueven.size(); i++){
            objetosQueSeMueven.get(i).dibujar(graficos);//llamo el metodo dibujar correcspondiente a cada objeto
        }

    }

    public ArrayList<ObjetoMoviendose> getObjetosQueSeMueven(){
        return objetosQueSeMueven;
    } 
}
