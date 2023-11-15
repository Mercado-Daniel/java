

import java.io.File;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        LeerBulk lector = new LeerBulk();//creo una objeto leer que se encarga de leer el bulk
        List<String> todasLasRutas = lector.leer(System.getProperty("user.home") + File.separator + "bulk.txt");//lee el bulk y extrae las direcciones de los archivos
        //guardandolas en una lista

        int indice = 0;
        int cantidadEntradas = 10;//se encarga de que solo se lean 10 direcciones del bulk

        while (true) {
            if (indice >= todasLasRutas.size()) {
                // Se ha procesado todas las rutas, reiniciar el índice
                indice = 0;
            }

            List<String> rutasActuales = todasLasRutas.subList(indice, Math.min(indice + cantidadEntradas, todasLasRutas.size()));

            CreaYBorra crearBorrar = new CreaYBorra(rutasActuales);
            Thread hilo = new Thread(crearBorrar);

            // Inicia el hilo
            hilo.start();

            // Espera a que el hilo termine 
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Incrementar el índice para procesar las siguientes rutas en la próxima iteración
            indice += cantidadEntradas;

            // Dormir el hilo  durante 10 segundos
            try {
                Thread.sleep(10000); // 10000 milisegundos = 10 segundos no olvidar cambiar a 1 minuto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        }
    }
