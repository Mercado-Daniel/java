package objetosDelJuego;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import estados.EstadoDeJuego;
import mat.Vector2D;

public abstract class ObjetoMoviendose extends ObjetoDelJuego {
    
    protected Vector2D velocidad;
    protected AffineTransform at;//es como las tranformaciones en en css
    protected double angulo;
    protected double velocidadMaxima;
    protected int ancho;
    protected int alto;
    protected EstadoDeJuego estadoDeJuego;

    public ObjetoMoviendose(Vector2D posicion, Vector2D velocidad, double velocidadMaxima, BufferedImage textura, EstadoDeJuego estadoDeJuego){
        super(posicion, textura);
        this.velocidad = velocidad;
        this.velocidadMaxima = velocidadMaxima;
        this.estadoDeJuego = estadoDeJuego;
        ancho = textura.getWidth();
        alto = textura.getHeight();
        angulo = 0;
    }

    protected void colicionaCon(){
        ArrayList<ObjetoMoviendose> objetosQueSeMueven = estadoDeJuego.getObjetosQueSeMueven();

        for(int i = 0; i < objetosQueSeMueven.size(); i++){

            ObjetoMoviendose m = objetosQueSeMueven.get(i);

            if(m.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }

            double distancia = m.getCentro().resta(getCentro()).getMagnitud();//de esta forma calulo la distancia de los objetos
            if(distancia < m.ancho/2 + ancho/2 && objetosQueSeMueven.contains(this)){//si la distancia es menor al radio de el objeto m mas el radio de este objeto y este objeto se encuentraen en el arraylist

                objetoColisionando(m, this);

            }
        }

    }

    private void objetoColisionando(ObjetoMoviendose a, ObjetoMoviendose b){
        if(a instanceof Jugador && (((Jugador)a).estaApareciendo())){
            return;
        }
        if(b instanceof Jugador && (((Jugador)b).estaApareciendo())){
            return;
        }
        if(!(a instanceof Meteoro && b instanceof Meteoro)){//si alguno de los objetos 
            //no es un meteoro procedo a la destruccion de ambos objetos
            estadoDeJuego.iniciarExplosion(getCentro());
            a.destruccuion();
            b.destruccuion();
        }
    }

    protected void destruccuion(){
        estadoDeJuego.getObjetosQueSeMueven().remove(this);//destruye este objeto
    }

    protected Vector2D getCentro(){//devuelve el centro de la nave
        return new Vector2D(posicion.getX() + ancho/2, posicion.getY() + alto/2);
    }

}
