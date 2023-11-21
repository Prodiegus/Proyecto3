package src;

public class Proceso {
    String nombre;
    int quantum;

    public Proceso(String nombre, int quantum) {
        this.nombre = nombre;
        this.quantum = quantum;
    }

    @Override
    public String toString() {
        return "[Proceso:" + nombre + "| Quantum:" + quantum + "]";
    }
}