package nivel;



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
import objetosDelJuego.Enemigos2;
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
    private Enemigos2 enemigo2;
    private EstadoDeJuego estadoDeJuego;
    private int[][] mapa;// = obtienemapa("assets/niveles/nivel-1.txt");
    private Monedas moneda;
    private Adornos adorno;
    private Banderita banderita;
    private LadrilloIndestructible ladrilloIndestructible;
    private LaCaja laCaja;
    private CajaInvisible cajaInvisible;
    private ArrayList<ObjetoDelJuego> ladrillos = new ArrayList<ObjetoDelJuego>();

    public Nivel(EstadoDeJuego estadoDeJuego, String nombreMapa){
        this.estadoDeJuego = estadoDeJuego;
        this.mapa = obtienemapa(nombreMapa);
    }


   

    public int[][] obtienemapa(String nombreArchivo){
        

            int[][] matriz = null;
            try {
                File archivo = new File(nombreArchivo);
                Scanner scanner = new Scanner(archivo);
                Scanner  scanner2 = new Scanner(archivo);

                numeroColumnas = 0;
                

                //cuento las filas y columnas
                while (scanner.hasNextLine()) {
                    numeroFilas++;
                    String[] numeros = scanner.nextLine().split(",");//separo por medio de las comas
                    if (numeros.length > numeroColumnas) {
                        numeroColumnas = numeros.length;
                    }
                }


                matriz = new int[numeroFilas][numeroColumnas];

                //relleno la matriz
                int fila = 0;
                while (scanner2.hasNextLine()) {
                    String[] numeros = scanner2.nextLine().split(",");
                    for(int columna = 0; columna < numeroColumnas; columna++) {
                        matriz[fila][columna] = Integer.parseInt(numeros[columna]);
                    }
                    fila ++;
                }

                scanner.close();
                scanner2.close();
                
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
                
                switch (mapa[fila][columna]) {
                    case 1:
                    ladrillo = new LadrilloDestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.ladrillo[0], estadoDeJuego);
                    ladrillos.add(ladrillo);
                    break;

                    case 2:
                    enemigo = new Enemigo(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.enemigo[0], estadoDeJuego, Assets.enemigo, this);
                    ladrillos.add(enemigo);
                    break;

                    case 3:
                    moneda = new Monedas(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.moneda[1], estadoDeJuego, Assets.moneda);
                    ladrillos.add(moneda);
                    break;
                    
                    case 4:
                    ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.ladrilloIndestructible, estadoDeJuego);
                    ladrillos.add(ladrilloIndestructible);
                    break;

                    case 5:
                    cajaInvisible = new CajaInvisible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.invisible, estadoDeJuego, this, "vida");
                    ladrillos.add(cajaInvisible);
                    break;

                    case 6:
                    adorno = new Adornos(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.nube, estadoDeJuego);
                    ladrillos.add(adorno);
                    break;

                    case 7:
                    adorno = new Adornos(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.castillo, estadoDeJuego);
                    ladrillos.add(adorno);
                    break;

                    case 8:
                    adorno = new Adornos(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.colina, estadoDeJuego);
                    ladrillos.add(adorno);
                    break;

                    case 9:
                    laCaja = new LaCaja(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.cajaPregunta[0], estadoDeJuego, Assets.cajaPregunta, Assets.cajaVacia);
                    ladrillos.add(laCaja);
                    break;
                    
                    case 10:
                    ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.canoChico, estadoDeJuego);
                    ladrillos.add(ladrilloIndestructible);
                    break;

                    case 11:
                    ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.canoMediano, estadoDeJuego);
                    ladrillos.add(ladrilloIndestructible);
                    break;

                    case 12:
                    ladrilloIndestructible = new LadrilloIndestructible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.canoGrande, estadoDeJuego);
                    ladrillos.add(ladrilloIndestructible);
                    break;


                    case 15:
                    banderita = new Banderita(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.poste, estadoDeJuego);
                    ladrillos.add(banderita);
                    break;

                    case 16:
                    cajaInvisible = new CajaInvisible(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.ladrillo[0], estadoDeJuego, this, "grande");
                    ladrillos.add(cajaInvisible);
                    break;

                    case 17:
                    enemigo2 = new Enemigos2(new Vector2D(columna*Constantes.ANCHO_TILE, fila*Constantes.ALTO_TILE), Assets.enemigo2[0], estadoDeJuego, Assets.enemigo2, this);
                    ladrillos.add(enemigo2);
                    break;
                }
                
                
            }
        }
        return ladrillos;
    }
}
