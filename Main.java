import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import src.SimuladorSwapping;

public class Main {
    /**
     * Método principal
     * @param args
     * Por la linea de comandos se leera el tipo de memoria que se usara en la simulacion (LRU o FIFO)
     * Ademas dentro el main tendra otro menu el cual operara
     * Tambien se leera el tamano de la memoria principal y de la memoria de intercambio
     * con el formato: 
     *  java Main (-FIFO o -LRU) 
     *  -TMP <tamano_memoria_principal> 
     *  -TMI <tamano_memoria_intercambio>
     *  -help o -h para pedir ayuda
     */
    public static void main(String[] args) {
        String tipoMemoria = "FIFO"; // "LRU" o "FIFO
        int tamanoMemoriaPrincipal = 10;
        int tamanoMemoriaIntercambio = 10;

        String nombre;
        String titulo = "\n"+
            "\t┌───┐      ┌┐     ┌┐\n"+
            "\t│┌─┐│      ││     ││\n"+
            "\t│└──┬┬┐┌┬┐┌┤│┌──┬─┘├──┬─┐\n"+
            "\t└──┐├┤└┘││││││┌┐│┌┐│┌┐│┌┘\n"+
            "\t│└─┘│││││└┘│└┤┌┐│└┘│└┘││\n"+
            "\t└───┴┴┴┴┴──┴─┴┘└┴──┴──┴┘\n"+
            "\t┌───┐\n"+
            "\t│┌─┐│\n"+
            "\t│└──┬┐┌┐┌┬──┬──┬──┬┬─┐┌──┐\n"+
            "\t└──┐│└┘└┘│┌┐│┌┐│┌┐├┤┌┐┤┌┐│\n"+
            "\t│└─┘├┐┌┐┌┤┌┐│└┘│└┘│││││└┘│\n"+
            "\t└───┘└┘└┘└┘└┤┌─┤┌─┴┴┘└┴─┐│\n"+
            "\t            ││ ││     ┌─┘│\n"+
            "\t            └┘ └┘     └──┘\n";
        int quantum;
        if (args.length == 0) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\n\u001B[33m"+titulo+"\u001B[0m");
            System.out.println("Este programa utliza la linea de comandos: ");
            System.out.println("\u001B[32m-FIFO\u001B[0m o \u001B[32m-LRU\u001B[0m para cambiar el algoritmo de reubicacion");
            System.out.println("\u001B[32m-TMP\u001B[0m \u001B[30m<tamano_memoria_principal>\u001B[0m para cambiar el tamano de la memoria principal");
            System.out.println("\u001B[32m-TMI\u001B[0m \u001B[30m<tamano_memoria_intercambio>\u001B[0m para cambiar el tamano de la memoria de intercambio");
            System.out.println("\u001B[32m-help\u001B[0m o \u001B[32m-h\u001B[0m para ver la ayuda\n");

            System.out.println("\u001B[34mValores actuales: \u001B[0m");
            System.out.println("Tipo de memoria: \u001B[31m"+tipoMemoria+"\u001B[0m");
            System.out.println("Tamano de la memoria principal: \u001B[31m"+tamanoMemoriaPrincipal+"\u001B[0m");
            System.out.println("Tamano de la memoria de intercambio: \u001B[31m"+tamanoMemoriaIntercambio+"\u001B[0m\n");
            System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
            System.console().readLine();
        }
        // leemos la linea de comandos y validamos el formato de entrada si la linea de comando esta vacia mostramos ayuda y continuamos el programa con opciones default
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-FIFO") || args[i].equals("-LRU")) {
                tipoMemoria = args[i].substring(1);
            } else if (args[i].equals("-TMP")) {
                // verificamos que el tamano de la memoria principal sea un numero entero mayor a 0
                try {
                    if (Integer.parseInt(args[i+1]) > 0) {
                        tamanoMemoriaPrincipal = Integer.parseInt(args[i+1]);
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        // mostramos el titulo en color morado
                        System.out.println("\n\u001B[35m"+titulo+"\u001B[0m");
                        System.out.println("El tamano de la memoria principal debe ser un numero entero mayor a 0");
                        System.out.println("Se usara el tamano de la memoria principal por default: \u001B[31m"+tamanoMemoriaPrincipal+"\u001B[0m");
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                        System.console().readLine();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                } catch (Exception e) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    // mostramos el titulo en color morado
                    System.out.println("\n\u001B[35m"+titulo+"\u001B[0m");
                    System.out.println("El tamano de la memoria principal debe ser un numero entero mayor a 0");
                    System.out.println("Se usara el tamano de la memoria principal por default: \u001B[31m"+tamanoMemoriaPrincipal+"\u001B[0m");
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } else if (args[i].equals("-TMI")) {
                // verificamos que el tamano de la memoria de intercambio sea un numero entero mayor a 0
                try {
                    if (Integer.parseInt(args[i+1]) > 0) {
                        tamanoMemoriaIntercambio = Integer.parseInt(args[i+1]);
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        // mostramos el titulo en color morado
                        System.out.println("\n\u001B[35m"+titulo+"\u001B[0m");
                        System.out.println("El tamano de la memoria de intercambio debe ser un numero entero mayor a 0");
                        System.out.println("Se usara el tamano de la memoria de intercambio por default: \u001B[31m"+tamanoMemoriaIntercambio+"\u001B[0m");
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                        System.console().readLine();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                } catch (Exception e) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    // mostramos el titulo en color morado
                    System.out.println("\n\u001B[35m"+titulo+"\u001B[0m");
                    System.out.println("El tamano de la memoria de intercambio debe ser un numero entero mayor a 0");
                    System.out.println("Se usara el tamano de la memoria de intercambio por default: \u001B[31m"+tamanoMemoriaIntercambio+"\u001B[0m");
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } else if (args[i].equals("-help") || args[i].equals("-h")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                // mostramos el titulo en color morado
                System.out.println("\n\u001B[35m"+titulo+"\u001B[0m");
                System.out.println("Comandos validos: ");
                System.out.println("\u001B[32m-FIFO\u001B[0m o \u001B[32m-LRU\u001B[0m para cambiar el algoritmo de reubicacion");
                System.out.println("\u001B[32m-TMP\u001B[0m \u001B[30m<tamano_memoria_principal>\u001B[0m para cambiar el tamano de la memoria principal");
                System.out.println("\u001B[32m-TMI\u001B[0m \u001B[30m<tamano_memoria_intercambio>\u001B[0m para cambiar el tamano de la memoria de intercambio");
                System.out.println("\u001B[32m-help\u001B[0m o \u001B[32m-h\u001B[0m para ver la ayuda");
                System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                System.console().readLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.exit(0);
            }
        }
        SimuladorSwapping simulador = new SimuladorSwapping(tamanoMemoriaIntercambio, tamanoMemoriaPrincipal, tipoMemoria);
        simulador.cambiarAlgoritmo(tipoMemoria);
        
        while (true) {
            try {
                //limpiamos la pantalla
                System.out.print("\033[H\033[2J");
                System.out.flush();
                // menu de opciones
                // bienvenido estara en color azul
                System.out.println("\n\u001B[34m"+titulo+"\u001B[0m"); 
                // nombre del autor en color verde
                System.out.println("\u001B[32mAutor: \u001B[0m"+"\u001B[01m\u001B[35mDiego Fernandez\u001B[0m");
                // tipo de memoria estara en color rojo
                System.out.println("\u001B[21mTipo de memoria:\u001B[0m \u001B[31m"+tipoMemoria+"\u001B[0m\n");
                // mostramos la memoria principal y la memoria de intercambio
                simulador.verTodosLosProcesos();
                System.out.print("Ingrese la opcion que desea realizar \u001B[32m-help\u001B[0m para ver las opciones: \u001B[34m");
                // el numero de la opcion estara en color verde
                String opcion[] = System.console().readLine().split(" ");
                // una vez que se lea la opcion se le borrara la vista de la entrada al usuario
                System.out.print("\u001B[0m\033[H\033[2J");
                System.out.flush();
                System.out.println("\n\u001B[35m"+titulo+"\u001B[0m");
                if (opcion[0].equals("add")) {
                    if(opcion[1].equals("-l")){
                        //capturamos el lote de procesos
                        nombre = opcion[2];
                        //leemos el archivo
                        try {
                            File archivo = new File("lotes/"+nombre);
                            FileReader fr = new FileReader(archivo);
                            BufferedReader br = new BufferedReader(fr);
                            String linea;
                            while((linea = br.readLine()) != null){
                                String[] proceso = linea.split(" ");
                                nombre = "\u001B[34m"+proceso[0]+"\u001B[0m";
                                quantum = Integer.parseInt(proceso[1]);
                                simulador.agregarProceso(nombre, quantum);
                            }
                            br.close();
                        } catch (Exception e) {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            // mostramos el titulo en color morado
                            System.out.println("\n\u001B[35m"+titulo+"\u001B[0m");
                            System.out.println("No se encontro el lote\n");
                            System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                            System.console().readLine();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        }
                    }else{
                        nombre = "\u001B[34m"+opcion[1]+"\u001B[0m";
                        quantum = Integer.parseInt(opcion[2]);
                        simulador.agregarProceso(nombre, quantum);
                    }
                } else if (opcion[0].equals("run")) {
                    simulador.ejecutarProcesos();
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                } else if (opcion[0].equals("look")) {
                    if (opcion.length == 1) {
                        System.out.println("\u001B[32m add <nombre_proceso> <quantum>.\u001B[0m"+" Agregar proceso");
                        System.out.println("\u001B[32m add -l <Nombre_Archivo.txt>.\u001B[0m"+" Agrega un lote de procesos que debe ser cargado desde un fichero");
                        System.out.println("\u001B[32m run\u001B[0m"+" Ejecutar procesos");
                        System.out.println("\u001B[32m look -main\u001B[0m"+" Ver Procesos en memoria principal");
                        System.out.println("\u001B[32m look -swap\u001B[0m"+" Ver Procesos en memoria de intercambio");
                        System.out.println("\u001B[32m look -all\u001B[0m"+" Ver todos los procesos");
                        System.out.println("\u001B[32m look -queue\u001B[0m"+" Ver cola de procesos");
                        System.out.println("\u001B[32m look -help\u001B[0m"+" Ver opciones de look");
                        System.out.println("\u001B[32m look \u001B[0m"+" Ver opciones de look");
                        System.out.println("\u001B[32m kill <nombre_proceso>.\u001B[0m"+" Eliminar proceso");
                        System.out.println("\u001B[32m change (-FIFO o -LRU).\u001B[0m"+" Cambiar algoritmo de reubicacion");
                        System.out.println("\u001B[32m exit.\u001B[0m"+" Salir");
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                    } else if (opcion[1].equals("-main")) {
                        simulador.verProcesosMemoriaPrincipal();
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                        System.console().readLine();
                    } else if (opcion[1].equals("-swap")) {
                        simulador.verProcesosMemoriaIntercambio();
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                        System.console().readLine();
                    } else if (opcion[1].equals("-all")) {
                        simulador.verTodosLosProcesos();
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                        System.console().readLine();
                    } else if (opcion[1].equals("-queue")) {
                        simulador.verColaProcesos();
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                        System.console().readLine();
                    } else if (opcion[1].equals("-help")) {
                        System.out.println("\u001B[32m add <nombre_proceso> <quantum>.\u001B[0m"+" Agregar proceso");
                        System.out.println("\u001B[32m add -l <Nombre_Archivo.txt>.\u001B[0m"+" Agrega un lote de procesos que debe ser cargado desde un fichero");
                        System.out.println("\u001B[32m run\u001B[0m"+" Ejecutar procesos");
                        System.out.println("\u001B[32m look -main\u001B[0m"+" Ver Procesos en memoria principal");
                        System.out.println("\u001B[32m look -swap\u001B[0m"+" Ver Procesos en memoria de intercambio");
                        System.out.println("\u001B[32m look -all\u001B[0m"+" Ver todos los procesos");
                        System.out.println("\u001B[32m look -queue\u001B[0m"+" Ver cola de procesos");
                        System.out.println("\u001B[32m look -help\u001B[0m"+" Ver opciones de look");
                        System.out.println("\u001B[32m look \u001B[0m"+" Ver opciones de look");
                        System.out.println("\u001B[32m kill <nombre_proceso>.\u001B[0m"+" Eliminar proceso");
                        System.out.println("\u001B[32m change (-FIFO o -LRU).\u001B[0m"+" Cambiar algoritmo de reubicacion");
                        System.out.println("\u001B[32m exit\u001B[0m"+" Salir");
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                        System.console().readLine();
                    } else {
                        System.out.println("\u001B[32m look -main\u001B[0m"+" Ver Procesos en memoria principal");
                        System.out.println("\u001B[32m look -swap\u001B[0m"+" Ver Procesos en memoria de intercambio");
                        System.out.println("\u001B[32m look -all\u001B[0m"+" Ver todos los procesos");
                        System.out.println("\u001B[32m look -queue\u001B[0m"+" Ver cola de procesos");
                        System.out.println("\u001B[32m look -help\u001B[0m"+" Ver opciones de look");
                        System.out.println("\u001B[32m look \u001B[0m"+" Ver opciones de look");
                        System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                        System.console().readLine();
                    }
                } else if (opcion[0].equals("kill")) {
                    nombre = "\u001B[34m"+opcion[1]+"\u001B[0m";
                    simulador.eliminarProceso(nombre);
                    System.out.println("\u001B[35mProceso: \u001B[0m"+nombre+" \u001B[31meliminado\u001B[0m");
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                } else if (opcion[0].equals("change")) {
                    if (opcion[1].equals("-FIFO") || opcion[1].equals("-LRU")) {
                        tipoMemoria = opcion[1].substring(1);
                        simulador.cambiarAlgoritmo(tipoMemoria);
                    } else {
                        System.out.println("Opcion no valida");
                    }
                } else if (opcion[0].equals("exit")) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.exit(0);
                }else if (opcion[0].equals("-help")) {
                    System.out.println("\u001B[32m add <nombre_proceso> <quantum>.\u001B[0m"+" Agregar proceso");
                    System.out.println("\u001B[32m add -l <Nombre_Archivo.txt>.\u001B[0m"+" Agrega un lote de procesos que debe ser cargado desde un fichero");
                    System.out.println("\u001B[32m run\u001B[0m"+" Ejecutar procesos");
                    System.out.println("\u001B[32m look -main\u001B[0m"+" Ver Procesos en memoria principal");
                    System.out.println("\u001B[32m look -swap\u001B[0m"+" Ver Procesos en memoria de intercambio");
                    System.out.println("\u001B[32m look -all\u001B[0m"+" Ver todos los procesos");
                    System.out.println("\u001B[32m look -queue\u001B[0m"+" Ver cola de procesos");
                    System.out.println("\u001B[32m look -help\u001B[0m"+" Ver opciones de look");
                    System.out.println("\u001B[32m look \u001B[0m"+" Ver opciones de look");
                    System.out.println("\u001B[32m kill <nombre_proceso>.\u001B[0m"+" Eliminar proceso");
                    System.out.println("\u001B[32m change (-FIFO o -LRU).\u001B[0m"+" Cambiar algoritmo de reubicacion");
                    System.out.println("\u001B[32m exit\u001B[0m"+" Salir");
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                } else {
                    System.out.println("\u001B[32m add <nombre_proceso> <quantum>.\u001B[0m"+" Agregar proceso");
                    System.out.println("\u001B[32m add -l <Nombre_Archivo.txt>.\u001B[0m"+" Agrega un lote de procesos que debe ser cargado desde un fichero");
                    System.out.println("\u001B[32m run\u001B[0m"+" Ejecutar procesos");
                    System.out.println("\u001B[32m look -main\u001B[0m"+" Ver Procesos en memoria principal");
                    System.out.println("\u001B[32m look -swap\u001B[0m"+" Ver Procesos en memoria de intercambio");
                    System.out.println("\u001B[32m look -all\u001B[0m"+" Ver todos los procesos");
                    System.out.println("\u001B[32m look -queue\u001B[0m"+" Ver cola de procesos");
                    System.out.println("\u001B[32m look -help\u001B[0m"+" Ver opciones de look");
                    System.out.println("\u001B[32m look \u001B[0m"+" Ver opciones de look");
                    System.out.println("\u001B[32m kill <nombre_proceso>.\u001B[0m"+" Eliminar proceso");
                    System.out.println("\u001B[32m change (-FIFO o -LRU).\u001B[0m"+" Cambiar algoritmo de reubicacion");
                    System.out.println("\u001B[32m exit\u001B[0m"+" Salir");
                    System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                    System.console().readLine();
                }
            } catch (Exception e) {
                // limpiamos la pantalla
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\n\u001B[34m"+titulo+"\u001B[0m"); 
                // nombre del autor en color verde
                System.out.println("\u001B[32mAutor: \u001B[0m"+"\u001B[01m\u001B[35mDiego Fernandez\u001B[0m");
                // tipo de memoria estara en color rojo
                System.out.println("\u001B[21mTipo de memoria:\u001B[0m \u001B[31m"+tipoMemoria+"\u001B[0m\n");
                System.out.println("\u001B[32m add <nombre_proceso> <quantum>.\u001B[0m"+" Agregar proceso");
                System.out.println("\u001B[32m add -l <Nombre_Archivo.txt>.\u001B[0m"+" Agrega un lote de procesos que debe ser cargado desde un fichero");
                System.out.println("\u001B[32m run\u001B[0m"+" Ejecutar procesos");
                System.out.println("\u001B[32m look -main\u001B[0m"+" Ver Procesos en memoria principal");
                System.out.println("\u001B[32m look -swap\u001B[0m"+" Ver Procesos en memoria de intercambio");
                System.out.println("\u001B[32m look -all\u001B[0m"+" Ver todos los procesos");
                System.out.println("\u001B[32m look -queue\u001B[0m"+" Ver cola de procesos");
                System.out.println("\u001B[32m look -help\u001B[0m"+" Ver opciones de look");
                System.out.println("\u001B[32m look \u001B[0m"+" Ver opciones de look");
                System.out.println("\u001B[32m kill <nombre_proceso>.\u001B[0m"+" Eliminar proceso");
                System.out.println("\u001B[32m change (-FIFO o -LRU).\u001B[0m"+" Cambiar algoritmo de reubicacion");
                System.out.println("\u001B[32m exit\u001B[0m"+" Salir");
                System.out.println("\u001B[31mPresione enter para continuar...\u001B[0m");
                System.out.println(e);
                System.console().readLine();
            }
        }
    }
}
