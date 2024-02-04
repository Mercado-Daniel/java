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

    public boolean colisionAbajo(){
        for(int i = 0; i < ladrillos.size(); i++){
            if(this.rectangulo().intersects(ladrillos.get(i).rectangulo())){
               // return true;
                
                    System.out.println((int)this.rectangulo().getMaxY());
                    System.out.println((int)ladrillos.get(i).rectangulo().getMinY());
                    if((int)this.rectangulo().getMaxY() == (int)ladrillos.get(i).rectangulo().getMinY() + 4){
                        return true;
                    }
                
            }
        }
        return false;
    }

    public boolean colisionDerecha(){
        for(int i = 0; i < ladrillos.size(); i++){
            if (this.getLadoDerecho() == ladrillos.get(i).getLadoIzquierdo()
                && (this.getCentroEjeY() <= ladrillos.get(i).getLadoSuperior()
                && this.getCentroEjeY() >= ladrillos.get(i).getLadoInferior() )) {
                return true;
            }
        }
        return false;
    }

    public String colision(){
        for(int i = 0; i < ladrillos.size(); i++){
            if(this.rectangulo().intersects(ladrillos.get(i).rectangulo())){
                return tipoColision(ladrillos.get(i));  
            }
        }
        return null;
    }
    
    public String tipoColision(Ladrillo ladrillo){
    
        double diferenciaX = this.getCentro().getEjeX() - ladrillo.getCentro().getEjeX();
        double diferenciaY = this.getCentro().getEjeY() - ladrillo.getCentro().getEjeY();
                            //calculo el valor absoluto de diferencia y le resto la suma de las mitades del ancho de los dos objetos
        double colisionX = Math.abs(diferenciaX) - (this.rectangulo().getWidth() / 2 + ladrillo.rectangulo().getWidth() / 2);
        double colisionY = Math.abs(diferenciaY) - (this.rectangulo().getHeight() / 2 + ladrillo.rectangulo().getHeight() / 2);
    
        if(colisionX < 0 && colisionY < 0){
            if(Math.abs(colisionX) > Math.abs(colisionY)){
                //colicion por los lados
                if(diferenciaX < 0){
                    return "izquierda";
                }else{
                    return "derecha";
                }
            }else{
                if(diferenciaY < 0){
                    return "arriba";
                }else{
                    return "abajo";
                }
            }
        }else{
            return null;
        }
    
    }
    
}

