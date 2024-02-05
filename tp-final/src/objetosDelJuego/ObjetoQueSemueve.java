package objetosDelJuego;

import java.awt.image.BufferedImage;
import java.util.List;

import estados.EstadoDeJuego;
import matematicas.Vector2D;
import nivel.Nivel;

public abstract class ObjetoQueSemueve extends ObjetoDelJuego {
    
    protected BufferedImage[] texturaArray;
    protected Cronometro cronometro;
    protected Nivel nivel;
    private List<Ladrillo> ladrillos;
    int izquierda;
    int derecha;
    
    public ObjetoQueSemueve(Vector2D posicion, BufferedImage textura, EstadoDeJuego estadoDeJuego, BufferedImage[] texturaArray, Nivel nivel){
        super(posicion, textura, estadoDeJuego);
        this.texturaArray = texturaArray;
        this.nivel = nivel;
        //this.estadoDeJuego = estadoDeJuego;
        setCaida(Constantes.GRAVEDAD);
        izquierda = -1;
        derecha = + 1;
        ladrillos = nivel.getLadrillos();
    }

    public boolean colisionDerecha(){
        for(int i = 0; i < ladrillos.size(); i++){
            if(this.rectangulo().intersects(ladrillos.get(i).rectangulo())){
                if(this.rectangulo().getMaxX() == ladrillos.get(i).rectangulo().getMinX() + 1 &&
                    this.rectangulo().getCenterY() - 4 >= ladrillos.get(i).rectangulo().getMinY() &&
                    this.rectangulo().getCenterY() -4 < ladrillos.get(i).rectangulo().getMaxY() 
                    ){
                    return true;  
                }
            }
        }
        return false;
    }
    

    public boolean colisionAbajo(){
        for(int i = 0; i < ladrillos.size(); i++){
            if(this.rectangulo().intersects(ladrillos.get(i).rectangulo())){
                if(this.rectangulo().getMaxY() - 4 == ladrillos.get(i).rectangulo().getMinY()&&
                    this.rectangulo().getMinX() <= ladrillos.get(i).rectangulo().getMaxX() - 4 &&
                    this.rectangulo().getMaxX() >= ladrillos.get(i).rectangulo().getMinX() + 4    
                ){
                    return true;  
                }
            }
        }
        return false;
    }
    
    

    public boolean colisionIzquierda(){
        for(int i = 0; i < ladrillos.size(); i++){
            if(this.rectangulo().intersects(ladrillos.get(i).rectangulo())){
                if(this.rectangulo().getMinX() == ladrillos.get(i).rectangulo().getMaxX() - 1 &&
                    this.rectangulo().getCenterY() - 4 >= ladrillos.get(i).rectangulo().getMinY() &&
                    this.rectangulo().getCenterY() -4 < ladrillos.get(i).rectangulo().getMaxY() 
                    ){
                    return true;  
                }
            }
        }
        return false;
    }

    public boolean colisionArriba(){
        for(int i = 0; i < ladrillos.size(); i++){
            if(this.rectangulo().intersects(ladrillos.get(i).rectangulo())){
                if(this.rectangulo().getMinY() + 4 == ladrillos.get(i).rectangulo().getMaxY() &&
                    this.rectangulo().getMinX() <= ladrillos.get(i).rectangulo().getMaxX() - 4 &&
                    this.rectangulo().getMaxX() >= ladrillos.get(i).rectangulo().getMinX() + 4   
                ){
                    System.out.println("casas");
                    return true;  
                }
                System.out.println(this.rectangulo().getMinY());
                System.out.println(ladrillos.get(i).rectangulo().getMaxY());
            }
        }
        return false;
    }
}

