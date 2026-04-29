package Grafo;

public class Cola<T extends Comparable<T>> implements InterfazCola<T> {
    private ElementoCola<T> principio;
    private ElementoCola<T> fin;
    private int tamaño = 0;


    public Cola(int capacidad) {
        principio = null;
        fin = null;
        tamaño = 0;
    }

    // Comprueba si la cola esta vacia o no
    public boolean isEmpty() {
        return principio == null;
    }

    // Metodo que añade un elemento al final de la cola
    public void enqueue(T dato) {
        ElementoCola<T> nuevo = new ElementoCola(dato);
        if (isEmpty()) {
            principio = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamaño++; // Aumenta el tamaño de la cola
    }

    // Metodo que elimina el primer elemento de la cola
    public T dequeue() {
        if (isEmpty()) return null;
        T dato = principio.dato;
        principio = principio.siguiente;
        tamaño--; // El tamaño disminuye
        return dato;
    }

    // Devuelve la informacion del primer elemento de la cola
    public T peek() {
        if (isEmpty()) return null;
        return principio.dato;
    }

    // Devuelve el tamaño de la cola
    public int getSize() {
        return tamaño;
    }
}