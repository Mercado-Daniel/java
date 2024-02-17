package entrada;


import java.awt.Image;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class BotonPausa   {
    JLabel boton ;
    ImageIcon imagen, imagenTamano;
    public static boolean PAUSA ;

    public BotonPausa(){ 
        boton = new JLabel();
        imagen = new ImageIcon("assets/imagenes/boton/btnpausa2.png");
        imagenTamano=new ImageIcon(imagen.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
        boton.setIcon(imagenTamano);
        
        // Establecemos el fondo del JLabel como transparente
        boton.setOpaque(false);
        boton.setBackground(new Color(0, 0, 0, 0)); // Establecemos un color transparente
        boton.setBounds(370, 150, 100, 50);
    
       
     
    }
    
    public void botonVisible(boolean b){
        boton.setVisible(b);
    }
   
    public JLabel getBoton(){
        return boton;
    }
}