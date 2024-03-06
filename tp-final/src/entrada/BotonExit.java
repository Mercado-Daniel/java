package entrada;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonExit {
    private JButton boton ;
    

    public BotonExit(){ 
        boton = new JButton("Exit");
        boton.setForeground(Color.WHITE); // Color del texto
        boton.setBackground(Color.RED); // Color de fondo
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(370, 290, 110, 50);
         exit();
            
     
    }
    
    private void exit(){
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        System.exit(0); }
    });
    }

    public void botonVisibleExit(boolean b){
        boton.setVisible(b);
        
    }
   
    public JButton getBotonExit(){
        return boton;
    }

}
