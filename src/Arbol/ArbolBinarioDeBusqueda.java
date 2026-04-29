package Arbol;

public class ArbolBinarioDeBusqueda <T extends Comparable<T>> {

    public NodoArbolBinario<T> raiz;//nodo raiz
    public NodoArbolBinario<T> getRaiz() { return raiz; }
    public void setRaiz(NodoArbolBinario<T> raiz) {
        this.raiz = raiz;
    }

    //es una hoja
    public boolean isLeaf(NodoArbolBinario<T> nodo) {
        return nodo.getDer() == null && nodo.getIzq() == null;
    }

    //es interno
    public boolean isInternal(NodoArbolBinario<T> nodo) {
        return !isLeaf(nodo);
    }

    //devuelve el grado maximo
    public int getGrado() {
        return getGrado(raiz);
    }
    //metodo auxiliar
    public int getGrado(NodoArbolBinario<T> nodo) {
        if (nodo == null) return 0;
        //inicializamos en 0
        int grado = 0;
        if (nodo.getIzq() != null) grado++;//el nodo tiene hijo izquierdo
        if (nodo.getDer() != null) grado++;//el nodo tiene hijo derecho
        //observamos si los hijos izquierdos y derechos tienen hijos
        int gradoIzq = getGrado(nodo.getIzq());
        int gradoDer = getGrado(nodo.getDer());

        return Math.max(grado, Math.max(gradoIzq, gradoDer));//devuelve el grado maximo
    }

    //devuelve altura
    public int getAltura() {
        return getAltura(raiz);
    }
    //metodo auxiliar
    public int getAltura(NodoArbolBinario<T> nodo) {
        //caso base
        if (nodo == null) return -1;//arbol vacio
        //inicializamos en 0
        int altura  = 0;
        //calculamos altura si tiene al menos un hijo
        if (isInternal(nodo)) {
            //compara altura del nodo actual con la altura del hijo izquierdo
            if (nodo.getIzq() != null) {
                altura = Math.max(altura, getAltura(nodo.getIzq()));//math.max pilla el maximo de los dos(el mayor)
            }
            //compara altura del nodo actual con la altura del hijo derecho
            if (nodo.getDer() != null) {
                altura = Math.max(altura, getAltura(nodo.getDer()));
            }
            altura++;//sumamos altura
        }
        return altura;
    }

    //devuelve una lista con datos en un nivel especifico
    public ListaNodo<T> getListaDatosNivel (int nivel) {
        ListaNodo<T> lista = new ListaNodo<>();//crea lista nueva
        getListaDatosNivel(raiz, 0, nivel, lista);
        return lista;
    }
    //metodo auxiliar
    public void getListaDatosNivel(NodoArbolBinario<T> nodo, int nivelActual, int nivelBuscado, ListaNodo<T> lista) {
        if (nodo == null) return;
        if (nivelBuscado == nivelActual) {
            lista.add(nodo.getDato());//añade el dato en el nivel que estamos buscando
            return;
        }

        getListaDatosNivel(nodo.getIzq(), nivelActual + 1, nivelBuscado, lista);
    }

    //devuelve true si todos los nodos internos tienen el mismo grado
    public boolean isArbolhomogeneo() {
        return isArbolHomogeneo(raiz);
    }
    //metodo auxiliar
    public boolean isArbolHomogeneo(NodoArbolBinario<T> nodo) {
        //caso base: es una hoja o el arbol esta vacio
        if (nodo == null || isLeaf(nodo)) return true;
        //calculamos grado del nodo actual inicializando en 0
        int grado = 0;
        if (nodo.getIzq() != null) grado++;
        if (nodo.getDer() != null) grado++;
        //comprobamos que los hijos tengan el mismo grado que el padre
        if (nodo.getIzq() != null) {//hijo izquierdo
            int gradoIzq = 0;
            if (nodo.getIzq().getIzq() != null) gradoIzq++;
            if (nodo.getIzq().getDer() != null) gradoIzq++;

            if (gradoIzq != grado && gradoIzq != 0) return false;
        }
        if (nodo.getDer() != null) {//hijo derecho
            int gradoDer = 0;
            if (nodo.getDer().getIzq() != null) gradoDer++;
            if (nodo.getDer().getDer() != null) gradoDer++;

            if (gradoDer != 0 && gradoDer != grado) return false;//grado distinto
        }
        return isArbolHomogeneo(nodo.getIzq()) && isArbolHomogeneo(nodo.getDer());
    }

    //devuelve true si todos los niveles llenos excepto el ultimo
    public boolean isArbolCompleto() {
        return isArbolCompleto(raiz, false);
    }
    //metodo auxiliar
    public boolean isArbolCompleto(NodoArbolBinario<T> nodo, boolean eshoja) {
        if (nodo == null) return true;
        NodoArbolBinario<T> izq = nodo.getIzq();
        NodoArbolBinario<T> der = nodo.getDer();
        //no hay hijo izquierdo pero si derecho
        if (izq == null && der != null) return false;
        //el nodo debe ser hoja pero tiene algun hijo
        if (eshoja && (izq != null || der != null)) return false;
        //el nodo no tiene hijos y por tanto los siguientes nodos son hojas
        boolean nuevaHoja = eshoja || (izq == null || der == null);
        return isArbolCompleto(izq, nuevaHoja) && isArbolCompleto(der, nuevaHoja);
    }

    //devuelve true si se permite un fallo en el arbol con un nodo con un solo hijo
    public boolean isArbolCasiCompleto() {
        return isArbolCasiCompleto(raiz, false);//aun no hemos usado el error permitido
    }
    //metodo auxiliar
    public boolean isArbolCasiCompleto(NodoArbolBinario<T> nodo, boolean primererror) {
        if (nodo == null) return true;
        NodoArbolBinario<T> izq = nodo.getIzq();
        NodoArbolBinario<T> der = nodo.getDer();
        //solo existe hijo derecho
        if (izq == null && der != null) return false;
        //el nodo tiene dos hijos(fallo)
        boolean Error = (izq == null || der == null);
        //ya habia un error antes
        if (primererror && Error) return false;
        //ya se ha usado el primer error
        boolean nuevoError = primererror || Error;
        return isArbolCasiCompleto(izq, nuevoError) && isArbolCasiCompleto(der, nuevoError);
    }

    //devuelve la lista del camino desde la raiz
    public ListaNodo<T> getCamino(T dato) {
        //crear lista
        ListaNodo<T> lista2 = new ListaNodo<>();
        getCamino(dato, raiz, lista2);

        //imprimir el camino
        StringBuilder sb = new StringBuilder();
        ElementoSE<T> aux = lista2.primero;
        while (aux != null) {
            sb.append(aux.dato).append(", ");
            aux = aux.siguiente;
        }
        System.out.println("Camino: " + sb);
        return lista2;
    }
    //metodo auxiliar
    public ListaNodo<T> getCamino(T dato, NodoArbolBinario<T> nodo, ListaNodo<T> lista2) {
        if (nodo == null) return lista2;
        lista2.add(nodo.getDato());//añadir nodo actual al camino
        if (nodo.getDato().compareTo(dato) == 0) return lista2;//nodo actual
        if (dato.compareTo(nodo.getDato()) < 0) {
            return getCamino(dato, nodo.getIzq(), lista2);//subarbol izquierdo
        } else {
            return getCamino(dato, nodo.getDer(), lista2);//subarbol derecho
        }
    }

    //añadir dato
    public void add(){}
    public void add(T dato) {
        raiz = add(raiz, dato);
    }
    //metodo auxiliar
    public NodoArbolBinario<T> add(NodoArbolBinario<T> origen, T dato) {
        if (origen == null) {
            return new NodoArbolBinario<>(dato);//crear nodo nuevo
        }
        if (dato.compareTo(origen.getDato()) < 0) {
            origen.setIzq(add(origen.getIzq(), dato));//menor que 0 en subarbol izquierdo
        } else {
            origen.setDer(add(origen.getDer(), dato));//mayor que 0
        }
        return origen;
    }

    //devuelve el subarbol izquierdo como arbol nuevo
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        //crea un nuevo arbol binario vacio
        ArbolBinarioDeBusqueda<T> subarbol = new ArbolBinarioDeBusqueda<>();
        //comprobar si el arbol no esta vacio y tiene hijo izquierdo
        if (raiz != null && raiz.getIzq() != null) {
            subarbol.setRaiz(raiz.getIzq());//el hijo es la nueva raiz
        }
        return subarbol;
    }

    //devuelve el subarbol derecho como arbol nuevo
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        //crea un nuevo arbol binario vacio
        ArbolBinarioDeBusqueda<T> subArbol = new ArbolBinarioDeBusqueda<>();
        //comprueba que el arbol no esta vacio y tiene hijo derecho
        if (raiz != null && raiz.getDer() != null) {
            subArbol.setRaiz(raiz.getDer());//el hijo derecho es la nueva raiz
        }
        return subArbol;
    }

    //PREORDEN
    public void preorder(NodoArbolBinario<T> nodo) {

        System.out.print(nodo.getDato().toString() + ", ");

        if (nodo.getIzq() != null) {
            preorder(nodo.getIzq());
        }

        if (nodo.getDer() != null) {
            preorder(nodo.getDer());
        }
    }

    //INORDEN
    public void inorder(NodoArbolBinario<T> nodo) {
        if (nodo.getIzq() != null) {
            inorder(nodo.getIzq());
        }

        System.out.print(nodo.getDato().toString() + ", ");

        if (nodo.getDer() != null) {
            inorder(nodo.getDer());
        }
    }

    //POSTORDEN
    public void postorder(NodoArbolBinario<T> nodo) {

        if (nodo.getIzq() != null) {
            postorder(nodo.getIzq());
        }

        if (nodo.getDer() != null) {
            postorder(nodo.getDer());
        }

        System.out.print(nodo.getDato().toString() + ", ");
    }

    //devuelve lista en recorrido preorden
    public ListaNodo<T> getListaPreOrden() {
        //crea nueva lista
        ListaNodo<T> listapreorden = new ListaNodo<>();
        getListaPreOrden(raiz, listapreorden);
        return listapreorden;
    }
    //metodo auxiliar
    public void getListaPreOrden(NodoArbolBinario<T> nodo, ListaNodo<T> listapreorden) {
        if (nodo == null) return;//vacio
        listapreorden.add(nodo.getDato());//añadir raiz
        getListaPreOrden(nodo.getIzq(), listapreorden);//añadir nodo izquierdo
        getListaPreOrden(nodo.getDer(), listapreorden);//añadir nodo derecho
    }

    //devuelve lista en recorrido postorden
    public ListaNodo<T> getListaPostOrden() {
        //crea lista nueva
        ListaNodo<T> listapostorden = new ListaNodo<>();
        getListaPostOrden(raiz, listapostorden);
        return listapostorden;
    }
    //metodo auxiliar
    public void getListaPostOrden(NodoArbolBinario<T> nodo, ListaNodo<T> listapostorden) {
        if (nodo == null) return;//vacio
        getListaPostOrden(nodo.getIzq(), listapostorden);//añade nodo izquierdo
        getListaPostOrden(nodo.getDer(), listapostorden);//añade nodo derecho
        listapostorden.add(nodo.getDato());//añadir raiz
    }

    //devuelve lista en recorrido inorden
    public ListaNodo<T> getListaOrdenCentral() {
        //crea nueva lista
        ListaNodo<T> listaordencentral = new ListaNodo<>();
        getListaOrdenCentral(raiz, listaordencentral);
        return listaordencentral;
    }
    public void getListaOrdenCentral(NodoArbolBinario<T> nodo, ListaNodo<T> listaordencentral) {
        if (nodo == null) return;//vacio
        getListaOrdenCentral(nodo.getIzq(), listaordencentral);//añadir nodo izquierdo
        listaordencentral.add(nodo.getDato());//añadir raiz
        getListaOrdenCentral(nodo.getDer(), listaordencentral);//añadir nodo izquierda
    }


}
