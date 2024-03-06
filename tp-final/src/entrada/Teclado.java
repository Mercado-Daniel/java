package entrada;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Teclado implements KeyListener  {
    private boolean[] teclas = new boolean[256];

    public static boolean IZQUIERDA, DERECHA, SALTAR, ATACAR, CORRER ;

    public Teclado(){
        IZQUIERDA = false;
        DERECHA = false;
        SALTAR = false;
        ATACAR = false;
 
    }

    public void actualizar(){//asigno las letras a las vriable si estas son presionados su valor sera true
        IZQUIERDA = teclas[KeyEvent.VK_A];
        DERECHA = teclas[KeyEvent.VK_D];
        SALTAR = teclas[KeyEvent.VK_SPACE];
        CORRER = teclas[KeyEvent.VK_SHIFT]; 

    }

   

    
    @Override
    public void keyPressed(KeyEvent e){
        teclas[e.getKeyCode()] = true;
    }
    @Override
    public void keyReleased(KeyEvent e){
        teclas[e.getKeyCode()] = false;
    }
    @Override
    public void keyTyped(KeyEvent e){
    }
}
