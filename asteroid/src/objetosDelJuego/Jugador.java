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
    
    private Vector2D puntaNave;//es hacia donde esta mirando la nave
    private Vector2D aceleracion;//es el cambio en la velocidad con respecto al tiempo
    private final double ACC = 0.2;
    private boolean acelerando = false;

    //para que la clase jugador pueda tener acceso al arrayList de objetosMoviendose
    private EstadoDeJuego estadoDeJuego;
    

    public Jugador(Vector2D posicion,Vector2D velocidad , double velocidadMaxima,BufferedImage textura, EstadoDeJuego estadoDeJuego){
        super(posicion, velocidad, velocidadMaxima,textura);
        this.estadoDeJuego = estadoDeJuego;
        puntaNave = new Vector2D(0, 1);
        aceleracion = new Vector2D();//incializo la aceleracion de la nave
    }

    @Override//el que borra el override 
    public void actualizar(){
        /*if(Teclado.DERECHA){
            posicion.setX(posicion.getX() + 1);//muevo uno a la derecha el jugador
        }
        if(Teclado.IZQUIERDA){
            posicion.setX(posicion.getX() - 1);//muevo uno a la derecha el jugador 
        }*/

        if(Teclado.DISPARAR){//crea un laser que se añade al arraylist que se origina el 
            //centro de la nave
            estadoDeJuego.getObjetosQueSeMueven().add(new Laser(
                getCentro().suma(puntaNave.mulPorEscalar(ancho/2)),
                puntaNave, 
                10,
                angulo,
                Assets.laserRojo));
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
            System.out.println(Window.ANCHO);//buscar mejor nombre para window en futuro
            posicion.setX(0);//aparece por la parte izquierda
        }
        if(posicion.getY() > Window.ALTO){//si sale por arriba
            posicion.setY(0);//aparece por abajo
        }
        if(posicion.getX() < 0 ){//si sale por la izquierda 
            posicion.setX(Window.ANCHO);//aparece por la derecha
        }
        if(posicion.getY() < -1){//si sale por abajo 
            posicion.setY(Window.ALTO);//aparece por arriba
        }


    }

    @Override
    public void dibujar(Graphics graficos){
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
        graficos2D.drawImage(Assets.jugador, at, null);//dibujo la nave
    }

    public Vector2D getCentro(){//devuelve el centro de la nave
        return new Vector2D(posicion.getX() + ancho/2, posicion.getY() + alto/2);
    }
}
