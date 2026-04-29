Pregunta 1: el camino minimo es el menor numero de aristas que separan dos nodos. Para calcularlo, hemos implementado la funcion de caminoMinimo() utilizando el BFS que se encarga de recorrer el grafo por niveles segun la cercania al origen, garantizando que ese sea el camino mas corto.

Pregunta 2: un grafo disjunto es aquel grafo que tiene algunos vertices que no estan conectados con los otros. Para comprobar esto, hemos utilizado el metodo esDisjunto() que tambien emplea el BFS contando todos los nodos que puede alcanzar.

Pregunta 3: para saber los fisicos que nacieron en la misma ciudad que Einstein, necesitamos que el grafo contenga esta informacion y para ello hemos creado un fichero: datos.text; que contiene toda la informacion del grafo. Además, tambien necesitamos el meotdo fisicosMismaCiudadQueEinstein() que se encarga de buscar en todas las tripletas en las que el sujeto es Einstein y el predicado es nacio_en, luego busca todas las tripletas donde el predicado es nacio_en y el objeto es el mismo que encontro anteriormente.

Pregunta 4: para empezar, hemos añadido esta tripleta en nuestro programa a traves del metodo anadirTripleta(). Despues, para listar los lugares de nacimiento empleamos el metodo listarLugaresNacimiento() que recorre todas las tripletas buscando aquellas que tienen como predicado nacio_en e imprime el sujeto y el objeto de cada una. Sobre los caminos que se necesitan recorrer, Antonio aparece en la lista porque tiene el predicado nace_en, pero como la unica informacion que tenemos sobre el, es el lugar de nacimiento, unicamente recorre solo un camino, el que va de Antonio a Villarubia de los Caballeros.

Pregunta 5: tenemos dos tipos de nodos. Por un lado, los vertices que son los sujetos y objetos, aquellos que representan nombres, ciudades, paises... Y, por otro lado, tenemos las aristas que son los predicados, aquellos que conectan los diferentes vertices. Por tanto, el sujeto y el objeto son los vertices y el predicado es la relacion que los une.

Pregunta 6: una ontologia es un mapa de como estan relacionadas las cosas entre si, es decir, la representacion grafica del grafo. La ontologia para nuestro problema quedaria de la siguiente manera:
Persona -> nacio_en -> Lugar
Lugar -> esta_en -> Lugar
Persona -> gano -> Premio
Con la ontologia podemos ver nuestro grafo de una mas visual.
