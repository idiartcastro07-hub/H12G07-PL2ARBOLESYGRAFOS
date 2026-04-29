public class Iterador<T> implements MiIterador<T> {
    //clase que recorre la lista simple elemento a elemento
    private ElementoSE<T> actual;//nodo actual

    //Constructor:establece al nodo actual como el comienzo del recorrido
    public Iterador(ElementoSE<T> comienzo) {
        this.actual = comienzo;
    }

    @Override
    public boolean hasNext() {//devuelve true si el nodo actual no es null
        return actual != null;
    }

    @Override
    public T next() {
        if (!hasNext())
            return null;//si no hay mas elementos en la lista devuelve null
        //si hay pasa al siguiente nodo
        T dato = actual.dato; //guarda el dato actual
        actual = actual.siguiente;//el siguiente dato es el nuevo actual
        return dato;//devuelve el nuevo dato
    }
}
