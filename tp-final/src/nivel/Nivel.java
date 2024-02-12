package nivel;

//import java.awt.Color;
import java.awt.Graphics;
//import java.util.ArrayList;
import java.util.ArrayList;
//import java.util.List;

import estados.EstadoDeJuego;
import graficos.Assets;
import matematicas.Vector2D;
import objetosDelJuego.Enemigo;
//import objetosDelJuego.Enemigo;
//import graficos.Assets;
import objetosDelJuego.Ladrillo;
import objetosDelJuego.Monedas;
//import objetosDelJuego.ObjetoDelJuego;
import objetosDelJuego.ObjetoDelJuego;


public class Nivel {
    private int fila = 0;
    private int columna = 0;
    private int numeroFilas = 18;
    private int numeroColumnas = 32;
    //private final int anchoBloque = 32;
    //private final int altoBloque = 32;
    private Ladrillo ladrillo;
    private Enemigo enemigo;
    private EstadoDeJuego estadoDeJuego;
    private int[][] laberinto = obtieneLaberinto();
    private Monedas moneda;
    private ArrayList<ObjetoDelJuego> ladrillos = new ArrayList<ObjetoDelJuego>();

    public Nivel(EstadoDeJuego estadoDeJuego){
        this.estadoDeJuego = estadoDeJuego;
    }


    public void dibujar(Graphics graficos){
        
        for(fila = 0; fila < numeroFilas; fila++){
            for(columna = 0; columna < numeroColumnas; columna++){
                if(laberinto[fila][columna] == 1){
                    /*graficos.setColor(Color.BLUE);
                    graficos.fillRect(columna*32, fila*32, anchoBloque, altoBloque);
                    graficos.setColor(Color.BLACK);
                    graficos.drawRect(columna*32, fila*32, anchoBloque, altoBloque);
                    */
                    //ladrillo.dibujar(graficos, columna*ladrillo.getAncho(), fila*ladrillo.getAncho());
                    ladrillo = new Ladrillo(new Vector2D(columna*32, fila*32), Assets.ladrillo[0], estadoDeJuego);
                    //estadoDeJuego.addObjetos(ladrillo);
                    //ladrillos.add(ladrillo);
                    ladrillo.dibujar(graficos);
                }/*else if(laberinto[fila][columna] == 2){
                    enemigo = new Enemigo(new Vector2D(columna*16, fila*16), Assets.enemigo[0], estadoDeJuego, Assets.enemigo, null);
                    enemigo.dibujar(graficos);
                }*/
            }
        }
    }

    public int[][] obtieneLaberinto(){
        //LABERINTO 1
        int laberinto[][]=
            {{1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,1,0,1,1,0,0,0,0,0,0,1},
            { 1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,1,3,2,3,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
            { 1,0,1,0,0,2,0,0,0,3,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,1,1,1},
            { 1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,0,1,1,1,1,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1},
            { 1,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
            { 1,0,1,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,3,0,1,1,0,0,0,0,1,1,1},
            { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1},
            { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

            return laberinto;
    }

    public int getAnchoNivel(){
        return numeroColumnas * 32;
    }

    public int getAltoNivel(){
        return numeroFilas * 32;
    }

    public int getCordenada(int columna, int fila){
        return laberinto[fila][columna];
    }

    public ArrayList<ObjetoDelJuego> getLadrillos(){
        for(fila = 0; fila < numeroFilas; fila++){
            for(columna = 0; columna < numeroColumnas; columna++){
                if(laberinto[fila][columna] == 1){
                    /*graficos.setColor(Color.BLUE);
                    graficos.fillRect(columna*32, fila*32, anchoBloque, altoBloque);
                    graficos.setColor(Color.BLACK);
                    graficos.drawRect(columna*32, fila*32, anchoBloque, altoBloque);
                    */
                    //ladrillo.dibujar(graficos, columna*ladrillo.getAncho(), fila*ladrillo.getAncho());
                    ladrillo = new Ladrillo(new Vector2D(columna*32, fila*32), Assets.ladrillo[0], estadoDeJuego);
                    //estadoDeJuego.addObjetos(ladrillo);
                    ladrillos.add(ladrillo);
                    //ladrillo.dibujar(graficos);
                }
                if(laberinto[fila][columna] == 2){
                    enemigo = new Enemigo(new Vector2D(columna*32, fila*32), Assets.enemigo[0], estadoDeJuego, Assets.enemigo, this);
                    ladrillos.add(enemigo);
                }
                if(laberinto[fila][columna] == 3){
                    moneda = new Monedas(new Vector2D(columna*32, fila*32), Assets.moneda[1], estadoDeJuego, Assets.moneda);
                    ladrillos.add(moneda);
                }
            }
        }
        return ladrillos;
    }
}