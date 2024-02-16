package objetosDelJuego;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import entrada.BotonPausa;
import sonidos.ReproductorSonidos;

public class Explicacion  extends JFrame{
  
  private ReproductorSonidos sonidoMenu,sonidoFondo;
  
  public Explicacion(ReproductorSonidos sonidoMenu, ReproductorSonidos sonidoFondo) {  
      this.sonidoMenu = sonidoMenu;
      this.sonidoFondo=sonidoFondo;
   
      setTitle("Instructivo de teclas:");
      setSize(Constantes.ANCHO_EXPLICACION ,Constantes.ALTO_EXPLICACION);
      setLocationRelativeTo(null);
      setVisible(true);
    
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4 , 1,2,2));

    JLabel label = new JLabel("Con la tecla A te moves para la Izquierda");
    JLabel label1 = new JLabel("Con la tecla D te moves para la Derecha");
    JLabel label2 = new JLabel("Con la tecla Esc, pausas el juego");
    JLabel label3 = new JLabel("Para reanudar el juego vuelve a pretar la tecla Esc");
    // JLabel label4 = new JLabel();
    // JLabel label5 = new JLabel();
    JButton closeButton = new JButton("Cerrar instrutivo");

    panel.add(label);
    panel.add(label1);
    panel.add(label2);
    panel.add(label3);


    closeButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
         BotonPausa.PAUSA =!BotonPausa.PAUSA;
         dispose(); // Cierra la ventana emergente
         sonidoMenu.detener();
         sonidoFondo.reproducirInfi();
        }
    });

    add(panel, BorderLayout.CENTER);
    add(closeButton, BorderLayout.SOUTH);

}
 

}
