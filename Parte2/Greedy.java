package Parte2;

import Parte_1.Procesador;
import Parte_1.Tarea;

import java.util.ArrayList;
import java.util.List;

public class Greedy {

    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private List<Procesador> solucion;
    private int metrica;
    private int indiceDeSobrecalentamiento;


    public Greedy(List<Procesador> pro, List<Tarea> tar, int indice) {
        this.procesadores = pro;
        this.tareas = tar;
        this.indiceDeSobrecalentamiento = indice;

    }

    /*Este algoritmo lo planteamos de la siguiente manera: nos paramos en una tarea de la lista de tareas y recorremos todos los procesadores,
    * quedandonos con el mas apto que encontremos. Para decidir cual es el mas apto tenemos varios metodos tanto en Greedy como en la clase Procesador, que corroboran
    * si es mas apto
    * La complejidad del algoritmo es de O(n*m) donde m es el numero de procesadores y n es el numero de tareas, ya que recorremos todos los procesadores
    * por la cantidad de tareas que hay.
    * */
    public void greedy() {

        metrica = 0;
        ArrayList<Procesador> aux = new ArrayList<>(procesadores);

        solucion = greedyPrivado(aux);

        System.out.println("Greedy");
        System.out.println("Solucion obtenida : ");
        for (Procesador procesador : solucion) {
            procesador.imprimirProcesadorconTareas();
        }
        System.out.println("Solucion obtenida : " + tiempoMaximoEjecucion(solucion));
        System.out.println("Metrica para analizar el costo de la solucion (cantidad de estados generados): " + metrica);
    }
    private List<Procesador> greedyPrivado(ArrayList<Procesador> auxProcesadores) {
        for (Procesador p : auxProcesadores) {
            p.setTareas(new ArrayList<>());
        }

        for (Tarea t : tareas) {
            Procesador mejorProcesador = null;
            for (Procesador p : auxProcesadores) {
                metrica++;
                if (p.puedoAddTarea(t, this.indiceDeSobrecalentamiento)) {
                    if (mejorProcesador == null) {
                        mejorProcesador = p;
                    }
                    if (p.esMejorProcesador(mejorProcesador)) {
                        mejorProcesador = p;
                    }
                }
            }
            if (mejorProcesador != null)
                mejorProcesador.addTarea(t);
        }
        return auxProcesadores;
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
}