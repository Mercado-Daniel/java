package objetosDelJuego;

public class Cronometro {
    private long delta, ultimoTiempo;
    private long tiempo;
    private boolean corriendo;

    public Cronometro(){
        delta = 0;
        ultimoTiempo = 0;
        corriendo = false;
    }

    public void arranque(long tiempo){
        corriendo = true;
        this.tiempo = tiempo;
    }

    public void actualizar(){
        if(corriendo){
            delta += System.currentTimeMillis() - ultimoTiempo;
        }
        
        if(delta >= tiempo){
            corriendo = false;
            delta = 0;
        }

        ultimoTiempo = System.currentTimeMillis();
    }

  

    public boolean estaCorriendo(){
        return corriendo;
    }
}
