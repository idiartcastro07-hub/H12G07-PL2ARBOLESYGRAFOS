package Grafo;

// Creamos una tripleta (unidad basica del grafo)
public class Tripleta {
    // Los tres elementos de la tripleta (sujeto, predicado y objeto)
    private String sujeto;
    private String predicado;
    private String objeto;

    public  Tripleta (String sujeto, String predicado, String objeto) {
        this.sujeto = sujeto;
        this.predicado = predicado;
        this.objeto = objeto;
    }

    // Getters de los elementos de la tripleta
    public String getSujeto() {
        return sujeto;
    }

    public String getPredicado() {
        return predicado;
    }

    public String getObjeto() {
        return objeto;
    }

    @Override
    // Como se imprime por pantalla la tripleta
    public String toString(){
        return "<" + sujeto + "," + predicado + "," + objeto + ">";
    }
}