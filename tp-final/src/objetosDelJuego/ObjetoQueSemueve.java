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
    protected int izquierda;
    protected int derecha;
    private ArrayList<ObjetoDelJuego> objetos;
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

            ObjetoDelJuego coliDere = objetos.get(i);

            if(coliDere.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }

            if(this.rectangulo().intersects(coliDere.rectangulo())){
                if((this.rectangulo().getMaxX() == coliDere.rectangulo().getMinX() + velocidad ||
                    this.rectangulo().getMaxX() == coliDere.rectangulo().getMinX() ||
                    this.rectangulo().getMaxX() + velocidad == coliDere.rectangulo().getMinX())&&
                    this.rectangulo().getCenterY() -caida >= coliDere.rectangulo().getMinY() &&
                    this.rectangulo().getCenterY() -caida < coliDere.rectangulo().getMaxY() 
                    ){
                return coliDere;
                
                }
            }
        }
        return null;
    }

    
    public ObjetoDelJuego colisionIzquierda(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego coliIzq = objetos.get(i);

            if(coliIzq.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }

            if(this.rectangulo().intersects(coliIzq.rectangulo())){
                if((this.rectangulo().getMinX() == coliIzq.rectangulo().getMaxX() - velocidad ||
                    this.rectangulo().getMinX() == coliIzq.rectangulo().getMaxX() ||
                    this.rectangulo().getMinX() - velocidad == coliIzq.rectangulo().getMaxX()) &&
                    this.rectangulo().getCenterY() - caida >= coliIzq.rectangulo().getMinY() &&
                    this.rectangulo().getCenterY() - caida < coliIzq.rectangulo().getMaxY() 
                ){
                return coliIzq;
                }
            }
        }
        return null;
    }
    
    public ObjetoDelJuego colisionArriba(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego coliArri = objetos.get(i);

            if(coliArri.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }

            if(this.rectangulo().intersects(coliArri.rectangulo())){
                if(this.rectangulo().getMinY() + Constantes.GRAVEDAD == coliArri.rectangulo().getMaxY() &&
                    this.rectangulo().getMinX() +derecha <= coliArri.rectangulo().getMaxX() - velocidad &&
                    this.rectangulo().getMaxX() +izquierda >= coliArri.rectangulo().getMinX() + velocidad  
                ){
                return coliArri;
                }
            }
        }
        return null;
    }


    protected ObjetoDelJuego colisionAbajo(){

        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego coliAba = objetos.get(i);

            if(coliAba.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }

            if(this.rectangulo().intersects(coliAba.rectangulo())){
                if(this.rectangulo().getMaxY() - Constantes.GRAVEDAD == coliAba.rectangulo().getMinY()&&
                    this.rectangulo().getMinX() + derecha <= coliAba.rectangulo().getMaxX() - velocidad &&
                    this.rectangulo().getMaxX() + izquierda >= coliAba.rectangulo().getMinX() + velocidad
                ){
                return coliAba;
                }
            }
        }
        return null;
    }

   


    public ObjetoDelJuego colisionGeneral(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego coliGen = objetos.get(i);

            if(coliGen.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }

            if(this.rectangulo().intersects(coliGen.rectangulo())){
                return coliGen;
                }
            }
        return null;
    }

    public ObjetoDelJuego colisionCentro(){
        objetos = estadoDeJuego.getObjetos();

        for(int i = 0; i < objetos.size(); i++){

            ObjetoDelJuego coliCen = objetos.get(i);

            if(coliCen.equals(this)){
                continue;//si son iguales no colicionan por lo tanto paso a la siguiente iteracion
            }

            if(this.rectangulito().intersects(coliCen.rectangulito())){
                return coliCen;
                }
            }
        return null;
    }

    
    
    
}