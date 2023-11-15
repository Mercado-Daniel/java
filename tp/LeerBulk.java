

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerBulk {//lee el bulk y extrae las rutas de los archivos
    public List<String> leer(String rutaArchivo) {
        List<String> rutas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String prueba;
            while ((prueba = br.readLine()) != null) {
                rutas.add(prueba);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rutas;
    }
}
