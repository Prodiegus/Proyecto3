package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SimuladorSwapping {
    String tipoMemoria; // "LRU" o "FIFO"
    Proceso[] memoriaPrincipal;
    Proceso[] memoriaIntercambio;
    LinkedList<Proceso> colaLRUMain;
    Queue<Proceso> colaFIFOMain;
    LinkedList<Proceso> colaLRUSwap = new LinkedList<>();
    Queue<Proceso> colaFIFOSwap = new LinkedList<>();

    public SimuladorSwapping(int tamano_memoria_intercambio, int tamano_memoria_principal, String tipoMemoria) {
        this.tipoMemoria = tipoMemoria;
        this.memoriaPrincipal = new Proceso[tamano_memoria_principal];
        this.memoriaIntercambio = new Proceso[tamano_memoria_intercambio];
        this.colaLRUMain = new LinkedList<>();
        this.colaFIFOMain = new LinkedList<>();
        this.colaLRUSwap = new LinkedList<>();
        this.colaFIFOSwap = new LinkedList<>();
    }

    public void agregarProceso(String nombre, int quantum) {
        Proceso nuevoProceso = new Proceso(nombre, quantum);
        Proceso aux = nuevoProceso; // auxiliar para agregar a la cola
        Proceso aux2 = nuevoProceso; // auxiliar para agregar a la cola
        Boolean espacioDisponible = true;
        Boolean espacioEnSwap = true;
        /**
         * Agregar proceso a la memoria principal si hay espacio disponible
         */
        for (int i = 0; i < memoriaPrincipal.length; i++) {
            if (memoriaPrincipal[i] == null) {
                memoriaPrincipal[i] = nuevoProceso;
                break;
            }
            // Si no hay espacio disponible, utilizar memoria de intercambio y agregar a la cola
            if (i == memoriaPrincipal.length - 1) {
                espacioDisponible = false;
            }
        }
        if (!espacioDisponible) {
            if (tipoMemoria.equals("LRU")) {
                // sacamos de la cola el proceso que sea last recently used
                aux = colaLRUMain.removeFirst();
                // buscamos el proceso en la memoria principal
                for (int i = 0; i < memoriaPrincipal.length; i++) {
                    if (memoriaPrincipal[i].equals(aux)) {
                        // agregamos el proceso a la memoria de intercambio
                        memoriaPrincipal[i] = nuevoProceso;
                        for (int j = 0; j < memoriaIntercambio.length; j++) {
                            if (memoriaIntercambio[j] == null) {
                                memoriaIntercambio[j] = aux;
                                colaLRUSwap.add(aux);
                                colaFIFOSwap.add(aux);
                                break;
                            }
                            // Si no hay espacio en la memoria de intercambio usamos la cola
                            if (j == memoriaIntercambio.length - 1) {
                                espacioEnSwap = false;
                            }
                        }
                        if (!espacioEnSwap) {
                            // sacamos de la cola el proceso que sea last recently used
                            aux2 = colaLRUSwap.removeFirst();
                            // buscamos el proceso en la memoria intercamio
                            for (int k = 0; k < memoriaIntercambio.length; k++) {
                                if (memoriaIntercambio[k].equals(aux2)) {
                                    // agregamos el proceso a la memoria de intercambio
                                    memoriaIntercambio[k] = aux;
                                    colaLRUSwap.add(aux);
                                    colaFIFOSwap.add(aux);
                                    break;
                                }
                            }
                        }
                        // agregamos el proceso a la cola
                        colaLRUMain.add(nuevoProceso);
                        colaFIFOMain.add(nuevoProceso);
                        break;
                    }
                }
            } else if (tipoMemoria.equals("FIFO")){
                // sacamos de la cola el proceso que sea first in first out
                aux = colaFIFOMain.poll();
                // buscamos el proceso en la memoria principal
                for (int i = 0; i < memoriaPrincipal.length; i++) {
                    if (memoriaPrincipal[i].equals(aux)) {
                        // agregamos el proceso a la memoria de intercambio
                        memoriaPrincipal[i] = nuevoProceso;
                        for (int j = 0; j < memoriaIntercambio.length; j++) {
                            if (memoriaIntercambio[j] == null) {
                                memoriaIntercambio[j] = aux;
                                colaFIFOSwap.add(aux);
                                colaLRUSwap.add(aux);
                                break;
                            }
                            // Si no hay espacio en la memoria de intercambio usamos la cola
                            if (j == memoriaIntercambio.length - 1) {
                                espacioEnSwap = false;
                            }
                        }
                        if (!espacioEnSwap) {
                            // sacamos de la cola el proceso que sea first in first out
                            aux2 = colaFIFOSwap.poll();
                            // buscamos el proceso en la memoria intercamio
                            for (int k = 0; k < memoriaIntercambio.length; k++) {
                                if (memoriaIntercambio[k].equals(aux2)) {
                                    // agregamos el proceso a la memoria de intercambio
                                    memoriaIntercambio[k] = aux;
                                    colaFIFOSwap.add(aux);
                                    colaLRUSwap.add(aux);
                                    break;
                                }
                            }
                        }
                        // agregamos el proceso a la cola
                        colaFIFOMain.add(nuevoProceso);
                        colaLRUMain.add(nuevoProceso);
                        break;
                    }
                }
            }
        }
    }

    public void ejecutarProcesos() {
        
    }

    public void verProcesosMemoriaPrincipal() {
        // Mostrar procesos en memoria principal 
        // salida en color rosa
        System.out.println("\u001B[35mProcesos en memoria principal\u001B[0m");
        System.out.println("-----------------------------");
        for (Proceso proceso : memoriaPrincipal) {
            System.out.println("| " + ((proceso!=null) ? proceso : "      Celda | vacia      ") + " |");
        }
        System.out.println("-----------------------------");
    }

    public void verProcesosMemoriaIntercambio() {
        // Mostrar procesos en memoria de intercambio
        // salida en color rosa
        System.out.println("\u001B[35mProcesos en memoria de intercambio\u001B[0m");
        System.out.println("-----------------------------");
        for (Proceso proceso : memoriaIntercambio) {
            System.out.println("| " + ((proceso!=null) ? proceso : "      Celda | vacia      ") + " |");
        }
        System.out.println("-----------------------------");
    }

    public void verTodosLosProcesos() {
        ArrayList<String> salidas = new ArrayList<>();
        ArrayList<String[]> procesos = new ArrayList<>();
        String salida = "";
        // Mostrar todos los procesos
        // salida en color rosa
        salida = ("\u001B[35mProcesos en memoria principal\u001B[0m \t \u001B[35mProcesos en memoria de intercambio\u001B[0m");
        salidas.add(salida);
        int memoriaPrincipalLength = memoriaPrincipal.length;
        int memoriaIntercambioLength = memoriaIntercambio.length;
        int max = Math.max(memoriaPrincipalLength, memoriaIntercambioLength);
        for (int i = 0; i < max; i++) {
            if (i >= memoriaPrincipalLength) {
                salida = ("| \t | \t | " + ((memoriaIntercambio[i]!=null) ? memoriaIntercambio[i] : "      Celda | vacia      ") + " |");
                salidas.add(salida);
                continue;
            } else if (i >= memoriaIntercambioLength) {
                salida = ("| " + ((memoriaPrincipal[i]!=null) ? memoriaPrincipal[i] : "      Celda | vacia      ") + " | \t | \t |");
                salidas.add(salida);
                continue;
            }else {
                salida =  ("| " + ((memoriaPrincipal[i]!=null) ? memoriaPrincipal[i] : "      Celda | vacia      ") + " | \t | " +((memoriaIntercambio[i]!=null) ? memoriaIntercambio[i] : "      Celda | vacia      ") + " |");
                salidas.add(salida);
            }
        }
        // eliminamos los espacios vacios en las salidas
        procesos.add(salidas.get(0).split("\t"));
        for (int i = 1; i < salidas.size(); i++) {
            salida = salidas.get(i);
            salida = salida.replace(" ", "");
            procesos.add(salida.split("\t"));
        }
        // calamos la cadena mas larga en 0 y 1
        int max0 = 0;
        int max1 = 0;
        for (int i = 0; i < procesos.size(); i++) {
            String[] proceso = procesos.get(i);
            if (proceso[0].length() > max0) {
                max0 = proceso[0].length();
            }
            if (proceso[1].length() > max1) {
                max1 = proceso[1].length();
            }
        }
        // alargamos las cadenas mas cortas con espacios antes del ultimo |
        for (int i = 0; i < procesos.size(); i++) {
            String[] proceso = procesos.get(i);
            if (proceso[0].length() < max0) {
                int diferencia = max0 - proceso[0].length();
                for (int j = 0; j < diferencia; j++) {
                    proceso[0] = " " + proceso[0];
                }
                procesos.set(i, proceso);
            }
            if (proceso[1].length() < max1) {
                int diferencia = max1 - proceso[1].length();
                for (int j = 0; j < diferencia; j++) {
                    proceso[1] = " " + proceso[1];
                }
                procesos.set(i, proceso);
            }
        }
        System.out.println(procesos.get(0)[0] + "\t\t" + procesos.get(0)[1]);
        // imprmimos tantos - como la cadena mas larga en 0 y 1 y separamos con un tab
        for (int i = 0; i < max0; i++) {
            System.out.print("-");
        }
        System.out.print("\t");
        for (int i = 0; i < max1; i++) {
            System.out.print("-");
        }
        System.out.println();
        // imprimimos los procesos
        for (int i = 1; i < procesos.size(); i++) {
            System.out.println(procesos.get(i)[0] + "\t" + procesos.get(i)[1]);
        }
        for (int i = 0; i < max0; i++) {
            System.out.print("-");
        }
        System.out.print("\t");
        for (int i = 0; i < max1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void verColaProcesos() {
        // Mostrar cola de procesos
        // salida en color rosa
        System.out.println("\u001B[35mCola de procesos Principal\u001B[0m");
        if (tipoMemoria.equals("LRU")) {
            for (Proceso proceso : colaLRUMain) {
                System.out.println("Proceso: " + proceso.nombre);
            }
        } else {
            for (Proceso proceso : colaFIFOMain) {
                System.out.println("Proceso: " + proceso.nombre);
            }
        }

        System.out.println("\u001B[35mCola de procesos Swap\u001B[0m");
        if (tipoMemoria.equals("LRU")) {
            for (Proceso proceso : colaLRUSwap) {
                System.out.println("Proceso: " + proceso.nombre);
            }
        } else {
            for (Proceso proceso : colaFIFOSwap) {
                System.out.println("Proceso: " + proceso.nombre);
            }
        }
    }

    public void cambiarAlgoritmo(String tipoMemoria) {
        // Cambiar algoritmo de reubicación
        this.tipoMemoria = tipoMemoria;
    }
    

    public void eliminarProceso(String nombre) {
        // Eliminar proceso de la memoria principal
        for (int i = 0; i < memoriaPrincipal.length; i++) {
            if (memoriaPrincipal[i] != null && memoriaPrincipal[i].nombre.equals(nombre)) {
                memoriaPrincipal[i] = null;
                break;
            }
        }

        // Eliminar proceso de la memoria de intercambio
        for (int i = 0; i < memoriaIntercambio.length; i++) {
            if (memoriaIntercambio[i] != null && memoriaIntercambio[i].nombre.equals(nombre)) {
                memoriaIntercambio[i] = null;
                break;
            }
        }

        // Eliminar proceso de la cola LRU
        for (int i = 0; i < colaLRUMain.size(); i++) {
            if (colaLRUMain.get(i).nombre.equals(nombre)) {
                colaLRUMain.remove(i);
                break;
            }
        }
        for (int i = 0; i < colaLRUSwap.size(); i++) {
            if (colaLRUSwap.get(i).nombre.equals(nombre)) {
                colaLRUSwap.remove(i);
                break;
            }
        }

        // Eliminar proceso de la cola FIFO
        for (int i = 0; i < colaFIFOMain.size(); i++) {
            if(colaFIFOMain.peek().nombre.equals(nombre)){
                colaFIFOMain.remove();
                break;
            }
        }

        for (int i = 0; i < colaFIFOSwap.size(); i++) {
            if(colaFIFOSwap.peek().nombre.equals(nombre)){
                colaFIFOSwap.remove();
                break;
            }
        }
    }

    public static void main(String[] args) {
        SimuladorSwapping simulador = new SimuladorSwapping(10, 5, "LRU");

        // Agregar procesos de ejemplo
        simulador.agregarProceso("ProcesoA", 5);
        simulador.agregarProceso("ProcesoB", 8);
        simulador.agregarProceso("ProcesoC", 3);

        // Ejecutar procesos y simular reubicación
        simulador.ejecutarProcesos();
        simulador.ejecutarProcesos();
    }
}
