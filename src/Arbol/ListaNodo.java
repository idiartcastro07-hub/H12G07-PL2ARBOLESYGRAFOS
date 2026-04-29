package Arbol;

public class ListaNodo <T extends Comparable<T>> implements Lista<T> {
    //Atributos
    protected ElementoSE<T> primero;
    protected int tamano = 0;

    @Override
    public void add (T dato) {//añade elemento al final de la lista
        ElementoSE<T> nuevo = new ElementoSE<>(dato);
        if (primero == null) {
            primero = nuevo;//añade elemento al principio de la lista si esta vacia
        } else {
            ElementoSE<T> aux = primero;
            while (aux.siguiente != null)//recorre hasta el ultimo nodo
                aux = aux.siguiente;//añade el elemento al final
            aux.siguiente = nuevo;
        }
        tamano++;//aumenta el contador
    }

    @Override
    public T get(T dato) {//devuelve el elemento que buscas
        ElementoSE<T> aux = primero;
        while (aux != null) {
            if (aux.dato.compareTo(dato) == 0)//compara elemento a elemento hasta encontrarlo
                return aux.dato;//devuelve el elemento
            aux = aux.siguiente;
        }
        return null;//no encontrado
    }

    @Override
    public T get(int dato)  {//devuelve el elemento que buscas por indice
        ElementoSE<T> aux = primero;
        for (int i = 0; i < dato; i++) {
          aux = aux.siguiente;//avanza hasta el indice que estamos buscado
        }
        return aux.dato;
    }

    @Override
    public T del(T dato) {//elimina elemento
        ElementoSE<T> act = primero, ant = null;//ant nodo anterior
        while(act != null) {
            if (act.dato.compareTo(dato) == 0) {//compara elemento a elemento
                if (ant == null) {
                    primero = act.siguiente;//el siguiente elemento se convierte en primero
                } else {
                    ant.siguiente = act.siguiente;//el nodo anterior es el eliminado
                }
                tamano--;//disminuye numero de elementos
                return act.dato;
            }
            ant = act;
            act = act.siguiente;
        }
        return null;//no encontrado
    }

    @Override
    public T del(int dato) {//elimina elemento por indice
        ElementoSE<T> act = primero, ant = null;
        for (int i = 0; i < dato; i++) {
            ant = act;
            act = act.siguiente;//avanza hasta el indice que estamos buscado
        }
        if (ant == null) {
            primero = act.siguiente;//siguiente nodo es el primero
        } else {
            ant.siguiente = act.siguiente;//anterior nodo apunta el siguiente
        }
        tamano--;
        return act.dato;
    }

    @Override
    public boolean isEmpty() { return primero == null; }//lista vacia si el primero es null
    @Override
    public int getSize() { return tamano; }//devuelve el tamaño de la lista
    @Override
    public MiIterador<T> getIterador() {
        return new Iterador<>(this.primero);//recorre todos los elementos desde el inicio
    }

}

