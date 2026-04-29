public interface InterfazCola<T extends Comparable<T>> {
    T peek(); // // Devuelve la informacion del primer elemento de la cola
    void enqueue(T dato); // Añade un elemento alf inal de la cola
    T dequeue(); // Elimina un elemento al principio de la cola
    boolean isEmpty(); // Comprueba si la cola esta vacia
    int getSize(); // Devuelve el tamaño;
}
