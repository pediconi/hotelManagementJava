import java.time.LocalDate;
import java.util.*;

public class Hotel {


    static Scanner sc = new Scanner(System.in);

    private Archivador<Cliente> archiCliente;
    private Archivador<Usuario> archiUsuario;
    private Archivador<Reserva> archiReserva;
    private Archivador<Pasajero> archiPasajero;
    private Archivador archiHabitacion;
    private Set<Cliente> clientes;
    private Set<Pasajero> pasajeros;
    private Map<Integer, Habitacion> habitaciones;  //(hash) no me interesa tanto el orden de las habitaciones,
    private Set<Reserva> reservas;

    private Usuario usuario;
    private Set<Usuario> usuarios;
    private Display display;
    private int cantPasajeros = 0;


    public Hotel() {

        archiHabitacion = new Archivador();
        archiCliente = new Archivador<Cliente>("clientes");
        archiUsuario = new Archivador<Usuario>("usuarios");
        archiReserva = new Archivador<Reserva>("reservas");
        archiPasajero = new Archivador<Pasajero>("pasajeros");
        clientes = archiCliente.datosArchivoToSet();
        habitaciones = archiHabitacion.cargarHabitaciones();
        usuarios = archiUsuario.datosArchivoToSet();
        reservas = archiReserva.datosArchivoToSet();
        pasajeros = archiPasajero.datosArchivoToSet();
        display = new Display();

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////                  HOTEL                      ////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * metodo para inicializar el sistema, segun el tipo de usuario que esté ingresando al mismo.
     * Segun se ingrese como CONSERJE, ADMINISTRADOR O PASAJERO, derivara al metodo que corresponda
     */
    public void Abrir() {

        boolean encontrado = false;


        //switch (display.menuPrincipal()) {

        //  case 1:

        switch (display.menuSeleccionTipoUsuario()) {

            case 1:
                System.out.println("Ingrese su DNI");
                pasajero(sc.nextInt());
                break;

            case 2:

                String nombre = display.nombreDeUsuario();

                for (Usuario u : usuarios) {

                    if (nombre.equals(u.getNombre())) {

                        encontrado = true;

                        if (u.getTipo().equals(Usuario.tipoUsuario.ADMIN)) {

                            String passsword = display.contrasenia();  // pido la contrasenia

                            if (passsword.equals(u.getClave())) {

                                administrador();
                                backupColectios();

                            } else System.out.println("Contraseña incorrecta.");


                        } else System.out.println("Modo de acceso incorrecto");

                        break;

                    }
                }
                if (!encontrado) System.out.println("Usuario no encontrado");
                break;

            case 3:

                nombre = display.nombreDeUsuario();

                for (Usuario u : usuarios) {

                    if (nombre.equals(u.getNombre())) {

                        encontrado = true;

                        if (u.getTipo().equals(Usuario.tipoUsuario.CONSERJE)) {

                            String passsword = display.contrasenia();  // pido la contrasenia

                            if (passsword.equals(u.getClave())) {

                                conserje();
                                backupColectios();

                            } else System.out.println("Contraseña incorrecta.");


                        } else System.out.println("Modo de acceso incorrecto");

                        break;

                    }
                }
                if (!encontrado) System.out.println("Usuario no encontrado");
                break;

            case 0:
                Abrir();
                break;

        }


        // default:
        //   break;

        //case 0:
        //display.displayMensaje("Cerrando sistema");
        //  break;
    }


    //}


    /**
     * metodo para poder ingresar una fecha, validarla, y retornarla en formato LocalDate()
     *
     * @return un LocalDate correspondiente a la fecha ingresada.
     */
    static LocalDate convertirFechaToLocalDate() {

        int dia, mes, anio;

        do {
            System.out.println("Dia: ");
            dia = sc.nextInt();
        } while (dia < 0 || dia > 31);

        do {
            System.out.println("Mes: ");
            mes = sc.nextInt();
        } while (mes < 0 || mes > 12);

        do {
            System.out.println("Año: ");
            anio = sc.nextInt();
        } while (anio < 2018);

        return LocalDate.of(anio, mes, dia);

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(Set<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Map<Integer, Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Map<Integer, Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////                   CONSERJE                        //////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Permite seleccionar entre todos los metodos que puede utilizar al ingresar como tipoUsiario CONSERJE
     */
    public void conserje() {

        switch (display.getMenuConserje().menuPrincipal()) {

            case 1:
                Reservar();
                conserje();
                break;
            case 2:
                checkIn();
                conserje();
                break;
            case 3:
                checkOut();
                conserje();
                break;

            case 4:
                listarHabitacionesLibres().forEach(System.out::println);
                conserje();
                break;
            case 5:
                listarHabitacionesOcupadas().forEach(System.out::println);
                conserje();
                break;
            case 6:
                listarHabitacionesEnMantenimiento().forEach(System.out::println);
                conserje();
                break;
            case 7:
                pasajeros.forEach(System.out::println);
                conserje();
                break;

            case 8:
                clientes.forEach(System.out::println);
                conserje();

            case 0:
                Abrir();
                break;

            default:
                break;
        }

    }

    /**
     * Busca un cliente en el set de clientes, si no está lo crea.
     *
     * @return retorna al cliente buscado, si lo encontro, o al creado.
     */
    private Cliente identCliente() {
        System.out.println("Datos del cliente ");
        System.out.println("DNI: ");
        int dni = sc.nextInt();
        Cliente cliente = getClientePorDni(dni);
        if (cliente == null) {
            cliente = crearCliente(dni);
        } else System.out.println("Cliente encontrado");
        return cliente;
    }

//////////////////////////////////////////////HABITACION///////////////////////////////////////////////////////////

    /**
     *
     */

    private void agregarHabitacion() {
        System.out.println("Ingrese la capacidad de la Habitación");
        int capacidad = sc.nextInt();
        habitaciones.put(habitaciones.size(), new Habitacion(habitaciones.size(), Habitacion.estadoHabitacion.LIBRE, capacidad));
        display.displayMensaje("La habitacion fue creada satisfactoriamente");
    }

    /**
     * busca y retorna una habitacion libre para un periodo determinado
     *
     * @param inicio          comienzo del periodo de solicitud de la habitacion
     * @param fin             finalizacion del periodo de solicutur de la habitacion
     * @param mapHabitaciones es el mapa que contiene las habitaciones donde se realizara la busqueda
     * @return retorna una habitacion libre para un periodo determinado o null si no encontro ninguna.
     */
    private Habitacion getHabitacionLibrePorFecha(LocalDate inicio, LocalDate fin, Map<Integer, Habitacion> mapHabitaciones) {

        for (Map.Entry<Integer, Habitacion> h : mapHabitaciones.entrySet()) {

            if (checkDisponibilidadHabitacionPorFecha(h.getValue(), inicio, fin)) { // osea si es TRUE ( esta disponible)
                return h.getValue(); // retorno la habitacion
            }

        }
        return null;
    }

    /**
     * busca en un mapa de habitaciones aquellas que tienen una determinada capacidad de ocupantes y las pasa a otro mapa
     *
     * @param cantOcupantes   capacidad de la habitacion
     * @param mapHabitaciones es el mapa que contiene las habitaciones donde se realizara la busqueda
     * @return retorna un mapa con las habitaciones solicitadas por capacidad
     */
    private Map<Integer, Habitacion> getHabitacionesPorCantOcupantes(int cantOcupantes, Map<Integer, Habitacion> mapHabitaciones) {  //devuelvo las habitaciones que tengan el espacio solicitado

        Map<Integer, Habitacion> habitacionesSolicitadas = new HashMap<>();

        for (Map.Entry<Integer, Habitacion> h : mapHabitaciones.entrySet()) {
            if (h.getValue().getCapacidad() == cantOcupantes) {
                habitacionesSolicitadas.put(h.getKey(), h.getValue()); // paso al mapa, "habitacionesSolicitadas", las q cumplan con la condicion If
            }
        }
        return habitacionesSolicitadas;
    }

    /**
     * metodo requerido para el proceso de liberacion de una habitacion.Pide los datos del pasajero y
     * verifica que éste esté registrado en el hotel, y a continuacion procede liberar su habitacion
     *
     * @return true si se encontro al pasajero y por consiguiente se hizo efectivo el checkOut.
     */
    private boolean checkOut() {

        System.out.println("Datos del Pasajero");
        System.out.println("DNI:");
        int dni = sc.nextInt();
        Pasajero pasajero = getPasajeroPorDni(dni);

        if (pasajero != null) {

            Reserva reserva = getReservaPorDni(pasajero.getDni());
            pasajeros.remove(pasajero);
            reservas.remove(reserva);

            assert reserva != null; // siempre se cumple porque pasajero siempre tiene una reserva
            if (getReservasPorIdHabitacion(reserva.getHabitacion().getId()) == null) { // la idea es ver si la habitacion que se libera no tiene mas reservas
                reserva.getHabitacion().setEstado(Habitacion.estadoHabitacion.LIBRE);
            } else {
                reserva.getHabitacion().setEstado(Habitacion.estadoHabitacion.RESERVADA);
            }


            System.out.println("CheckOut realizado");
            System.out.println("Habitacion " + reserva.getHabitacion().getId() + " liberada");
            return true;

        } else System.out.println("Error");

        return false;
    }

    /**
     * comprueba que una habitacion determinada este libre o no tenga reservas para el periodo solicitado por parametro
     *
     * @param h      es la habitacion a comprobar
     * @param inicio comienzo de la fecha del periodo a comprobar
     * @param fin    finalizacion de la fecha del periodo a comprobar
     * @return true si la habitacion se encuentra libre de reservas y ocupacion para el periodo solicitado.
     */
    private boolean checkDisponibilidadHabitacionPorFecha(Habitacion h, LocalDate inicio, LocalDate fin) { // inicio y fin son las fechas en que estan solicitando la habitacion

        boolean reservable = false;

        if ((inicio.isEqual(LocalDate.now()) || inicio.isAfter(LocalDate.now())) && fin.isAfter(LocalDate.now())) {

            if (h.getEstado().equals(Habitacion.estadoHabitacion.LIBRE)) {
                reservable = true;

            } else if (h.getEstado().equals(Habitacion.estadoHabitacion.RESERVADA) || h.getEstado().equals(Habitacion.estadoHabitacion.OCUPADA)) { //significa que tiene reservas o está ocupada

                reservable = true;
                List<Reserva> totalReservasHabitacion = getReservasPorIdHabitacion(h.getId());  // traigo las reservas que tiene la habitacion h , para poder empezar a comparar

                for (Reserva r : totalReservasHabitacion) {  // si la lista esta vacia quiere decir q no tenia reservas, x lo tanto se puede reservar, xq reservable = true;
                    if (!(r.getInicio().isAfter(fin) || r.getFin().isBefore(inicio))) {  // Con esta MAGIA contemplo todos los casos para q no se superpogan las reservas
                        reservable = false;
                        break;
                    }
                }
            }
        }
        return reservable;
    }

    private void Reservar() {
        Cliente cliente = identCliente();
        if (cliente != null) {
            System.out.println("Cantidad de ocupantes?");
            int ocupantes = sc.nextInt();
            System.out.println("Ingrese fecha de inicio y finalizacion de la reserva");
            crearReserva(cliente, convertirFechaToLocalDate(), convertirFechaToLocalDate(), ocupantes);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////RESERVAS////////////////////////////////////////////////////////////

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * retorna true si la reserva pasada por parametro aun esta en vigencia
     *
     * @param r reserva a checkear
     * @return true si la reserva aun esta en vigencia
     */
    private boolean checkFechaDeReserva(Reserva r) {  // compruebo que la reserva sea para el dia de hoy, o aun sigue en vigencia (no haya terminado)
        if (r != null) {

            return r.getInicio().isEqual(LocalDate.now()) || (r.getInicio().isBefore(LocalDate.now()) && r.getFin().isAfter(LocalDate.now())); // retorna true si se cumplen alguna de esas dos condiciones
        }
        return false;
    }

    /**
     * crea una nueva reserva buscando las habitaciones disponibles segun los requerimientos del cliente
     *
     * @param cliente      a realizarle la reserva
     * @param inicio       comienzo del periodo a reservar
     * @param fin          finalizacion del periodo a reservar
     * @param cantPersonas cantidad de personas que ocuparan la habitacion a reservar
     * @return la reserva creada o null si no se pudo crear la reserva
     */
    private Reserva crearReserva(Cliente cliente, LocalDate inicio, LocalDate fin, int cantPersonas) {

        Habitacion habitacion = null;
        Reserva reserva = null;

        Map<Integer, Habitacion> habitacionPosibles = getHabitacionesPorCantOcupantes(cantPersonas, habitaciones); // traigo las habitaciones segun la cant de ocupantes solicitada
        habitacion = getHabitacionLibrePorFecha(inicio, fin, habitacionPosibles);  // le busco una habitacion

        if (habitacion != null) {
            habitacion.setEstado(Habitacion.estadoHabitacion.RESERVADA);
            reserva = new Reserva(habitacion, cliente, inicio, fin, cantPersonas); // creo la reserva

            getReservas().add(reserva);
            System.out.println("Reserva realizada: " + reserva);


            return reserva; // encontro habitacion
        } else System.out.println("No hay habitaciones disponibles.");
        return null; // no encontro habitaicon
    }

    /**
     * busca en reservas las habitaciones que tienen una reserva vigente y por lo tanto se consideran "ocupadas"
     *
     * @return una lista de habitaciones ocupadas
     */
    private List<Habitacion> listarHabitacionesOcupadas() {
        List<Habitacion> ocupadas = new ArrayList<>();

        for (Map.Entry<Integer, Habitacion> h : habitaciones.entrySet()) {

            if (h.getValue().getEstado().equals(Habitacion.estadoHabitacion.OCUPADA)) {
                ocupadas.add(h.getValue()); // agrego la habitacion
            }
        }
        Collections.sort(ocupadas);

        return ocupadas;
    }

    /**
     * busca en habitaciones aquellas que tengan como estadoHabitacion LIBRE, es decir, estan libres
     *
     * @return una lista de habitaciones libres
     */
    private List<Habitacion> listarHabitacionesLibres() {
        List<Habitacion> libres = new LinkedList<>();

        for (Map.Entry<Integer, Habitacion> h : habitaciones.entrySet()) {
            if (h.getValue().getEstado().equals(Habitacion.estadoHabitacion.LIBRE) || h.getValue().getEstado().equals(Habitacion.estadoHabitacion.RESERVADA)) {
                libres.add(h.getValue());
            }
        }
        Collections.sort(libres);

        return libres;
    }

    /**
     * busca en habitaciones aquellas que tengan como estadoHabitacion LIMPIEZA o REPARACION
     *
     * @return una lista de habitaciones en mantenimiento (LIMPIEZA o REPARACION)
     */
    private List<Habitacion> listarHabitacionesEnMantenimiento() {
        List<Habitacion> enMantenimiento = new ArrayList<>();

        for (Map.Entry<Integer, Habitacion> h : habitaciones.entrySet()) {
            if (h.getValue().getEstado().equals(Habitacion.estadoHabitacion.LIMPIEZA) || h.getValue().getEstado().equals(Habitacion.estadoHabitacion.REPARACION)) {
                enMantenimiento.add(h.getValue());
            }
        }
        Collections.sort(enMantenimiento);

        return enMantenimiento;
    }

    /**
     * retorna una lista con las reservas que tiene una misma habitacion
     *
     * @param idHabitacion
     * @return una lista con las reservas que tiene una misma habitacion
     */
    private List<Reserva> getReservasPorIdHabitacion(int idHabitacion) {  // devuelvo las reservas q tenga la habitacion

        List<Reserva> reservasTotales = new ArrayList<>();

        for (Reserva r : reservas) {   // recorro el arreglo de reservas
            if (r.getHabitacion().getId() == idHabitacion) { //guardo las que corresponden a la habitacion "idHabitacion"

                reservasTotales.add(r);
            }
        }
        return reservasTotales;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////CLIENTES/////////////////////////////////////////////////////////////

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public void setCantPasajeros(int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
    }

    /**
     * busca y retorna un cliente buscado en el set de clientes por dni
     *
     * @param dni dni del cliente a buscar
     * @return retorna un cliente buscado en el set de clientes por nombe y apellido o null si no lo encontró.
     */
    private Cliente getClientePorDni(int dni) {

        for (Cliente c : clientes) {
            if (c.getDni() == dni) {
                return c;
            }
        }
        return null;
    }

    /**
     * busca y retorna un pasajero buscado en el set de pasajeros por dni
     *
     * @param dni dni del pasajero a buscar
     * @return retorna un cliente buscado en el set de clientes por nombe y apellido o null si no lo encontró.
     */
    private Pasajero getPasajeroPorDni(int dni) {
        for (Pasajero p : pasajeros) {
            if (p.getDni() == dni) {
                return p;
            }
        }
        return null;
    }

    /**
     * metodo para recibir al cliente al hotel, asignarle la habitacion y agregarlo como pasajero. Busca en primera instancia
     * al cliente en el set de clientes a traves de identCliente una vez obtenido el cliente, verifica si este tiene una reserva vigente.
     * Si la tiene, le asigna la habitacion que le corresponde segun su reserva. Si no tiene reserva le crea una nueva y le asigna la habitacion
     * correspondiente a tal reserva. Finalmente verifica que se haya encontrado una habitacion para el cliente y se procede a la carga final de datos
     * para ingresar al cliente como pasajero registrado en el hotel.
     */
    private void checkIn() {
        LocalDate partida;
        int cantPersonas;
        Cliente cliente = identCliente();
        Habitacion habitacion = null;

        Reserva reserva = getReservaPorDni(cliente.getDni()); // primero veo si el cliente tiene reserva

        if (checkFechaDeReserva(reserva)) {  // SI TIENE RESERVA Y ES PARA EL DIA DE HOY
            System.out.println("RESERVA VERIFICADA");
            habitacion = reserva.getHabitacion(); // le asigno la habitacion que reservo

        } else { //SI NO TIENE RESERVA PARA EL DIA DE LA FECHA...
            getReservas().remove(reserva); //si tenia una reserva la quito xq era erronea,es decir no era para el dia de hoy

            System.out.println("Cantidad de ocupantes?");
            cantPersonas = sc.nextInt();
            System.out.println("Estadia: ");
            System.out.println("Ingrese fecha de partida");
            partida = convertirFechaToLocalDate();
            reserva = crearReserva(cliente, LocalDate.now(), partida, cantPersonas); //CREO una nueva RESERVA que representa el tiempo que va a estar ocupada la habitacion

            if (reserva != null) {
                habitacion = reserva.getHabitacion(); // le asigno la habitacion
            }
        }

        if (habitacion != null) { // si se LE ENCONTRO UNA HABITACION PARA QUEDARSE...
            setCantPasajeros(getCantPasajeros() + 1);
            habitacion.setEstado(Habitacion.estadoHabitacion.OCUPADA);

            sc.nextLine();
            System.out.println("Datos del Pasajero");
            System.out.println("Ciudad: ");
            String ciudad = sc.nextLine();
            System.out.println("Domicilio");
            String domicilio = sc.nextLine();

            pasajeros.add(new Pasajero(cantPasajeros + 1, cliente.getNombre(), cliente.getApellido(), cliente.getDni(), ciudad, domicilio));

            System.out.println("BIENVENIDO/A " + cliente.getNombre() + " " + cliente.getApellido());


        } else System.out.println("NO HAY HABITACIONES DISPONIBLES EN ESTE MOMENTO\n");


    }


    /**
     * busca por dni del cliente una determinada reserva, si existe, y la retorna.
     *
     * @param dni es el dni del cliente por quien se busca la reserva
     * @return retorna la reserva encontrada para ese dni o null si no encontro ninguna.
     */
    private Reserva getReservaPorDni(int dni) {
        for (Reserva r : reservas) {   // recorro el arreglo de reservas
            if (r.getCliente().getDni() == dni) {
                return r; //retorno la reserva
            }
        }
        return null;
    }

    /**
     * retorna al cliente creado si pudo agregarlo al set de clientes o null si éste está duplicado
     *
     * @param dni el dni del cliente a crear
     * @return cliente creado si pudo agregarlo al set de clientes o null si éste está duplicado
     */
    private Cliente crearCliente(int dni) {

        sc.nextLine();
        System.out.println("Nombre:");
        String nombreCliente = sc.nextLine();
        System.out.println("Apellido");
        String apellidoCliente = sc.nextLine();

        Cliente cliente = new Cliente(nombreCliente, apellidoCliente, dni);

        try {
            agregarCliente(cliente);
        } catch (ClienteDuplicadoException e) {
            cliente = null;
            e.printStackTrace();
        }
        return cliente;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////                  ADMINISTRADOR                  ////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Permite seleccionar entre todos los metodos que puede utilizar al ingresar como tipoUsiario ADMINISTRADOR
     */
    public void administrador() {
                /*Administrador: Es el encargado de las funciones administrativas del sistema. Dentro de
                sus funciones está la realización del backup de la información, la creación de otros
                usuarios, la asignación de permisos a usuarios, etc.
                Agregar Habitacion | Gestio Usuario |  Realizar BackUp  */

        switch (display.getMenuAdministrador().menuPrincipal()) {
            case 1:
                agregarHabitacion();
                administrador();
                break;
            case 2:
                gestionarUsuarios();
                administrador();
                break;
            case 3:
                backupColectios();
                administrador();
                break;
            case 0:
                Abrir();
                break;

            default:
                break;
        }

    }


    /**
     * agrega al set el cliente pasado por parametro
     *
     * @param cliente es el cliente a agregar al set
     * @throws ClienteDuplicadoException si el cliente ya existe en el set
     */
    private void agregarCliente(Cliente cliente) throws ClienteDuplicadoException {

        if (!clientes.add(cliente)) {
            throw new ClienteDuplicadoException();
        }
        backupColectios();
    }

    /**
     * metodo para elegir entre las opciones de gestion de usuarios
     */
    private void gestionarUsuarios() {
        switch (display.getMenuAdministrador().menuGestionUsuarios()) {
            case 1:
                agregarUsuario();
                break;
            case 2:
                eliminarUsuario();
                break;
            case 0:
                administrador();
                break;
            default:
                break;
        }
    }

    /**
     * Elimina un usuario del set de usuarios
     */
    private void eliminarUsuario() {
        String usuarioName = display.getMenuAdministrador().displayNombreDeUsuario();
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(usuarioName)) {
                usuarios.remove(u);
                System.out.println("Usuario " + u.getNombre() + " eliminado");
                break;
            }
        }
    }

    /**
     * Agrega un usuario nuevo a la colection
     */
    private void agregarUsuario() {
        String nombre = display.getMenuAdministrador().displayNombreDeUsuario();
        String clave = display.getMenuAdministrador().displayContrasenia();
        String tipo = display.getMenuAdministrador().displayTipo();
        usuarios.add(new Usuario(nombre, clave, Usuario.tipoUsuario.valueOf(tipo)));
        backupColectios();
    }

    /**
     * *Almacena toda la información en HD
     */
    private void backupColectios() {
        archiUsuario.backupColection(usuarios);
        archiCliente.backupColection(clientes);
        archiPasajero.backupColection(pasajeros);
        archiReserva.backupColection(reservas);
        archiHabitacion.backupHabitaciones(habitaciones);

        display.displayMensaje("Informacion almacenada");
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////                  PASAJERO                                ////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Permite seleccionar entre todos los metodos que puede utilizar al ingresar como tipoUsiario ADMINISTRADOR
     */
    public void pasajero(int dni) {


        if (getPasajeroPorDni(dni) != null) {

            switch (display.getMenuPasajero().menuPrincipal()) {
                case 1:
                    System.out.println("En instantes lo atenderemos");
                    //pasajero();
                    break;
                case 2:
                    System.out.println("Limpieza solicitada");
                    //pasajero();

                    break;

                case 0:
                    Abrir();
                    break;

                default:
                    break;
            }


        } else
            System.out.println("Usted no es un pasajero! Farzante!");


    }

}
