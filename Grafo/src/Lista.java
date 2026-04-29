public class Lista implements InterfazLista {
    private ElementoLista principio;  // Primer nodo de la lista
    private ElementoLista fin;        // Ultimo nodo de la lista
    private int tamaño = 0;           // Numero de elementos

    public Lista(int capacidad) {
        principio = null;
        fin = null;
        tamaño = 0;
    }

    // Añade un elemento al final de la lista
    public void add(String s) {
        ElementoLista nuevo = new ElementoLista(s);
        if (principio == null) {
            principio = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamaño++;
    }

    // Comprueba si un elemento esta en la lista
    public boolean contiene(String s) {
        ElementoLista actual = principio;
        while (actual != null) {
            if (actual.dato.equals(s)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    // Devuelve el elemento en la posicion i
    public String get(int i) {
        ElementoLista actual = principio;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    // Devuelve el tamaño de la lista
    public int tamaño() {
        return tamaño;
    }
}