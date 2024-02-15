package entrada;

import javax.swing.JButton;

public class BotonPausa {
    JButton boton ;
    public static boolean PAUSA ;

    public BotonPausa(){ 
        boton = new JButton("Pausa");
        boton.setBounds(350, 300, 100, 50);
    }
    
    public void botonVisible(boolean b){
        boton.setVisible(b);
    }
   
    public JButton getBoton(){
        return boton;
    }
}
