package Arbol;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //PRIMER PROGRAMA DE PRUEBA
        //anadir en orden los numeros del 0 al 128
        //crear arbol nuevo
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        for (int i = 0; i <= 128; i++) {
            arbol.add(i);
        }

        //calcular la suma
        int suma = arbol.getSuma();
        System.out.println("Suma: " + suma);

        //suma en los 3 tipos de recorridos posibles
        //PREORDEN
        int sumapreorden = 0;
        //iterador
        MiIterador<Integer> itPreorden = arbol.getListaPreOrden().getIterador();
        while (itPreorden.hasNext()) {
            sumapreorden += itPreorden.next();
        }
        System.out.println("Suma preorden: " + sumapreorden);

        //INORDEN
        int sumainorden = 0;
        //iterador
        MiIterador<Integer> itInorden = arbol.getListaOrdenCentral().getIterador();
        while (itInorden.hasNext()) {
            sumainorden += itInorden.next();
        }
        System.out.println("Suma inorden: " + sumainorden);

        //POSTORDEN
        int sumapostorden = 0;
        //iterador
        MiIterador<Integer> itPostorden = arbol.getListaPostOrden().getIterador();
        while (itPostorden.hasNext()) {
            sumapostorden += itPostorden.next();
        }
        System.out.println("Suma postorden: " + sumapostorden);

        //suma de los elementos de los subarboles
        //IZQUIERDA
        ArbolBinarioDeBusquedaEnteros subizq = arbol.getSubArbolIzquierda();
        int sumaizq = subizq.getSuma();
        System.out.println("Suma: " + sumaizq);
        //DERECHA
        ArbolBinarioDeBusquedaEnteros subder = arbol.getSubArbolDerecha();
        int sumader = subder.getSuma();
        System.out.println("Suma: " + sumader);
        //SUMA TOTAL
        int sumatotal = arbol.getSuma();
        System.out.println("Suma: " + sumatotal);

        //altura
        int altura = arbol.getAltura();
        System.out.println("Altura: " + altura);

        //Camino hasta 110 y longitud
        ListaNodo<Integer> camino = arbol.getCamino(110);
        System.out.println("Longitud del camino: " + camino.getSize());

        //SEGUNDO PROGRAMA DE PRUEBA
        //anadir numeros de manera aleatoria
        //crea arbol nuevo
        ArbolBinarioDeBusquedaEnteros arbol2 = new ArbolBinarioDeBusquedaEnteros();
        //crea una lista del 0 al 128
        ListaNodo<Integer> lista = new ListaNodo<>();
        for (int i = 0; i <= 128; i++) {
            lista.add(i);
        }
        //anadirlos aleatoriamente
        Random rand = new Random();
        while (lista.getSize() > 0) {
            int aleatorio = rand.nextInt(lista.getSize());//indice aleatorio
            arbol2.add(lista.get(aleatorio));//añadir al arbol
            lista.del(aleatorio);//eliminar de la lista
        }

        //calcular la suma
        int suma2 = arbol2.getSuma();
        System.out.println("Suma: " + suma2);

        //verificar que la suma es la misma en los 3 recorridos posibles
        //PREORDEN
        int sumapreorden2 = 0;
        MiIterador<Integer> itPreorden2 = arbol2.getListaPreOrden().getIterador();
        while (itPreorden2.hasNext()) {
            sumapreorden2 += itPreorden2.next();
        }
        System.out.println("Suma preorden: " + sumapreorden2);

        //INORDEN
        int sumainorden2 = 0;
        MiIterador<Integer> itInorden2 = arbol2.getListaOrdenCentral().getIterador();
        while (itInorden2.hasNext()) {
            sumainorden2 += itInorden2.next();
        }
        System.out.println("Suma inorden: " + sumainorden2);

        //POSTORDEN
        int sumapostorden2 = 0;
        MiIterador<Integer> itPostorden2 = arbol2.getListaPostOrden().getIterador();
        while (itPostorden2.hasNext()) {
            sumapostorden2 += itPostorden2.next();
        }
        System.out.println("Suma postorden: " + sumapostorden2);

        //verificar que la suma es igual que la suma de los subarboles mas la raiz
        //IZQUIERDA
        ArbolBinarioDeBusquedaEnteros subizq2 = arbol2.getSubArbolIzquierda();
        int sumaizq2 = subizq2.getSuma();
        System.out.println("Suma: " + sumaizq2);
        //DERECHA
        ArbolBinarioDeBusquedaEnteros subder2 = arbol2.getSubArbolDerecha();
        int sumader2 = subder2.getSuma();
        System.out.println("Suma: " + sumader2);
        //SUMA TOTAL
        int sumatotal2 = arbol2.getSuma();
        System.out.println("Suma: " + sumatotal2);

        //calcular altura
        int altura2 = arbol2.getAltura();
        System.out.println("Altura: " + altura2);

        //camino hasta al 110 y longitud
        ListaNodo<Integer> camino2 = arbol2.getCamino(110);
        System.out.println("Longitud del camino: " + camino2.getSize());


    }
}
