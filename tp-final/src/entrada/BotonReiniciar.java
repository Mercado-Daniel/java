package entrada;


import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import estados.EstadoDeJuego;
import graficos.Assets;


public class BotonReiniciar   {
    private JButton boton ;
    
    

    public BotonReiniciar(){ 
        
        boton = new JButton("Reiniciar");
        boton.setForeground(Color.WHITE); // Color del texto
        boton.setBackground(Color.GREEN); // Color de fondo
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBounds(370, 220, 110, 50);
           
    }

    public void reiniciarLevel( EstadoDeJuego estado , BotonPausa pause , BotonExit exit){
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotonPausa.PAUSA =!BotonPausa.PAUSA;
                botonVisibleReiniciar(BotonPausa.PAUSA);
                pause.botonVisiblePause(BotonPausa.PAUSA);
                exit.botonVisibleExit(BotonPausa.PAUSA);
                
                estado.getJugador().setMonedas();
                estado.getJugador().setPuntos();
                estado.getJugador().setVidas5();
                estado.getJugador().setTexturas(Assets.jugadorMario[0] , Assets.jugadorMario);
                estado.getJugador().grande = false;
                estado.reiniciar();
                        
            }
        });
    }
    
    public void botonVisibleReiniciar(boolean b){
        boton.setVisible(b);
     
    }
   
    public JButton getBotonReiniciar(){
        return boton;
    }

}