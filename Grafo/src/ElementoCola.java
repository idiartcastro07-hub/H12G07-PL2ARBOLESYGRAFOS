// Definicion del Nodo de la cola
class ElementoCola <T> {
    // Los nodos en una cola se componen de un dato y un puntero
    T dato; // Dato del nodo
    ElementoCola<T> siguiente;

    ElementoCola(T dato) {
        this.dato = dato; // Guarda el dato en el nodo
        this.siguiente = null; // El puntero al siguiente nodo
    }
}