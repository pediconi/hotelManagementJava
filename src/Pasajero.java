public class Pasajero extends Cliente {
    private int id;
    private String ciudad;
    private String domicilio;
    public static final String NOMBRE="pasajeros";

    public Pasajero(int id, String nombre, String apellido, int dni, String ciudad, String domicilio) {
        super(nombre, apellido, dni);
        this.id = id;
        this.ciudad = ciudad;
        this.domicilio = domicilio;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pasajero)) return false;
        Pasajero pasajero = (Pasajero) o;
        return super.equals(o) && id == pasajero.id;
    }

    public int hashCode() {

        int result = super.hashCode();
        return 31 * result + Integer.hashCode(id);
    }


    @Override
    public String toString() {
        return super.toString() +
                "id: " + id + ", ciudad: " + ciudad + ", domicilio: " + domicilio + ".";
    }
}
