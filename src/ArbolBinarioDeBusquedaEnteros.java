public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {
    public ArbolBinarioDeBusquedaEnteros() {
        super();
    }

    //calcula suma para los enteros
    public int getSuma() {
        ListaNodo<Integer> lista = getListaPreOrden();//creamos una lista llamando a la de preorden que ya contiene todos los elementos del arbol
        int suma = 0;
        ElementoSE<Integer> aux = lista.primero;
        while (aux != null) {
            suma += aux.dato;
            aux = aux.siguiente;
        }
        return suma;
    }

    @Override//sobreescribe el subarbol izquierdo del Arbol Binario generico
    public ArbolBinarioDeBusquedaEnteros getSubArbolIzquierda() {
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();
        if (raiz != null && raiz.getIzq() != null) {
            sub.raiz = raiz.getIzq();//el hijo izquierdo es la nueva raiz
        }
        return sub;
    }
    @Override//sobreescribe el subarbol derecho de Arbol Binario generico
    public ArbolBinarioDeBusquedaEnteros getSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();
        if (raiz != null && raiz.getDer() != null) {
            sub.raiz = raiz.getDer();//el hijo derecho es la nueva raiz
        }
        return sub;
    }
}
