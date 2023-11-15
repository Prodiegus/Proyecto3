package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SimuladorSwapping {
    String tipoMemoria = "LRU"; // "LRU" o "FIFO
    List<Proceso> memoriaPrincipal = new ArrayList<>();
    List<Proceso> memoriaIntercambio = new ArrayList<>();
    LinkedList<Proceso> colaLRU = new LinkedList<>();
    Queue<Proceso> colaFIFO = new LinkedList<>();

    public void agregarProceso(String nombre, int quantum) {
        Proceso nuevoProceso = new Proceso(nombre, quantum);

        // Agregar a la memoria principal y a la cola FIFO
        memoriaPrincipal.add(nuevoProceso);
        colaFIFO.add(nuevoProceso);
    }

    public void ejecutarProcesos() {
        // Simulación de ejecución de procesos

        // Ejemplo: Se ejecuta un proceso de la memoria principal
        if (!memoriaPrincipal.isEmpty()) {
            Proceso procesoEjecutado = memoriaPrincipal.remove(0);
            System.out.println("Proceso ejecutado: " + procesoEjecutado.nombre);
            // Agregar a la cola LRU después de ejecutar
            colaLRU.add(procesoEjecutado);
        }

        // Simulación de reubicación según el algoritmo LRU
        if (!colaLRU.isEmpty()) {
            Proceso procesoLRU = colaLRU.removeFirst();
            System.out.println("Reubicación LRU: " + procesoLRU.nombre);
            // Agregar a la memoria de intercambio después de reubicar
            memoriaIntercambio.add(procesoLRU);
        }

        // Simulación de reubicación según el algoritmo FIFO
        if (!colaFIFO.isEmpty()) {
            Proceso procesoFIFO = colaFIFO.poll();
            System.out.println("Reubicación FIFO: " + procesoFIFO.nombre);
            // Agregar a la memoria de intercambio después de reubicar
            memoriaIntercambio.add(procesoFIFO);
        }
    }

    public void verProcesosMemoriaPrincipal() {
        // Mostrar procesos en memoria principal 
        // salida en color rosa
        System.out.println("\u001B[35mProcesos en memoria principal\u001B[0m");
        for (Proceso proceso : memoriaPrincipal) {
            System.out.println("Proceso: " + proceso.nombre);
        }
    }

    public void verProcesosMemoriaIntercambio() {
        // Mostrar procesos en memoria de intercambio
        // salida en color rosa
        System.out.println("\u001B[35mProcesos en memoria de intercambio\u001B[0m");
        for (Proceso proceso : memoriaIntercambio) {
            System.out.println("Proceso: " + proceso.nombre);
        }
    }

    public void verTodosLosProcesos() {
        // Mostrar todos los procesos
        // salida en color rosa
        System.out.println("\u001B[35mProcesos en memoria principal\u001B[0m");
        for (Proceso proceso : memoriaPrincipal) {
            System.out.println("Proceso: " + proceso.nombre);
        }
        //salida en color rosa
        System.out.println("\u001B[35mProcesos en memoria de intercambio\u001B[0m");
        for (Proceso proceso : memoriaIntercambio) {
            System.out.println("Proceso: " + proceso.nombre);
        }
    }

    public void verColaProcesos() {
        // Mostrar cola de procesos
        // salida en color rosa
        System.out.println("\u001B[35mCola de procesos\u001B[0m");
        if (tipoMemoria.equals("LRU")) {
            for (Proceso proceso : colaLRU) {
                System.out.println("Proceso: " + proceso.nombre);
            }
        } else {
            for (Proceso proceso : colaFIFO) {
                System.out.println("Proceso: " + proceso.nombre);
            }
        }
    }

    public void cambiarAlgoritmo(String tipoMemoria) {
        // Cambiar algoritmo de reubicación
        this.tipoMemoria = tipoMemoria;
    }
    
    public static void main(String[] args) {
        SimuladorSwapping simulador = new SimuladorSwapping();

        // Agregar procesos de ejemplo
        simulador.agregarProceso("ProcesoA", 5);
        simulador.agregarProceso("ProcesoB", 8);
        simulador.agregarProceso("ProcesoC", 3);

        // Ejecutar procesos y simular reubicación
        simulador.ejecutarProcesos();
        simulador.ejecutarProcesos();
    }
}
