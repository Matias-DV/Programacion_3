package Parte_1;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class Servicios {
    private HashMap<String, Tarea> hashTareas;
    private List<Tarea> tareasCriticas = new ArrayList<>();
    private List<Tarea> tareasNoCriticas = new ArrayList<>();



    // Complejidad: O(n)
    /*La complejidad del constructor es de O(n + m) ya que tenemos que recorrer todas las tareas (n)
     * para poder pasarlas de formato .csv a un hash map
     * y tambien debemos recorrer todos los procesadores (m) en el metodo readProcessors del reader.
     */


    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores);
        hashTareas = reader.readTasks(pathTareas);

        dividirTareas();
    }

    //Complejidad: O(1)
    /*En este servicio la complejidad es O(1) ya que la complejidad interna del metodo .get de un hash map es O(1)*/
    public Tarea servicio1(String ID) {
        if (!hashTareas.isEmpty())
            return hashTareas.get(ID);
        else return null;
    }



    //Complejidad: 0(1)
    /*En este servicio la complejidad es O(1) ya que tenemos de ante mano las 2 listas posibles a retornar, por lo tanto la complejidad es
    retornar una lista O(1)*/
    public List<Tarea> servicio2(boolean esCritica) {
        if (esCritica) {
            return tareasCriticas;
        } else {
            return tareasNoCriticas;
        }
    }

    //Complejidad: 0(n)
    /* En este servicio la complejidad es O(n) ya que es necesario recorrer toda una coleccion de objetos O(n)*/
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        Collection<Tarea> cole = getTareas();
        List<Tarea> rta = new LinkedList<>();
        if ((prioridadInferior > 0 && prioridadInferior < prioridadSuperior) && (prioridadSuperior < 100)) {
            if (!cole.isEmpty() ) {
                for (Tarea tarea : cole) {
                    if(tarea.getPrioridad() <= prioridadSuperior && tarea.getPrioridad() >= prioridadInferior) {
                        rta.add(tarea);
                    }
                }
            }
        } else {
            System.out.println("La prioridad de las tareas no esta dentro de un rango valido");
        }
        return rta;
    }

    private void dividirTareas() {
        Collection<Tarea> cole = getTareas();

        if (!cole.isEmpty()) {
            for (Tarea tarea : cole) {
                if (tarea.isEsCritica()) {
                    tareasCriticas.add(tarea);
                } else {
                    tareasNoCriticas.add(tarea);
                }
            }
        }

    }

    public Collection<Tarea> getTareas() {
        return hashTareas.values();
    }
}

