package entrada;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {//implemento la clase keylister para poder escuchar las teclas presionadas
    private boolean[] teclas = new boolean[256];
    public static boolean ARRIBA, IZQUIERDA, DERECHA;//son los tres tipos de teclas a usar

    public Teclado(){
        ARRIBA = false;//las teclas inician en flase
        IZQUIERDA = false;
        DERECHA = false;
    }

    public void actualizar(){
        ARRIBA = teclas[KeyEvent.VK_W] || teclas[KeyEvent.VK_UP];
        IZQUIERDA = teclas[KeyEvent.VK_A] || teclas[KeyEvent.VK_LEFT];
        DERECHA = teclas[KeyEvent.VK_D] || teclas[KeyEvent.VK_RIGHT];
    }

    
    @Override
    public void keyPressed(KeyEvent e){//se encaga de escuchar cuando se presiona una tecla
        //System.out.println(e.getKeyCode());
        teclas[e.getKeyCode()] = true; //cambia a true la tecla presionada
    }
    
    @Override
    public void keyReleased(KeyEvent e){//se encarga de escuchar cuando de despresiona una tecla
        teclas[e.getKeyCode()] = false;//cabia a falso la tecla cuando esta deja de ser presionada    
    }
    
    @Override
    public void keyTyped(KeyEvent e){

    }
    
}
