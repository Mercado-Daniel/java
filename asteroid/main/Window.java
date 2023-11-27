package main;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Window
 */
public class Window extends JFrame implements Runnable {//la ventana es un hilo y contiena un main

    public static final int ANCHO = 800, ALTO = 600;//aon como macros
    private Canvas canvas;
    private Thread hilo; //creo un hilo para que se encargue de dibujar y actualizar la pantalla
    private boolean corriendo = false;

    //para dibujar se necesitan dos objetos
    private BufferStrategy bs;//sirve para evitar el parpadeo de pantalla
    private Graphics graficos;//dibujara en el buffer, es donde se renderizan los graficos

    private static final int FPS = 60; // cntidad de fps objetivos en el juego
    private double TIEMPOFPS = 1000000000/FPS;//1 segundo en nanosegundos dividido la cantidad de fps objetivo  para aumentar la precision
    private double delta = 0; //delta es el cambio respecto al tiempo y se encarga de almacenar el tiempo transcurrido
    private double mostrarFPS = FPS;//se encarga de almacenar para mostrar los fps reales


    public Window(){//creo el constructor de la ventana
        setTitle("Space Ship Game");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
        //creamos el canvas 
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(ANCHO, ALTO));
        canvas.setMaximumSize(new Dimension(ANCHO, ALTO));
        canvas.setMinimumSize(new Dimension(ANCHO, ALTO));
        canvas.setFocusable(true);//permite obtner entradas desde el teclado
        
        add(canvas);//agregamos el canvas a la ventana

    }

    public static void main(String[] args) {
        new Window().start();
    }
    int x = 0;
    private void actualizar(){//se encarga de actualizar la pantalla
        x++;
    }

    private void dibujar(){//se encarga de dibujar en la ventana
        bs = canvas.getBufferStrategy();

        if(bs == null){
            canvas.createBufferStrategy(3);//triple buferring
            return;
        }
        graficos = bs.getDrawGraphics();//ahora puedo dibujar en el buffer
        //---------inicio dibujo-----
        graficos.clearRect(0, 0, ANCHO, ALTO);//limpia la ventana 
        graficos.drawRect(x, 0, 100, 100);//dibuja un cuadrado x e y son la posicion donde se encuentra la el objeto


        //---------fin dibujo-------
        graficos.dispose();//libero los recursos de graficos
        bs.show();//muestro en pantalla lo dibujado en el buffer
    }

    @Override
    public void run(){

        long ahora = 0;//registra el tiempo que va pasando
        long ultimoTiempo = System.nanoTime();//almacena el tiempo que paso en nanosegundos


        while(corriendo){//se encarga de actualizar la posicion de todos los 
        
            ahora = System.nanoTime();
            delta += (ahora - ultimoTiempo) / 
        
            //objetos del juego para despues dibujarlos en pantalla
           // actualizar();
            //dibujar();
        }

        stop();
    }

    private void start(){//inicia el hilo
        hilo = new Thread(this);
        hilo.start();
        corriendo = true;
    }

    private void stop(){//pausa el hilo
        try {
            hilo.join();//interrunpo el hilo
            corriendo = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}