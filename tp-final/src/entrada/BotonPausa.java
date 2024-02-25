package entrada;


import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class BotonPausa   {
    JLabel boton ;
    ImageIcon imagen, imagenTamano;
    public static boolean PAUSA ;

    public BotonPausa(){ 
        boton = new JLabel();
        imagen = new ImageIcon("assets/imagenes/boton/btnpausa2.gif");
        imagenTamano=new ImageIcon(imagen.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
        boton.setIcon(imagenTamano);
        boton.setBounds(370, 150, 100, 50);
       
     
    }
    
    public void botonVisible(boolean b){
        boton.setVisible(b);
    }
   
    public JLabel getBoton(){
        return boton;
    }
}