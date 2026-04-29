package Arbol;

//interfaz para el iterador
public interface MiIterador <T> {
    boolean hasNext();//devuelve true si quedan elementos que recorrer
    T next();//devuelve el elemento actual y pasa al siguiente;
}
