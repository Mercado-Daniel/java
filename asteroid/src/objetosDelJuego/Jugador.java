package objetosDelJuego;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import entrada.Teclado;
import estados.EstadoDeJuego;
import graficos.Assets;
import mat.Vector2D;
import main.Window;//tener cuidado ya que awt tiene tambien window y este hace referencia al objeto mismo (jugador)

public class Jugador extends /*ObjetoDelJuego*/ ObjetoMoviendose{
    private static final int CADENCIA_DE_TIRO = 300;
    private Vector2D puntaNave;//es hacia donde esta mirando la nave
    private Vector2D aceleracion;//es el cambio en la velocidad con respecto al tiempo
    private final double ACC = 0.2;
    private boolean acelerando = false;

    //para que la clase jugador pueda tener acceso al arrayList de objetosMoviendose
    

    //para limitar la cantidad de tiros por segundo
    private Cronometro cadenciaDeTiro;
    // private long tiempo = 0;
    // private long tiempoActual = System.currentTimeMillis();
    //hacer que titile la nave
    private boolean apareciendo, visible;
    private Cronometro tiempoAparecer; //= 3000;
    private Cronometro tiempoTitilar; //= 200;


    

    public Jugador(Vector2D posicion,Vector2D velocidad , double velocidadMaxima,BufferedImage textura, EstadoDeJuego estadoDeJuego){
        super(posicion, velocidad, velocidadMaxima,textura, estadoDeJuego);
        puntaNave = new Vector2D(0, 1);
        aceleracion = new Vector2D();//incializo la aceleracion de la nave
        cadenciaDeTiro = new Cronometro();
        tiempoAparecer = new Cronometro();
        tiempoTitilar = new Cronometro();
    }

    @Override//el que borra el override 
    public void actualizar(){

        /*if(Teclado.DERECHA){
            posicion.setX(posicion.getX() + 1);//muevo uno a la derecha el jugador
        }
        if(Teclado.IZQUIERDA){
            posicion.setX(posicion.getX() - 1);//muevo uno a la derecha el jugador 
        }*/
        //tiempo += System.currentTimeMillis() - tiempoActual;
        //tiempoActual = System.currentTimeMillis();

        if(!tiempoAparecer.estaCorriendo()){
            apareciendo = false;
            visible = true;
        }
        if(apareciendo){
            if(!tiempoTitilar.estaCorriendo()){
                tiempoTitilar.arranque(200);
                visible = !visible;
            }
        }

        if(Teclado.DISPARAR && !cadenciaDeTiro.estaCorriendo() && !apareciendo){//crea un laser que se añade al arraylist que se origina el 
            //centro de la nave
            estadoDeJuego.getObjetosQueSeMueven().add(0, new Laser(
                getCentro().suma(puntaNave.mulPorEscalar(ancho)),
                puntaNave, 
                10,
                angulo,
                Assets.laserRojo,
                estadoDeJuego));

            //tiempo = 0;
            cadenciaDeTiro.arranque(CADENCIA_DE_TIRO);
        }

        if(Teclado.DERECHA){
            angulo += Math.PI/20;
        }
        if(Teclado.IZQUIERDA){
            angulo -= Math.PI/20;
        }
        if(Teclado.ARRIBA){
            aceleracion = puntaNave.mulPorEscalar(ACC);//multiplico la punta de la nave
        //por el macro acc que es la tasa de incremento de la aceleracion para mover la 
        //nave
            acelerando = true;
        }else{
            if (velocidad.getMagnitud() != 0) {
                aceleracion = (velocidad.mulPorEscalar(-1).normalizar()).mulPorEscalar(ACC);
                //de esta forma me encargo de desacelerar la nave por medio de 
                //cambiar la direccion del vector velocidad normalizado y multiplicarlo 
                //por la aceleracion
                acelerando = false;
            }
        }

        velocidad = velocidad.suma(aceleracion);//es la velocidad con la que la nave
        // //se mueve hacia donde apunta la nave
        
        velocidad = velocidad.limite(velocidadMaxima);


        puntaNave = puntaNave.setDireccion(angulo - (Math.PI/2));//la hubicaion inicial de la punta de la nave
        //es 90° funciona en radianes

        posicion = posicion.suma(velocidad);

        //las siguientes lineas son para hacer que cuando la nave salga de pantalla 
        //aparesca del otro lado como en el pacman
        if(posicion.getX() > Window.ANCHO){//si la nave sale por la parte derecha
            //buscar mejor nombre para window en futuro
            posicion.setX(-ancho);//aparece por la parte izquierda
        }
        if(posicion.getY() > Window.ALTO){//si sale por arriba
            posicion.setY(-alto);//aparece por abajo
        }
        if(posicion.getX() < -ancho ){//si sale por la izquierda 
            posicion.setX(Window.ANCHO);//aparece por la derecha
        }
        if(posicion.getY() < -alto){//si sale por abajo 
            posicion.setY(Window.ALTO);//aparece por arriba
        }

        cadenciaDeTiro.actualizar();
        tiempoAparecer.actualizar();
        tiempoTitilar.actualizar();
        colicionaCon();
    }

    @Override
    public void dibujar(Graphics graficos){
        if(!visible){
            return;
        }
        //graficos.drawImage(textura,(int)posicion.getX(), (int)posicion.getY(), null);
        Graphics2D graficos2D = (Graphics2D)graficos;//se encarga de recibir los assets i dibujarlos en pantalla junto con sus transformaciones
        //recibe la posicion de la nave mas el centro de estta
        //propulsor derecho
        AffineTransform at1 = AffineTransform.getTranslateInstance(posicion.getX() + ancho/2 + 5, posicion.getY() + alto/2 + 10);//es una  transformacion para el efecto de velocidad
        //propulsor izquierdo
        AffineTransform at2 = AffineTransform.getTranslateInstance(posicion.getX() + 5, posicion.getY() + alto/2 + 10);//es una  transformacion para el efecto de velocidad
        
        at1.rotate(angulo, -5, -10);//los resto para centrarlo
        at2.rotate(angulo, ancho/2 - 5, -10);
        //dibujo el efecto de aceleracion
        if(acelerando){
            graficos2D.drawImage(Assets.velocidad, at1, null);
            graficos2D.drawImage(Assets.velocidad, at2, null);

        }
        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        //at.rotate(angulo, Assets.jugador.getWidth()/2, Assets.jugador.getHeight()/2);//obtengo el ancho y el alto de la imagen en el buffer 
        //y centro el punto de rotacion en el medio de esta por medio de la division
        at.rotate(angulo, ancho/2, alto/2);
        graficos2D.drawImage(textura, at, null);//dibujo la nave
    }

    @Override
    protected void destruccuion() {
        apareciendo = true;
        tiempoAparecer.arranque(3000);
        reiniciarValores();
        estadoDeJuego.restarVida();
        //super.destruccuion();
    }

    private void reiniciarValores(){
        angulo = 0;
        velocidad = new Vector2D();
        posicion = new Vector2D(400, 300);
    }

    public boolean estaApareciendo()
    {
        return apareciendo;
    }
    
}
