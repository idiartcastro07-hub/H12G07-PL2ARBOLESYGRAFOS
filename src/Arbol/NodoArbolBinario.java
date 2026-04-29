package Arbol;

public class NodoArbolBinario <T extends Comparable<T>> {
    private T dato;//dato en el nodo
    private NodoArbolBinario<T> izq;//hijo izquierdo
    private NodoArbolBinario<T> der;//hijo derecho

    //Constructor
    public NodoArbolBinario(T dato) {
        this.dato = dato;
    }
    public NodoArbolBinario(T dato, NodoArbolBinario<T> izq, NodoArbolBinario<T> der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    //GETTERS Y SETTERS
    public NodoArbolBinario<T> getIzq() {
        return izq;
    }
    public void setIzq(NodoArbolBinario<T> izq) {
        this.izq = izq;
    }
    public NodoArbolBinario<T> getDer() {
        return der;
    }
    public void setDer(NodoArbolBinario<T> der) {
        this.der = der;
    }
    public T getDato() {
        return dato;
    }
    public void setDato(T dato) {
        this.dato = dato;
    }
}
