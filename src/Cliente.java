import java.io.Serializable;

public class Cliente implements Serializable {
    protected String nombre;
    protected String apellido;
    protected int dni;
    public static final String NOMBRE = "clientes";

    public Cliente(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return dni == cliente.dni;   // declaro igualdad solo x coincidencia de dni
    }

    public int hashCode() {
        return Integer.hashCode(dni);
    }

    @Override
    public String toString() {
        return
                "Nombre: " + nombre + ", Apellido: " + apellido + ", Dni: " + dni + ".";

    }
}

