package Grafo;

// Definicion del nodo de la lista
class ElementoLista {
    // Los nodos de una LSE se componen de un dato y un puntero
    String dato; // Dato del nodo
    ElementoLista siguiente;

    ElementoLista(String dato) {
        this.dato = dato; // Guarda el dato en el nodo
        this.siguiente = null; // El puntero apunta al siguiente
    }
}