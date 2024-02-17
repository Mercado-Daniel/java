package objetosDelJuego;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import entrada.BotonPausa;
import sonidos.ReproductorSonidos;

public class Explicacion  extends JFrame{
  
  private ReproductorSonidos sonidoMenu,sonidoFondo;
  private JPanel panel;
  private JLabel label,label1,label2,label3,label4,label5,label6;

  public Explicacion(ReproductorSonidos sonidoMenu, ReproductorSonidos sonidoFondo) {  
      this.sonidoMenu = sonidoMenu;
      this.sonidoFondo=sonidoFondo;
   
      setTitle("Instructivo de teclas:");
      setSize(Constantes.ANCHO_EXPLICACION ,Constantes.ALTO_EXPLICACION);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      setResizable(false);

    panel = new JPanel();
    panel.setLayout(new GridLayout(4 , 1,2,2));

    label = new JLabel("Con la tecla A te moves para la Izquierda");
    label1 = new JLabel("Con la tecla D te moves para la Derecha");
    label2 = new JLabel("Con la tecla Esc, pausas el juego");
    label3 = new JLabel("Para reanudar el juego vuelve a pretar la tecla Esc");
    // label4 = new JLabel();
    // label5 = new JLabel();
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
         sonidoFondo.reproducirInf();
        }
    });
  
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowIconified(WindowEvent e) {
          // Evitar que la ventana se minimice
          setExtendedState(JFrame.NORMAL);
      }
  });
    add(panel, BorderLayout.CENTER);
    add(closeButton, BorderLayout.SOUTH);

}
 

}
