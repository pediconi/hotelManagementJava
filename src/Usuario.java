import java.io.Serializable;

public class Usuario implements Serializable {
    public static final String NOMBRE = "usuarios";

    public enum tipoUsuario {
        ADMIN,
        CONSERJE,
        PASAJERO;

    }

    private String nombre;
    private String clave;
    private tipoUsuario tipo;

    public Usuario(String nombre, String clave, tipoUsuario tipo) {
        this.nombre = nombre;
        this.clave = clave;
        this.tipo = tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public tipoUsuario getTipo() {
        return tipo;
    }

    public String getClave() {
        return clave;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", clave='" + clave + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
