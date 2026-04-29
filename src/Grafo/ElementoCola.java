package Grafo;

// Definicion del Nodo de la cola
class ElementoCola <T> {
    // Los nodos en una cola se componen de un dato y un puntero
    T dato; // Dato del nodo
    ElementoCola<T> siguiente;

    ElementoCola(T dato) {
        this.dato = dato; // Guarda el dato en el nodo
        this.siguiente = null; // El puntero al siguiente nodo
    }

    public static interface InterfazLista {
        void add(String s); // Añade un elemento al final de la lista
        boolean contiene(String s); // Comprueba si un elemento esta en la lista
        String get(int i); // Devuelve el elemento en la posicion i
        int tamaño(); // Devuelve el tamaño de la lista
    }
}