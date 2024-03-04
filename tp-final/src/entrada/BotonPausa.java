package entrada;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BotonPausa   {
    JButton boton ;
    public static boolean PAUSA ;
    BotonReiniciar reiniciar ;
     BotonExit exit;

    public BotonPausa(BotonReiniciar reiniciar , BotonExit exit){ 
        this.reiniciar = reiniciar;
        this.exit = exit;
        boton = new JButton("Pause");
        boton.setForeground(Color.WHITE); // Color del texto
        boton.setBackground(Color.BLUE); // Color de fondo
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(370, 150, 100, 50);
        updatePause();  
     
    }

    
    private void updatePause() {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            PAUSA =!PAUSA;
            botonVisiblePause(PAUSA);
            reiniciar.botonVisibleReiniciar(PAUSA);
            exit.botonVisibleExit(PAUSA);    
            }
        });
    }


    public void botonVisiblePause(boolean b){
        boton.setVisible(b);
        
     
    }
   
    public JButton getBoton(){
        return boton;
    }
}