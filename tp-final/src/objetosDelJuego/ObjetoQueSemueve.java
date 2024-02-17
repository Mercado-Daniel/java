package objetosDelJuego;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


import estados.EstadoDeJuego;
import matematicas.Vector2D;
import nivel.Nivel;

public abstract class ObjetoQueSemueve extends ObjetoDelJuego {
    
    protected BufferedImage[] texturaArray;
    protected Cronometro cronometro;
    protected Nivel nivel;
    private ArrayList<ObjetoDelJuego> objetos;
    int izquierda;
    int derecha;
    public int velocidad;
    
    public ObjetoQueSemueve(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray, Nivel nivel){
        super(posicion, textura, estadoDeJuego);
        this.texturaArray = texturaArray;
        this.nivel = nivel;
        setCaida(Constantes.GRAVEDAD);
        izquierda = -1;
        derecha = + 1;
        velocidad = 1;
        objetos = estadoDeJuego.getObjetos();
    }

    protected ObjetoDelJuego colisionDerecha(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego m = objetos.get(i);

            if(m.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }
            if(this.rectangulo().intersects(m.rectangulo())){
                if((this.rectangulo().getMaxX() == m.rectangulo().getMinX() + velocidad ||
                    this.rectangulo().getMaxX() == m.rectangulo().getMinX() ||
                    this.rectangulo().getMaxX() + velocidad == m.rectangulo().getMinX())&&
                    this.rectangulo().getCenterY() -caida >= m.rectangulo().getMinY() &&
                    this.rectangulo().getCenterY() -caida < m.rectangulo().getMaxY() 
                    ){
                return m;
                
                }
            }
        }
        return null;
    }

    
    protected ObjetoDelJuego colisionAbajo(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego m = objetos.get(i);

            if(m.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }
            if(this.rectangulo().intersects(m.rectangulo())){
                if(this.rectangulo().getMaxY() - Constantes.GRAVEDAD == m.rectangulo().getMinY()&&
                    this.rectangulo().getMinX() + derecha <= m.rectangulo().getMaxX() - velocidad &&
                    this.rectangulo().getMaxX() + izquierda >= m.rectangulo().getMinX() + velocidad
                ){
                return m;
                }
            }
        }
        return null;
    }
    
    public ObjetoDelJuego colisionIzquierda(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego m = objetos.get(i);

            if(m.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }
            if(this.rectangulo().intersects(m.rectangulo())){
                if((this.rectangulo().getMinX() == m.rectangulo().getMaxX() - velocidad ||
                    this.rectangulo().getMinX() == m.rectangulo().getMaxX() ||
                    this.rectangulo().getMinX() - velocidad == m.rectangulo().getMaxX()) &&
                    this.rectangulo().getCenterY() - caida >= m.rectangulo().getMinY() &&
                    this.rectangulo().getCenterY() - caida < m.rectangulo().getMaxY() 
                ){
                return m;
                }
            }
        }
        return null;
    }
    
    public ObjetoDelJuego colisionArriba(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego m = objetos.get(i);

            if(m.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }
            if(this.rectangulo().intersects(m.rectangulo())){
                if(this.rectangulo().getMinY() + Constantes.GRAVEDAD == m.rectangulo().getMaxY() &&
                    this.rectangulo().getMinX() +derecha <= m.rectangulo().getMaxX() - velocidad &&
                    this.rectangulo().getMaxX() +izquierda >= m.rectangulo().getMinX() + velocidad  
                ){
                return m;
                }
            }
        }
        return null;
    }

    public boolean importa(ObjetoDelJuego a , ObjetoDelJuego b){
        if(a instanceof Enemigo && b instanceof Enemigo){
            return false;
        }
        
        return true;
    }

    public ObjetoDelJuego colisionGeneral(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego m = objetos.get(i);

            if(m.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }
            if(this.rectangulo().intersects(m.rectangulo())){
                return m;
                }
            }
        return null;
    }

    public ObjetoDelJuego colisionCentro(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego m = objetos.get(i);

            if(m.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }
            if(this.rectangulito().intersects(m.rectangulito())){
                return m;
                }
            }
        return null;
    }

    

    
}