public class NodoGrafo {
    public String nombre; // Nombre del nodo
    public Lista vecinos; //Lista de nodos vecinos a los que esta conectado

    // Constructor que inicializa el nodo con su nombre y una lista vacia de vecinos
    public NodoGrafo(String nombre) {
        this.nombre = nombre;
        this.vecinos = new Lista(10);
    }
}