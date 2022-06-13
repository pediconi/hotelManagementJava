import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Reserva implements Serializable {
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate inicio;
    private LocalDate fin;
    private int capacidad;
    public static final String NOMBRE="reservas";

    public Reserva(Habitacion habitacion, Cliente cliente, LocalDate inicio, LocalDate fin, int capacidad) {
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.inicio = inicio;
        this.fin = fin;
        this.capacidad = capacidad;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = this.inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;

        Reserva reserva = (Reserva) o;
        return this.habitacion.equals(reserva.habitacion) && this.inicio.isEqual(reserva.inicio) && this.fin.isEqual(reserva.fin);
    }

    public int hashCode(){
        int result = Objects.hashCode(habitacion);
        result = 31 * result + Objects.hashCode(inicio);
        result = 31 * result + Objects.hashCode(fin);

        return result;

    }

    @Override
    public String toString() {
        return " habitacion: " + habitacion + " Nro Pasajeros: " + habitacion.getCapacidad() + ", Cliente: " + cliente.getApellido() +" "+ cliente.getNombre() + ", inicio: " + inicio + ", fin: " + fin + ".";
    }
}

