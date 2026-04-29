import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonUtil {

    // Guarda el grafo en un archivo JSON
    public static void guardarGrafoEnArchivo(String ruta, Grafo grafo) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(grafo, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga el grafo desde un archivo JSON
    public static Grafo cargarGrafoDesdeArchivo(String ruta) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(ruta)) {
            return gson.fromJson(reader, Grafo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Guarda cualquier objeto en un archivo JSON
    public static <T> void guardarObjetoEnArchivo(String ruta, T objeto) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(objeto, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga cualquier objeto desde un archivo JSON
    public static <T> T cargarObjetoDesdeArchivo(String ruta, Class<T> clase) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(ruta)) {
            return gson.fromJson(reader, clase);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}