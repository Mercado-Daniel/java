package estados;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graficos.Animacion;
import graficos.Assets;
//import graficos.Animacion;
import main.Window;
import mat.Vector2D;
import objetosDelJuego.Jugador;
import objetosDelJuego.Meteoro;
import objetosDelJuego.ObjetoMoviendose;
import objetosDelJuego.Ovni;
import objetosDelJuego.Tamanio;

//importo la clase Graficos

public class EstadoDeJuego {
    public static final int OVNI_MAXIMA_VELOCIDAD = 3;
    private Jugador jugador;
    private ArrayList<ObjetoMoviendose> objetosQueSeMueven = new ArrayList<ObjetoMoviendose>();//se encarga de almacenar
    //todos los objetos que hereden objetosmoviendose que se dibujaran en la pantalla

    private int meteoros;//la cantidad de metoros en la pantalla

    private ArrayList<Animacion> explociones = new ArrayList<Animacion>();//almacena las animaciones del juego de forma dinamica 

    public EstadoDeJuego() {
        //jugador = new Jugador(new Vector2D(100, 500), Assets.jugador);      
        jugador = new Jugador(new Vector2D(400, 300), new Vector2D(), 5, Assets.jugador, this);
        //this hace referencia a la clase EstadoDeJuego  
        objetosQueSeMueven.add(jugador);//añado jugador al arraylist

        meteoros = 1;

        iniciarOleada();
    }

    public void iniciarExplosion(Vector2D posicion){
        explociones.add(new Animacion(
            Assets.explosionFotogramas,
            50, 
            posicion.resta(new Vector2D(
                Assets.explosionFotogramas[0].getWidth()/2,
                Assets.explosionFotogramas[0].getHeight()/2))));
    }

    private void espamearOvni(){
        int alAzar = (int)(Math.random()*2);

        double x = alAzar == 0 ? (Math.random()*Window.ANCHO) : 0;
        double y = alAzar == 0 ? 0 : (Math.random()*Window.ALTO);

        ArrayList<Vector2D> camino = new ArrayList<Vector2D>();

        double posX, posY;

        posX = Math.random()*(Window.ANCHO/2) + Window.ANCHO/2;
        posY = Math.random()*Window.ALTO/2;
        camino.add(new Vector2D(posX, posY));

        posX = Math.random()*Window.ANCHO/2;
        posY = Math.random()*(Window.ALTO/2) + Window.ALTO/2;
        camino.add(new Vector2D(posX, posY));

        posX = Math.random()*(Window.ANCHO/2) + Window.ANCHO/2;
        posY = Math.random()*(Window.ALTO/2) + Window.ALTO/2;
        camino.add(new Vector2D(posX, posY));

        objetosQueSeMueven.add(new Ovni(
            new Vector2D(x, y), 
            new Vector2D(), 
            OVNI_MAXIMA_VELOCIDAD, 
            Assets.ovni, 
            camino, 
            this));
        
    }

    public void dividirMeteoro(Meteoro meteoro){

        Tamanio tamanio = meteoro.getTamanio();
        BufferedImage[] texturas = tamanio.texturas;

        Tamanio nuevoTamanio = null;

        switch (tamanio) {
            case GRANDE:
                nuevoTamanio = Tamanio.MEDIANO;
                break;
            case MEDIANO:
                nuevoTamanio = Tamanio.CHICO;
                break;
            case CHICO:
                nuevoTamanio = Tamanio.MUY_CHICO;
                break;
            default:
                return;
        }

        for(int i = 0; i < tamanio.cantidad; i++){
            objetosQueSeMueven.add(
                new Meteoro(meteoro.getPosicion(),
                    new Vector2D(0,1).setDireccion(Math.random()* Math.PI*2), 
                    2.0 * Math.random() + 1, 
                    texturas[(int)Math.random()*texturas.length], //se vuelve a elegir textura al azar
                    this, 
                    nuevoTamanio));
        }

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
        espamearOvni();
    }



    public void actualizar() {
        //jugador.actualizar();
        //uso un for para recorrer el arraylist
        for(int i = 0; i < objetosQueSeMueven.size(); i++){
            objetosQueSeMueven.get(i).actualizar();//llamo el metodo actualizar correspondiente a cada objeto

        }

        for(int i = 0; i < explociones.size(); i++){
            Animacion animacion = explociones.get(i);
            animacion.actualizar();
            if(!animacion.estaCorriendo()){//la animacion llego a su fin
                explociones.remove(i);//elimino la animacion de la pantalla
            }
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

        for(int i = 0;i < explociones.size(); i++){
            Animacion animacion = explociones.get(i);
            graficos2D.drawImage(
                animacion.getFotograma(),
                (int)animacion.getPosicion().getX(),
                (int)animacion.getPosicion().getY(), 
                null);
        }

    }

    public ArrayList<ObjetoMoviendose> getObjetosQueSeMueven(){
        return objetosQueSeMueven;
    } 

    public Jugador getJugador(){//retorna el jugador para que el ovni pueda dispararle
        return jugador;
    }
}
