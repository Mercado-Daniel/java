

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class CreaYBorra implements Runnable{
    private List<String> rutas;

    public CreaYBorra(List<String> rutas) {
        this.rutas = rutas;
    }

    @Override
    public void run() {
        for (String ruta : rutas) {
            File aux = new File(ruta);

            if (aux.exists()) {//si el archivo existe
                try {
                    Files.delete(aux.toPath());//lo borro
                    System.out.println("Archivo borrado: " + aux.getName());//notifico que archivo borro
                } catch (Exception e) {// si no se puede borrar
                    e.printStackTrace();
                    System.out.println("Error al borrar archivo: " + aux.getName());//notico que archivo no se pudo borrar
                }
            } else {//si el archivo no existe
                try {
                    aux.createNewFile();//creo el archivo
                    System.out.println("Archivo creado: " + aux.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error al crear archivo: " + aux.getName());
                }
            }
        }
    }
}
