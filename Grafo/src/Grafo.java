import java.io.*;

public class Grafo {
    // Atributos que vamos a necesitar para formar el grafo
    private Tripleta[] tripletas; // Array de tripletas donde vamos a guardar las distintas relaciones entre dos vertices y la arista que los une
    private int numTripletas; // Cuenta el numero de tripletas que hay

    public Grafo(int capacidad) {
        this.tripletas = new Tripleta[capacidad];
        this.numTripletas = 0;
    }

    private void ampliar() {
        // Cuando el array se llena, se crea uno nuevo de doble tamaño
        Tripleta[] nuevo = new Tripleta[tripletas.length * 2];
        for (int i = 0; i < tripletas.length; i++) {
            nuevo[i] = tripletas[i];
        }
        // Tripletas apunta al nuevo array, asi el grafo puede crecer
        tripletas = nuevo;
    }

    public void anadirTripleta(Tripleta tripleta) {
        if (numTripletas == tripletas.length) { // Se comprueba si el array esta lleno
            ampliar(); // Amplia el array
        }
        // Se añade la tripleta y se suma 1 al numero de tripletas
        tripletas[numTripletas] = tripleta;
        numTripletas++;
    }

    // Lee el fichero y carga las tripletas del grafo
    public void cargarDesdeArchivo (String archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo)); // Lee el fichero linea por linea
        String linea;
        while ((linea = br.readLine()) != null) { // Lee linea por linea por linea hasta que devuelva null (ha terminado)
            linea = linea.trim(); // Elimina espacios en blanco
            // Si la linea esta en blanco o empieza por # la ignora
            if (linea.length() == 0 || linea.startsWith("#")) {
                continue;
            }
            String[] partes = linea.split("\\s+"); // Divide la linea
            // Si la linea no tiene tres partes la ignora
            if (partes.length != 3) {
                continue;
            }
            Tripleta t = new Tripleta(partes[0], partes[1], partes[2]); // Crea una nueva tripleta con cada una de las partes
            anadirTripleta(t);
        }
        br.close(); // Cierra el fichero
    }

    // Getters y setters de los atributos de la clase
    public int getNumTripletas() {
        return numTripletas;
    }

    public Tripleta getTripleta(int i) {
        if (i < 0 || i >= numTripletas) {
            return null;
        }
        return tripletas[i];
    }

    // Recorre todas las tripletas del grafo y las imprime por pantalla
    public void imprimirGrafo() {
        for (int i = 0; i < numTripletas; i++) {
            System.out.println(tripletas[i]);
        }
    }

    // Recibe un nombre y busca las tripletas donde ese nombre es el sujeto
    public void mostrarVecinos(String sujeto) {
        System.out.println("Relaciones desde" + sujeto + ":");
        for (int i = 0; i < numTripletas; i++) {
            if(tripletas[i].getSujeto().equals(sujeto)){ // Compara los diferentes textos
                // Imprime el predicado y el objeto de las tripletas encontras (las relaciones desde ese nodo)
                System.out.println("- " + tripletas[i].getPredicado() + " -> " + tripletas[i].getObjeto());
            }
        }
    }

    // Busca camino mas corto entre dos nodos
    public void caminoMinimo(String origen, String destino) { // Recibe dos nodos
        int cap = numTripletas * 2 + 10;
        Cola cola = new Cola(1000); // Cola que controla los nodos que hay que explorar
        Lista visitados = new Lista(1000); // Lista que guarda los nodos explorados para no repetirlos
        Lista nodo = new Lista(1000); // Lista que guarda los nodos visitados en orden
        Lista previo = new Lista(1000); // Lista que permite reconstruir el camino

        // Inicio del BFS desde el nodo de origen
        cola.enqueue(origen); // Metemos el origen en la cola
        visitados.add(origen); // Lo añadimos a la lista de visitados para no repetirlo
        nodo.add(origen); // Guardamos el origen en la lista de nodos recorridos
        previo.add(null); // No hay nodo previo al origen

        boolean encontrado = false; // Busca el destino

        // Mientras no se encuentre el destino, saca el siguiente nodo de la cola
        while (!cola.isEmpty() && !encontrado) {
            String actual = (String) cola.dequeue();

            // Si el nodo actual es el destino se sale del bucle
            if (actual.equals(destino)) {
                encontrado = true;
                break;
            }

            // Recorremos todas las tripletas del grafo
            for (int i = 0; i < numTripletas; i++) {
                Tripleta t = tripletas[i];

                // Si el sujeto es el nodo que estamos explorando, su vecino es el objeto de la tripleta
                if (t.getSujeto().equals(actual)) {
                    String vecino = t.getObjeto();

                    // Si ese vecino, no ha sido visitado, se marca como visitado y lo metemos en la cola para explorar luego
                    if (!visitados.contiene(vecino)) {
                        visitados.add(vecino);
                        cola.enqueue(vecino);
                        nodo.add(vecino);
                        previo.add(actual);
                    }
                }
            }
        }

        // Si no se encuentra el destino, no existe ningun camino entre esos dos nodos
        if (!encontrado) {
            System.out.println("No hay camino entre " + origen + " y " + destino);
            return;
        }

        // Reconstruir el camino
        String[] camino = new String[1000]; // Array donde se ira guardando el camino
        int tam = 0;
        String actual = destino; // Se empieza por el destino
        while (actual != null) { // Mientras existe un nodo actual
            camino[tam++] = actual;
            String anterior = null;
            for (int i = 0; i < nodo.tamaño(); i++) { // Se recorre del reves
                if (nodo.get(i).equals(actual)) { // Busca cual es el nodo actual
                    anterior = previo.get(i); // Encuentra desde donde se llego al nodo actual
                    break; // Sale del bucle
                }
            }
            actual = anterior;
        }

        // Camino minimo
        System.out.println("Camino mínimo:");
        for (int i = tam - 1; i >= 0; i--) { // Como el camino, esta guardado al reves, lo recorre desde el final hasta el principio
            System.out.print(camino[i]);
            if (i > 0) System.out.print(" -> ");
        }
        System.out.println();
    }

    private boolean contiene(String[] array, int tamaño, String valor) {
        for (int i = 0; i < tamaño; i++) { // Recorre el array hasta el tamaño indicado
            // Si lo encuentra devuelve true
            if (array[i].equals(valor)) {
                return true;
            }
        }
        return false; // Si no lo encuentra devuelve false
    }

    private int contarNodos() {
        String[] nodos = new String[numTripletas*2]; // Crea un array vacio de tamaño del doble de tripletas porque cada una tiene un sujeto y un objeto
        int numNodos = 0;

        for (int i = 0; i < numTripletas; i++) { // Recorre todas las tripletas
            String s = tripletas[i].getSujeto(); // Coge el sujeto de la tripleta
            String o = tripletas[i].getObjeto(); // Coge el objeto de la tripleta
            // Comprueba que no se repitan nodos porque un mismo nodo puede aparecer mas de una vez en las tripletas
            if (!contiene(nodos, numNodos, s)) {
                nodos[numNodos++] = s;
            }
            if (!contiene(nodos, numNodos, o)) {
                nodos[numNodos++] = o;
            }
        }
        return numNodos; // Devuelve el numero de nodos unicos que hay en total
    }

    private int bfsDesde(String inicio) {
        int cap = numTripletas * 2 + 10; // La capacidad maxima es el doble de tripletas mas un margen de error
        Cola cola = new Cola(1000); // Se crea una nueva cola
        Lista visitados = new Lista(1000); // Se crea una nueva lista
        // Añadimos el nodo inicial a la cola y la lista de los ya visitados
        cola.enqueue(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = (String) cola.dequeue();
            for (int i = 0; i < numTripletas; i++) { // Recorre todas las tripletas
                Tripleta t = tripletas[i];
                // Si el sujeto es el nodo que estamos explorando su vecino es el objeto de la tripleta
                if (t.getSujeto().equals(actual)) {
                    String vecino = t.getObjeto();
                    // Si ese vecino no ha sido visitado todavia lo marcamos como visitado y lo metemos en la cola para explorarlo despues
                    if (!visitados.contiene(vecino)) {
                        visitados.add(vecino);
                        cola.enqueue(vecino);
                    }
                }
            }
        }
        return visitados.tamaño(); // Devuelve los nodos que ha podido visitar
    }

    public boolean esDisjunto() {
        // Si el grafo no tiene tripletas devuelve false
        if (numTripletas == 0) {
            return false;
        }
        // Recoge todos los nodos únicos para saber el total de nodos
        String[] todosNodos = new String[numTripletas * 2];
        int numNodos = 0;
        for (int i = 0; i < numTripletas; i++) {
            String s = tripletas[i].getSujeto();
            String o = tripletas[i].getObjeto();
            if (!contiene(todosNodos, numNodos, s)) todosNodos[numNodos++] = s;
            if (!contiene(todosNodos, numNodos, o)) todosNodos[numNodos++] = o;
        }
        int alcanzados = bfsDesde(todosNodos[0]); // Contamos cuantos nodos alcanza el BFS
        int total = numNodos; // Total de nodos unicos que hay en el grafo
        return alcanzados < total; // Si no ha podido alcanzar todos, hay nodos desconectados
    }

    public void fisicosMismaCiudadQueEinstein() {
        String ciudadEinstein = null; // Se crea una variable para guardar la ciudad de nacimiento de Einstein
        // Busca la ciudad donde nacio de Einstein
        for (int i = 0; i < numTripletas; i++) { // Recorre todas las tripletas
            Tripleta t = tripletas[i];
            // Busca todas las tripletas donde el sujeto es Einstein y el predicado sea nacio_en
            if (t.getSujeto().equals("Einstein") && t.getPredicado().equals("nacio_en")) {
                ciudadEinstein = t.getObjeto(); // Guarda el objeto, que es la ciudad de nacimiento
                break; // Sale del bucle
            }
        }
        // Si no lo encuentra, significa que no tenemos esa informacion en el grafo
        if (ciudadEinstein == null) {
            System.out.println("No se conoce la ciudad de nacimiento de Einstein");
            return;
        }

        // Busca otros físicos nacidos en la misma ciudad que Einstein
        System.out.println("Fisicos nacidos en " + ciudadEinstein + ":");
        for (int i = 0; i < numTripletas; i++) { // Recorre todas las tripletas
            Tripleta t = tripletas[i];
            // Busca otra persona que tambien haya nacido en la misma ciudad que Einstein
            if (!t.getSujeto().equals("Einstein") && t.getPredicado().equals("nacio_en") && t.getObjeto().equals(ciudadEinstein)) {
                System.out.println("- " + t.getSujeto()); // Imprime por pantalla el nombre de esa persona
            }
        }
    }

    public void listarLugaresNacimiento() {
        System.out.println("Lugares de nacimiento de los premios Nobel:");
        boolean hayAlguno = false; // Crea una variable que empieza siendo false
        for (int i = 0; i < numTripletas; i++) { // Recorre todas las tripletas
            Tripleta t = tripletas[i];
            if (t.getPredicado().equals("nacio_en")) { // Busca solo aquellas que tienen como predicado nacio_en
                System.out.println("  " + t.getSujeto() + " -> " + t.getObjeto()); // Imprime el sujeto y el objeto de la tripleta
                hayAlguno = true; // Cambia a true si ha encontrado al menos uno
            }
        }
        // Si despues de recorrer todas las tripletas no hay ninguno que coincida, significa que no hay informacion sobre los lugares de nacimiento
        if (!hayAlguno) {
            System.out.println("  (ninguno encontrado)");
        }
    }
}