import java.io.Serializable;
import java.time.LocalDate;

public class Habitacion implements Comparable<Habitacion>, Serializable {
    private int id;
    private estadoHabitacion estado; // reservada ocupada libre mantenimiento
    private int capacidad;

    public Habitacion(int id, estadoHabitacion estado, int capacidad) {
        this.id = id;
        this.estado = estado;
        this.capacidad = capacidad;
    }

    public enum estadoHabitacion {
        LIBRE,
        OCUPADA,
        RESERVADA,
        LIMPIEZA,
        REPARACION;
    }

    public int getId() {
        return id;
    }

    public estadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(estadoHabitacion estado) {
        this.estado = estado;
    }


    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habitacion)) return false;

        Habitacion habitacion = (Habitacion) o;
        return this.id == habitacion.id;
    }

    public int hashCode(){
        return Integer.hashCode(id);
    }

    @Override
    public int compareTo(Habitacion o) {

        if (id > o.id) {
            return 1;
        } else if (id < o.id) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "id: " + id + " estado: " + estado + ", capacidad: " + capacidad + ".";
    }
}

