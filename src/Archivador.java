import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.io.ObjectOutputStream;

public class Archivador<T> {
    private String nombreClase;

    public Archivador(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public Archivador(){

    }

    /**
     * metodo generico para cargar los datos de un archivo hacia una coleccion tipo set
     * @return el Set correspondiente segun los datos que haya leido.
     */

    public Set<T> datosArchivoToSet() {
        Set<T> colection = new HashSet<T>();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.nombreClase + ".txt"));
            // Se lee el primer objeto
            Object aux = ois.readObject();
            // Mientras haya objetos
            while (aux != null) {
                colection.add((T) aux);  // Se escribe en pantalla el objeto
                aux = ois.readObject();
            }
            ois.close();

        } catch (Exception e) {
            File archi = new File(this.nombreClase + ".txt");
            if (!archi.exists()) {
                crearArchivos();
            }
        }
        return colection;
    }

    /**
     * metodo para guardar la informacion de las colecciones a los archivos correspondientes
     * @param setClase es el set a archivar
     */
    public void backupColection(Set<T> setClase) {
        try {
            FileOutputStream archivo = (new FileOutputStream(this.nombreClase + ".txt"));
            ObjectOutputStream oos = new ObjectOutputStream(archivo);

            for (T u : setClase) {
                oos.writeObject(u);
            }
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * metodo para guardar el map de habitaciones en el archivo
     * @param habitaciones es el mapa de habitaciones a archivar
     */
    public void backupHabitaciones(Map<Integer, Habitacion> habitaciones) {
        try {
            FileOutputStream archivo = (new FileOutputStream("habitaciones.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(archivo);

            for (Map.Entry<Integer, Habitacion> h : habitaciones.entrySet()) {
                oos.writeObject(h.getValue());
            }

            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * metodo para pasar las habitaciones del archivo al map
     * @return el map de habitaciones
     */

    public Map<Integer, Habitacion> cargarHabitaciones() {

        Map<Integer, Habitacion> habitaciones = new HashMap<>();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("habitaciones.txt"));
            // Se lee el primer objeto
            Object aux = ois.readObject();
            // Mientras haya objetos
            while (aux != null) {
                if (aux instanceof Habitacion)
                    habitaciones.put(((Habitacion) aux).getId(), (Habitacion) aux);  // Se escribe en pantalla el objeto
                aux = ois.readObject();
            }
            ois.close();

            //Si se causa un error al leer cae aqui
        } catch (Exception e) {
            File archi = new File("habitaciones.txt");
            if (!archi.exists()) {
                crearArchivos();
            }

        }


        return habitaciones;
    }

    /**
     *  metodo para crear los archivos de habitaciones, clientes y usuarios por primera vez, si estos no existen
     */

    public void crearArchivos() {

        if (!new File("habitaciones.txt").exists()) {

            try {
                Map<Integer, Habitacion> habitaciones = new HashMap<>();
                for (int i = 0; i < 50; i++) {
                    habitaciones.put(i + 1, new Habitacion(i + 1, Habitacion.estadoHabitacion.LIBRE, 3));
                }
                FileOutputStream archivo = (new FileOutputStream("habitaciones.txt", true));
                ObjectOutputStream oos = new ObjectOutputStream(archivo);
                for (Map.Entry<Integer, Habitacion> archivar : habitaciones.entrySet()) {
                    oos.writeObject(archivar.getValue());
                }
                oos.close();
                //cargarHabitaciones();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!new File("clientes.txt").exists()) {

            try {

                Set<Cliente> clientes = cargaInicialDeClientes();
                FileOutputStream archivo = (new FileOutputStream("clientes.txt", true));
                ObjectOutputStream oos = new ObjectOutputStream(archivo);
                for (Cliente cli : clientes) {
                    oos.writeObject(cli);
                }
                oos.close();

                //datosArchivoToSet();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!new File("usuarios.txt").exists()) {

            Set<Usuario> usuarios = new HashSet<>();
            usuarios.add(new Usuario("admin", "admin", Usuario.tipoUsuario.ADMIN));
            usuarios.add(new Usuario("cons", "cons", Usuario.tipoUsuario.CONSERJE));

            try {
                FileOutputStream archivo = (new FileOutputStream("usuarios.txt", true));
                ObjectOutputStream oos = new ObjectOutputStream(archivo);
                for (Usuario u : usuarios) {
                    oos.writeObject(u);
                }
                oos.close();

               // datosArchivoToSet();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * metodo para generar clientes y agregarlos a un set
     * @return set con los clientes cargados.
     */
    private Set<Cliente> cargaInicialDeClientes() {
        Set<Cliente> clientes = new HashSet<Cliente>();
        clientes.add(new Cliente("Pablo", "Collova", 32160386));
        clientes.add(new Cliente("Ivan", "Pediconi", 434343));
        clientes.add(new Cliente("Ezequiel", "Maccimallo", 343415));
        clientes.add(new Cliente("Javier", "hdericoni", 1234534));
        clientes.add(new Cliente("Adrian", "Gimenez", 234523));
        clientes.add(new Cliente("Pedro", "Gomez", 414478));
        clientes.add(new Cliente("Pepe", "Trueno", 973542));
        clientes.add(new Cliente("Gonzalo", "Gonzalez", 5409812));
        clientes.add(new Cliente("Juan", "Rodriguez", 7567234));
        clientes.add(new Cliente("Ignacio", "Ramirez", 983423));
        clientes.add(new Cliente("Tito", "Tato", 4263463));
        clientes.add(new Cliente("Cosme", "Fulanito", 654635));
        clientes.add(new Cliente("Homero", "Simpson", 231345));
        clientes.add(new Cliente("juan", "Perez", 1234));
        clientes.add(new Cliente("carlos", "Martinez", 3236));
        clientes.add(new Cliente("tito", "Gonzales", 3434));
        clientes.add(new Cliente("Pedro", "Gomez", 614478));
        clientes.add(new Cliente("Pepe", "Trueno", 773542));
        clientes.add(new Cliente("Gonzalo", "Gonzalez", 8409812));
        clientes.add(new Cliente("Juan", "Rodriguez", 9567234));
        clientes.add(new Cliente("Ignacio", "Ramirez", 1083423));
        clientes.add(new Cliente("Tito", "Tato", 1163463));
        clientes.add(new Cliente("Cosme", "Fulanito", 124635));
        clientes.add(new Cliente("Homero", "Simpson", 131345));

        return clientes;

    }


}