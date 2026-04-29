package Grafo;

public interface InterfazLista {
    void add(String s); // Añade un elemento al final de la lista
    boolean contiene(String s); // Comprueba si un elemento esta en la lista
    String get(int i); // Devuelve el elemento en la posicion i
    int tamaño(); // Devuelve el tamaño de la lista
}
