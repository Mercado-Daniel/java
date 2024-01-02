package graficos;

import java.awt.image.BufferedImage;

import mat.Vector2D;

public class Animacion {
    private BufferedImage[] fotogramas;//imagenes de la animacion
    private int velocidad;//es la velocidad con la que cambian las imagenes de la aimacion
    private int indice;
    private boolean corriendo;
    private Vector2D posicion;
    private long tiempo, ultimoTiempo;

    public Animacion(BufferedImage[] fotogramas, int velocidad, Vector2D posicion){
        this.fotogramas = fotogramas;
        this.velocidad = velocidad;
        this.posicion = posicion;
        indice = 0;
        corriendo = true;
        tiempo = 0;
        ultimoTiempo = System.currentTimeMillis();
    }

    public void actualizar(){
        tiempo += System.currentTimeMillis() - ultimoTiempo;
        ultimoTiempo = System.currentTimeMillis();

        if(tiempo > velocidad){
            tiempo = 0;
            indice++;

            if(indice >= fotogramas.length){
                corriendo = false;
            }

        }
    }

    public Boolean estaCorriendo(){
        return corriendo;
    }

    public Vector2D getPosicion(){
        return posicion;
    }

    public BufferedImage getFotograma(){
        return fotogramas[indice];
    }

}
