import src.SimuladorSwapping;

public class Main {
    /**
     * Método principal
     * @param args
     * Por la linea de comandos se leera el tipo de memoria que se usara en la simulacion (LRU o FIFO)
     * Ademas dentro el main tendra otro menu el cual operara
     */
    public static void main(String[] args) {
        String tipoMemoria = "FIFO"; // "LRU" o "FIFO
        // leemos la linea de comandos
        if (args.length > 0) {
            tipoMemoria = args[0];
        }
        SimuladorSwapping simulador = new SimuladorSwapping();
        String nombre;
        int quantum;
        while (true) {
            //limpiamos la pantalla
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // menu de opciones
            // bienvenido estara en color azul
            System.out.println("\u001B[34mSimulador swapping\u001B[0m");
            // tipo de memoria estara en color rojo
            System.out.println("Tipo de memoria: \u001B[31m"+tipoMemoria+"\u001B[0m");
            System.out.println("Ingrese la opcion que desea realizar");
            // el numero de la opcion estara en color verde
            System.out.println("\u001B[32m 1.\u001B[0m"+" Agregar proceso");
            System.out.println("\u001B[32m 2.\u001B[0m"+" Ejecutar procesos");
            System.out.println("\u001B[32m 3.\u001B[0m"+" Ver Procesos en memoria principal");
            System.out.println("\u001B[32m 4.\u001B[0m"+" Ver Procesos en memoria de intercambio");
            System.out.println("\u001B[32m 5.\u001B[0m"+" Ver todos los procesos");
            System.out.println("\u001B[32m 6.\u001B[0m"+" Ver cola de procesos");
            System.out.println("\u001B[32m 7.\u001B[0m"+" Cambiar algoritmo de reubicacion");
            System.out.println("\u001B[32m 0.\u001B[0m"+" Salir");
            int opcion = Integer.parseInt(System.console().readLine());
            // una vez que se lea la opcion se le borrara la vista de la entrada al usuario
            System.out.print("\033[H\033[2J");
            switch (opcion) {
                case 1:
                    System.out.print("\u001B[34mIngrese el nombre del proceso: \u001B[0m");
                    // texto que ingrese el usuario estara en color azul como tambien lo que se escriba en la consola
                    nombre = "\u001B[34m"+System.console().readLine()+"\u001B[0m";
                    //salida en color morado
                    System.out.print("\u001B[35mIngrese el quantum del proceso: \u001B[0m");
                    quantum = Integer.parseInt(System.console().readLine());
                    simulador.agregarProceso(nombre, quantum);
                    break;
                case 2:
                    simulador.ejecutarProcesos();
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                    break;
                case 3:
                    simulador.verProcesosMemoriaPrincipal();
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                    break;
                case 4:
                    simulador.verProcesosMemoriaIntercambio();
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                    break;
                case 5:
                    simulador.verTodosLosProcesos();
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                    break;
                case 6:
                    simulador.verColaProcesos();
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                    break;
                case 7:
                    // validamos que el tipo de memoria sea LRU o FIFO
                    do {
                        System.out.print("\u001B[34mIngrese el tipo de memoria (LRU o FIFO): \u001B[34m");
                        tipoMemoria = System.console().readLine();
                    } while (!tipoMemoria.equals("LRU") && !tipoMemoria.equals("FIFO"));
                    simulador.cambiarAlgoritmo(tipoMemoria);
                    break;
                case 0:
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
