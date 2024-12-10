import Parte2.Backtraking;
import Parte2.Greedy;
import Parte_1.CSVReader;
import Parte_1.Procesador;
import Parte_1.Servicios;
import Parte_1.Tarea;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String pathProcesadores = "C:\\Users\\USUARIO\\Desktop\\Prog3_TPE\\Dataset\\Procesadores.csv";//cambiar dependiendo la pc
        String pathTareas = "C:\\Users\\USUARIO\\Desktop\\Prog3_TPE\\Dataset\\Tareas.csv";

        Servicios servicios = new Servicios(pathProcesadores, pathTareas);

        System.out.println("parte 1");

        if (servicios.servicio1("T1") == null) {
            System.out.println("LA TAREA NO EXISTE");
        } else {
            System.out.println(servicios.servicio1("T1"));
        }
        System.out.println(servicios.servicio2(false));
        System.out.println(servicios.servicio3(60, 70));

        //Parte 2.
        System.out.println("--------------------------------------------------------------");
        System.out.println("Parte 2");
        CSVReader csv = new CSVReader();
        List<Procesador> procesadores1;
        List<Tarea> tareas = new ArrayList<>(servicios.servicio2(true));
        tareas.addAll(servicios.servicio2(false));


        procesadores1 = csv.readProcessors(pathProcesadores);
        int n = 200;
        Backtraking backPrueba = new Backtraking(procesadores1, tareas,n);
        backPrueba.backtracking();

        System.out.println("\n");

        Greedy g = new Greedy(procesadores1, tareas, 10);
        g.greedy();
    }
}