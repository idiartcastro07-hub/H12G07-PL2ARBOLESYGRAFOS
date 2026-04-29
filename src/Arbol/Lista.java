package Arbol;

public interface Lista<T extends Comparable<T>> {
    void add(T dato);//añade elemento
    T get(T dato);//devuelve por dato
    T get(int dato);//devuelve por indice
    T del(T dato);//elimina por dato
    T del(int dato);//elimina por indice
    boolean isEmpty();//booleano lista vacia o no
    int getSize();//devuelve numero de elementos
    MiIterador<T> getIterador();//iterador que recorre la lista
}
