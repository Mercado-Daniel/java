package nivel;


import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import estados.EstadoDeJuego;
import graficos.Assets;
import matematicas.Vector2D;
import objetosDelJuego.Adornos;
import objetosDelJuego.Banderita;
import objetosDelJuego.CajaInvisible;
import objetosDelJuego.Constantes;
import objetosDelJuego.Enemigo;
import objetosDelJuego.LaCaja;
import objetosDelJuego.LadrilloDestructible;
import objetosDelJuego.LadrilloIndestructible;
import objetosDelJuego.Monedas;
import objetosDelJuego.ObjetoDelJuego;


public class Nivel {
    private int fila = 0;
    private int columna = 0;
    private int numeroFilas = 0; //= 18;
    private int numeroColumnas = 0; //= 208;
    private LadrilloDestructible ladrillo;
    private Enemigo enemigo;
    private EstadoDeJuego estadoDeJuego;
    private int[][] mapa = obtienemapa("assets/niveles/nivel-1.txt.txt");
    private Monedas moneda;
    private Adornos adorno;
    private Banderita banderita;
    private LadrilloIndestructible ladrilloIndestructible;
    private LaCaja laCaja;
    private CajaInvisible cajaInvisible;
    private ArrayList<ObjetoDelJuego> ladrillos = new ArrayList<ObjetoDelJuego>();

    public Nivel(EstadoDeJuego estadoDeJuego){
        this.estadoDeJuego = estadoDeJuego;
    }


   

    public int[][] obtienemapa(String nombreArchivo){
        

            int[][] matriz = null;
            try {
                File archivo = new File(nombreArchivo);
                Scanner scanner = new Scanner(archivo);
                numeroColumnas = 0;
                numeroColumnas = 0;
                //cuento las filas y columnas
                while (scanner.hasNextLine()) {
                    numeroFilas++;
                    String[] numeros = scanner.nextLine().split(",");//separo por medio de las comas
                    if (numeros.length > numeroColumnas) {
                        numeroColumnas = numeros.length;
                    }
                }

                scanner = new Scanner(archivo);

                matriz = new int[numeroFilas][numeroColumnas];

                //relleno la matriz
                int fila = 0;
                while (scanner.hasNextLine()) {
                    String[] numeros = scanner.nextLine().split(",");
                    for(int columna = 0; columna < numeroColumnas; columna++) {
                        matriz[fila][columna] = Integer.parseInt(numeros[columna]);
                    }
                    fila ++;
                }

                scanner.close();
                
            } catch (FileNotFoundException e) {
               System.out.println("no se encontro el archivo");
               e.printStackTrace();
            }
        return matriz;

    }

    public int getAnchoNivel(){
        return numeroColumnas * Constantes.ANCHO_TILE;
    }

    public int getAltoNivel(){
        return numeroFilas * Constantes.ALTO_TILE;
    }

    public int getCordenada(int columna, int fila){
        return mapa[fila][columna];
    }

    public ArrayList<ObjetoDelJuego> getLadrillos(){
        for(fila = 0; fila < /*numeroFilas*/mapa.length; fila++){
            for(columna = 0; columna < /*numeroColumnas*/mapa[fila].length; columna++){
                if(mapa[fila][columna] == 1){
                    ladrillo = new LadrilloDestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.ladrillo[0], estadoDeJuego);
                    ladrillos.add(ladrillo);
                    
                }
                if(mapa[fila][columna] == 2){
                    enemigo = new Enemigo(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.enemigo[0], estadoDeJuego, Assets.enemigo, this);
                    ladrillos.add(enemigo);
                }
                if(mapa[fila][columna] == 3){
                    moneda = new Monedas(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.moneda[1], estadoDeJuego, Assets.moneda);
                    ladrillos.add(moneda);
                }
                if(mapa[fila][columna] == 4){
                    ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.ladrilloIndestructible, estadoDeJuego);
                    ladrillos.add(ladrilloIndestructible);
                }
                if(mapa[fila][columna] == 5){
                    cajaInvisible = new CajaInvisible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.invisible, estadoDeJuego);
                    ladrillos.add(cajaInvisible);
                }
                if(mapa[fila][columna] == 9){
                    laCaja = new LaCaja(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.cajaPregunta[0], estadoDeJuego, Assets.cajaPregunta, Assets.cajaVacia);
                    ladrillos.add(laCaja);
                }
                if(mapa[fila][columna] == 10){
                    ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.canoChico, estadoDeJuego);
                    ladrillos.add(ladrilloIndestructible);
                }
                if(mapa[fila][columna] == 11){
                    ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.canoMediano, estadoDeJuego);
                    ladrillos.add(ladrilloIndestructible);
                }
                if(mapa[fila][columna] == 12){
                    ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.canoGrande, estadoDeJuego);
                    ladrillos.add(ladrilloIndestructible);
                }
                if(mapa[fila][columna] == 6){
                    adorno = new Adornos(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.nube, estadoDeJuego);
                    ladrillos.add(adorno);
                }
                if(mapa[fila][columna] == 7){
                    adorno = new Adornos(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.castillo, estadoDeJuego);
                    ladrillos.add(adorno);
                }
                if(mapa[fila][columna] == 8){
                    adorno = new Adornos(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.colina, estadoDeJuego);
                    ladrillos.add(adorno);
                }
                if(mapa[fila][columna] == 15){
                    banderita = new Banderita(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.poste, estadoDeJuego);
                    ladrillos.add(banderita);
                }

                
                
            }
        }
        return ladrillos;
    }
}
