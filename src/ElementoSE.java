//Nodo para listas simples
public class ElementoSE<T> {
    T dato;//dato en el nodo
    ElementoSE<T> siguiente;//siguiente nodo

    public ElementoSE(T dato) {
        this.dato = dato;
        this.siguiente = null;//el siguiente nodo es el vacio
    }
}
