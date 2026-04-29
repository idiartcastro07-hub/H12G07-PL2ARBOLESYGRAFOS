public class Main {
    public static void main(String[] args) {

        try {
            String fichero = "grafo.json";
            String operacion = args.length > 0 ? args[0] : "init";

            if (operacion.equals("init")) {
                // Crea el grafo y carga los datos del fichero
                Grafo grafo = new Grafo(20);
                grafo.cargarDesdeArchivo("./datos.txt");

                // Imprime todas las tripletas del grafo
                System.out.println("GRAFO");
                grafo.imprimirGrafo();
                System.out.println();

                // Muestra todas las relaciones que salen desde Einstein
                System.out.println("VECINOS DE EINSTEIN");
                grafo.mostrarVecinos("Einstein");
                System.out.println();

                // Calcula el camino minimo entre Einstein y Europa
                System.out.println("CAMINO MINIMO ENTRE EINSTEIN Y EUROPA");
                grafo.caminoMinimo("Einstein", "Europa");
                System.out.println();

                // Comprueba si el grafo es disjunto
                System.out.println("¿GRAFO DISJUNTO?");
                System.out.println(grafo.esDisjunto());
                System.out.println();

                // Busca fisicos nacidos en la misma ciudad que Einstein
                System.out.println("FISICOS EN LA MISMA CIUDAD QUE EINSTEIN");
                grafo.fisicosMismaCiudadQueEinstein();

                // Añade la tripleta de Antonio al grafo
                System.out.println();
                System.out.println("AÑADIR TRIPLETA DE ANTONIO");
                grafo.anadirTripleta(new Tripleta("persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros"));
                System.out.println("Tripleta añadida.");

                // Nombra los lugares de nacimiento de todos los premios Nobel
                System.out.println();
                System.out.println("LUGARES DE NACIMIENTO DE LOS PREMIOS NOBEL:");
                grafo.listarLugaresNacimiento();

                // Guarda el grafo en JSON
                System.out.println();
                System.out.println("GUARDANDO GRAFO EN JSON");
                GsonUtil.guardarGrafoEnArchivo(fichero, grafo);
                System.out.println("Fichero guardado: " + fichero);

            } else if (operacion.equals("show")) {
                // Carga el grafo desde el JSON
                Grafo grafoCargado = GsonUtil.cargarGrafoDesdeArchivo(fichero);
                if (grafoCargado != null) {
                    // Si se cargo, imprime el numero de tripletas y el grafo
                    System.out.println("GRAFO CARGADO DESDE JSON");
                    System.out.println("Numero de tripletas: " + grafoCargado.getNumTripletas());
                    grafoCargado.imprimirGrafo();
                } else {
                    // Si no se pudo cargar, no existe el fichero json
                    System.out.println("No se pudo cargar el grafo.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}