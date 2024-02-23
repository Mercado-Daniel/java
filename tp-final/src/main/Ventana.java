package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.*;

import entrada.Teclado;
import entrada.BotonPausa;
import estados.EstadoDeJuego;
import objetosDelJuego.Constantes;
import objetosDelJuego.Explicacion;
import graficos.Assets;
import sonidos.ReproductorSonidos;

public class Ventana extends JFrame implements Runnable{

    private Canvas lienzo;
    private Thread hilo;
    private volatile boolean enFuncionamiento = false; //me indica si el hilo esta en funcionamiento o no
    private Teclado teclado;
    private BotonPausa botonPausa;
    private ReproductorSonidos sonidoPausa;
    private ReproductorSonidos sonidoFondo;
    private ReproductorSonidos sonidoMenu;

    //tasa de actualizacion de la pantalla fps
    private double fps = 0;
    //manipular pixeles
    /*private BufferedImage imagen = new BufferedImage(
        Constantes.ANCHO, 
        Constantes.ALTO, 
        BufferedImage.TYPE_INT_RGB);*/

    //estados
    private EstadoDeJuego estadoDeJuego;
    //imagen del icono
    private static final ImageIcon icono = new ImageIcon(Ventana.class.getResource("/imagenes/mario/mario-1.png"));


    public Ventana(){
        setTitle(Constantes.NOMBRE);//titulo de la ventana
        setSize(Constantes.ANCHO,Constantes.ALTO);//tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//que sucede al cerrar la ventana
        setResizable(false);//el tamaño de la ventana no se puede reajustar
        setIconImage(icono.getImage());
        setLocationRelativeTo(null);//para centrar la ventana en la pantalla
        setVisible(true);//muestro la ventana

        sonidoFondo = new ReproductorSonidos("assets/music/fondo2.wav");
        sonidoPausa = new ReproductorSonidos("assets/music/pause.wav");
        teclado = new Teclado();
        botonPausa = new BotonPausa();
        lienzo = new Canvas();
        
        lienzo.setPreferredSize(new Dimension(Constantes.ANCHO, Constantes.ALTO));
        lienzo.setMaximumSize(new Dimension(Constantes.ANCHO, Constantes.ALTO));
        lienzo.setMinimumSize(new Dimension(Constantes.ANCHO, Constantes.ALTO));
        lienzo.setFocusable(true);//permite obtener entradas del traclado
        lienzo.addKeyListener(teclado);//añado el teclado al lienzo
        lienzo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){  
                 BotonPausa.PAUSA = !BotonPausa.PAUSA; 
                 botonPausa.botonVisible(BotonPausa.PAUSA); 
                 sonidoPausa.reproducir();
            }
        }
         });

        
         lienzo.addKeyListener(new KeyAdapter() {
          @Override
             public void keyPressed(KeyEvent e) {
                         if (e.getKeyCode() == KeyEvent.VK_L) {
                            sonidoMenu = new ReproductorSonidos("assets/music/menu.wav");
                            Explicacion ventanaInfo = new Explicacion(sonidoMenu,sonidoFondo);
                                BotonPausa.PAUSA = !BotonPausa.PAUSA;
                           sonidoMenu.reproducirInf();
                           sonidoFondo.detener();
                          }
                         }
                     });
                     

                     
        botonPausa.botonVisible(BotonPausa.PAUSA);

        add(botonPausa.getBoton()); 
        add(lienzo);//agrego el lienzo a la ventana
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ventana().iniciar();
            }
        });
    }

    public synchronized void iniciar(){
        enFuncionamiento = true;
        hilo = new Thread(this);//instancio el hilo, es un hilo de tipo ventana
        hilo.start();//inicio el hilo
    }

    public void iniciarAssetsYEstados(){
        Assets.iniciar();
        estadoDeJuego = new EstadoDeJuego();
    }

    public synchronized void detener(){
        try{
            enFuncionamiento = false;
            hilo.join();//interrunpo el hilo dejandolo terminar (stop no deja terminar el hilo nacho)
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void actualizar(){
        teclado.actualizar();
        estadoDeJuego.actualizar();

    }

    private void dibujar(){
        BufferStrategy bs = lienzo.getBufferStrategy();
        if(bs == null){
            lienzo.createBufferStrategy(3);//triplebuffering
            return;
        }
        
        Graphics graficos = bs.getDrawGraphics();
        //inicia dibujado
       graficos.setColor(Color.CYAN);
        
        //graficos.drawImage()
        graficos.fillRect(0, 0, Constantes.ANCHO, Constantes.ALTO);
        
        estadoDeJuego.dibujar(graficos);
        //termina dibujado
        graficos.dispose();//libera la memoria de graficos
        bs.show();

    }

    @Override
    public void run(){
        //para los fps
        long inicioBucle = 0;
        long tiempoActualizacion = System.nanoTime();
        long contador = System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0;//variacion del tiemp entre fotogramas
      
        //requestFocus();//para que tome automaticamente el teclado
        iniciarAssetsYEstados();
        
         sonidoFondo.reproducirInf();
        while(enFuncionamiento){
            
            if(!BotonPausa.PAUSA){
                if(!BotonPausa.PAUSA && delta >=1){
                    delta = 0;
                    inicioBucle = 0;
                }
            //para los fps
            inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - tiempoActualizacion;
            tiempoActualizacion = inicioBucle;
            delta += tiempoTranscurrido / Constantes.NANOSEGUNDOS_POR_FPS;

            
            if(delta >= 1){
                actualizar();
                dibujar();
                delta--;//redusco delta para reiniciar el cronometraje del siguiente fotograma
                fps++;
            }

            if(System.nanoTime() - contador > Constantes.NANOSEGUNDOS_POR_SEGUNDO){
                //me permite mostrar los fps en el borde superior de la ventana
                setTitle( Constantes.NOMBRE + " || FPS: " + fps);
                fps = 0;
                contador = System.nanoTime();
            }

          
        }
     }
   }
}
