package Parte2;

import Parte_1.Procesador;
import Parte_1.Tarea;

import java.util.ArrayList;
import java.util.List;

public class Backtraking {

    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private int maximoTiempoNoRefrigerado;
    private int metrica;

    /*
     * El algoritmo immplementado esta compuesto por dos metodos principales.
     * El Metodo publico denominado "backtracking" se encarga de almacenar las respectivas posibles soluciones y por otro lado la mejor solucion hasta el momento,
     * pero su principal funcion es hacer el llamado al segundo Metodo principal y privado "back", que recursivamente a recorrer los procesadores y colocar donde
     * corresponda una tarea.
     * Este utiliza el Metodo "soySolucion" para comprobar si todas las tareas fueron asignadas a los procesadores y devolver la posible solucion. Caso contrario
     * se seguiran asignando las tareas.
     * Es de mayor importancia el Metodo "tiempoMaximoEjecucion", ya que este devuelve el tiempo que toma ejecutar la lista de procesadores. Este Metodo es necesario
     * para realizar la poda en el Metodo principal "back", que con una simple condicion se va a encargar de comprobar si la posible solucion recorriendose
     * en ese instante, tiene un tiempo de ejecucion menor al de la actual mejor solucion; en caso de tener un tiempo mayor, esa posible solucion queda descartada.
     *
     * Si hay n decisiones posibles en cada paso (procesadores) y la profundidad maxima de la recursion es d (cantidad de tareas), la complejidad
     * computacional del algoritmo de backtracking es, en el peor de los casos, O(n^d)
     * */

    public Backtraking(List<Procesador> pro1, List<Tarea> tar, int n) {
        this.procesadores = pro1;
        this.tareas = tar;
        this.maximoTiempoNoRefrigerado = n;
        this.metrica = 0;
    }

    public void backtracking() {
        this.metrica = 0;
        //Creo la lista solucion con todos los procesadores cargados
        List<Procesador> solucion = new ArrayList<>(procesadores);
        //Creo la lista que va a ir probando todas las tareas en cada procesador
        List<Procesador> posSolucion = this.deepCopyProcesadores(solucion);


        //Se va a quedar con la mejor combinacion de tareas en cada procesador
        solucion = back(solucion, posSolucion, 0);

        System.out.println("Backtraking");
        System.out.println("Solucion obtenida : ");
        for (Procesador procesador : solucion) {
            procesador.imprimirProcesadorconTareas();
        }
        System.out.println("Solucion obtenida : " + tiempoMaximoEjecucion(solucion));
        System.out.println("Metrica para analizar el costo de la solucion : " + metrica);
    }

    private int tiempoMaximoEjecucion(List<Procesador> p) {
        int tiempoMaximo = 0;
        for (Procesador procesador : p) {
            if (tiempoMaximo < procesador.tiempoMaximo()) {
                tiempoMaximo = procesador.tiempoMaximo();
            }
        }
        return tiempoMaximo;
    }

    //Metodo privado backtraking
    private List<Procesador> back(List<Procesador> solucion, List<Procesador> posSolucion, int count) {
        metrica++;
        //Soluciones
        //checkeo si mi lista de posibles solucion es una solucion.
        if (soySolucion(posSolucion)) {
            //soy mejor solucion?
            if (esLaMejorSolucion(solucion, posSolucion)) {
                solucion = this.deepCopyProcesadores(posSolucion);
            }
        } else {
            //Iteracion cuando no es solucion
            //Itero cada procesador
            for (Procesador procesador : posSolucion) {
                //asigno tarea a un procesador

                if (procesador.puedoAddTarea(tareas.get(count), maximoTiempoNoRefrigerado)) {
                    procesador.addTarea(tareas.get(count));
                    //con la siguiente condicion se comprueba si la posible solucion sigue siendo factible para ser la mejor. En caso contrario
                    //no sigue llamando al metodo recursivamente
                    if (this.tiempoMaximoEjecucion(posSolucion) < this.tiempoMaximoEjecucion(solucion) || this.tiempoMaximoEjecucion(solucion) == 0 ) {
                    //llamado recursivo para seguir asignando tareas hasta que esten todas cargadas
                    solucion = back(solucion, posSolucion, count + 1);
                    //remuevo la tarea asignada para que se pueda usar devuelta en otro lugar
                    }
                    procesador.removeTarea(tareas.get(count));
                }
            }
        }
        return solucion;
    }

    private boolean soySolucion(List<Procesador> posSolucion) {
        //itero cada tarea
        for (Tarea tarea : tareas) {
            //condicion corte si una tarea no esta en ningun procesador
            boolean estoyAsignado = false;
            //itero cada procesador
            for (Procesador procesador : posSolucion) {
                //si un procesador tiene la tarea significa que esta asignada por lo que seguiria las iteraciones
                if (procesador.tengoTarea(tarea)) {
                    estoyAsignado = true;
                }
            }
            //caso de que una tarea no este asignada, posSolucion no es solucion
            if (!estoyAsignado) {
                return false;
            }
        }
        return true;
    }

    private boolean esLaMejorSolucion(List<Procesador> solucion, List<Procesador> posSolucion) {
        //retorna true si mi posSolucion es mejor que solucion
        return (tiempoMaximoEjecucion(posSolucion) < tiempoMaximoEjecucion(solucion) || tiempoMaximoEjecucion(solucion) == 0);
    }

    private void imprimirSolucion(List<Procesador> solucion) {
        System.out.println("Solucion obtenida : ");
        for (Procesador procesador : solucion) {
            procesador.imprimirProcesadorconTareas();
        }
        System.out.println("Solucion obtenida : " + tiempoMaximoEjecucion(solucion));
    }

    private List<Procesador> deepCopyProcesadores(List<Procesador> posSolucion) {
        List<Procesador> copy = new ArrayList<>();
        for (Procesador p : posSolucion) {
            copy.add(new Procesador(p.getId(), p.getCodigoProcesador(), p.isEstaRefrigerado(), p.getAnioFuncionamiento(), p.getTareas())); // Asume que Procesador tiene un constructor de copia
        }
        return copy;
    }
}
