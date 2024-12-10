package Parte_1;

import java.util.Objects;

public class Tarea {
    private String id;
    private String nombre;
    private int tiempoEjecucion;
    private boolean esCritica;
    private int prioridad;

    public Tarea(String id, String nombre, int tiempoEjecucion, boolean esCritica, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
        this.esCritica = esCritica;
        this.prioridad = prioridad;
        if (prioridad > 0 && prioridad < 100) {
            this.prioridad = prioridad;
        } else {
            this.prioridad = 100;
        }
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tiempoEjecucion=" + tiempoEjecucion +
                ", esCritica=" + esCritica +
                ", prioridad=" + prioridad +
                '}' + '\n';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public boolean isEsCritica() {
        return esCritica;
    }

    public void setEsCritica(boolean esCritica) {
        this.esCritica = esCritica;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
