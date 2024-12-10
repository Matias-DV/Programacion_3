package Parte_1;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private String id;
    private String codigoProcesador;
    private boolean estaRefrigerado;
    private int anioFuncionamiento;
    private List<Tarea> tareas;

    public Procesador(String id, String codigoProcesador, boolean estaRefrigerado, int anioFuncionamiento) {
        this.id = id;
        this.codigoProcesador = codigoProcesador;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        this.tareas = new ArrayList<>();
    }

    public Procesador(String id, String codigoProcesador, boolean estaRefrigerado, int anioFuncionamiento, List<Tarea> tareasnuevas) {
        this.id = id;
        this.codigoProcesador = codigoProcesador;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        this.setTareas(tareasnuevas);
    }

    //Cambios mati

    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas);
    }

    public void setTareas(List<Tarea> list) {
        this.tareas = list;
    }

    public boolean puedoAddTarea(Tarea t, int n) {
        if (t.isEsCritica()) {
            if (this.cantTareasCriticas() < 2) {
                if (this.isEstaRefrigerado()) {
                    return true;
                } else if (!sobrecalentamiento(t, n)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (this.isEstaRefrigerado()) {
            return true;
        } else if (!sobrecalentamiento(t, n)) {
            return true;
        }
        return false;
    }

    public void addTarea(Tarea t) {
        tareas.add(t);
    }

    public void removeTarea(Tarea t) {
        tareas.remove(t);
    }

    public boolean tengoTarea(Tarea t) {
        return tareas.contains(t);
    }

    public int tiempoMaximo() {
        int total = 0;
        for (Tarea t : tareas) {
            total += t.getTiempoEjecucion();
        }
        return total;
    }

    public void imprimirProcesadorconTareas() {
        System.out.print(this.getId() + " : ");
        for (Tarea t : tareas) {
            System.out.print(t.getId() + " , ");
        }
    }

    private int cantTareasCriticas() {
        int rta = 0;
        for (Tarea t : tareas) {
            if (t.isEsCritica()) {
                rta++;
            }
        }
        return rta;
    }

    public boolean sobrecalentamiento(Tarea t, int n) {
        if (this.isEstaRefrigerado()) {
            return false;
        } else if (tiempoMaximo() + t.getTiempoEjecucion() > n) {
            return true;
        }
        return false;
    }

    // devuelve true cuando el procesador es mejor (menor tiempo de ejercucion) que otro.
    public boolean esMejorProcesador(Procesador mejorP) {
        return (this.tiempoMaximo() < mejorP.tiempoMaximo());
    }

    // ---------------------------------------------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoProcesador() {
        return codigoProcesador;
    }

    public void setCodigoProcesador(String codigoProcesador) {
        this.codigoProcesador = codigoProcesador;
    }

    public boolean isEstaRefrigerado() {
        return estaRefrigerado;
    }

    public void setEstaRefrigerado(boolean estaRefrigerado) {
        this.estaRefrigerado = estaRefrigerado;
    }

    public int getAnioFuncionamiento() {
        return anioFuncionamiento;
    }

    public void setAnioFuncionamiento(int anioFuncionamiento) {
        this.anioFuncionamiento = anioFuncionamiento;
    }
}
