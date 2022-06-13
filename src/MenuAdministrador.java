import java.util.Scanner;
import java.time.LocalDate;

import static java.lang.System.out;

public class MenuAdministrador {

    private Scanner sc = new Scanner(System.in);

    /**
     * menu que muestra todas las opciones que puede elegir un administrador
     * @return el valor que se ingreso por pantalla
     */
    public int menuPrincipal() {
        /*Agregar Habitacion | Gestio Usuario |  Realizar BackUp*/
        out.println(" ==============================");
        out.println("| Elija una opcion:            |");
        out.println("| 1 - Agregar Habitacion       |");
        out.println("| 2 - Gestionar Usuarios       |");
        out.println("| 3 - Realizar BackUp          |");
        out.println("| 0 - Volver al menu principal |");
        out.println("|______________________________|");

        return sc.nextInt();
    }

    /**
     * menu que muestra todas las opciones de menuGestionUsuarios
     * @return el valor que se ingreso por pantalla
     */

    public int menuGestionUsuarios() {
        out.println(" =============================");
        out.println("| 1 - Agregar Usuario         |");
        out.println("| 2 - Eliminar Usuario        |");
        out.println("| 0 - Volver                  |");
        out.println("|_____________________________|");

        return sc.nextInt();
    }

    /**
     * metodo para ingresar un nombre de usuario
     * @return el String con el nombre de usuario que se ingreso
     */
    public String displayNombreDeUsuario() {
        sc.nextLine();
        System.out.println("Nombre de usuario");
        return sc.nextLine();
    }

    /**
     * metodo para ingresar una contrasenia
     * @return el String con la contrasenia que se ingreso
     */
    public String displayContrasenia() {
        System.out.println("Contraseña");
        return sc.nextLine();
    }

    /**
     * menu para poder seleccionar entre un tipo de usuario y asignarlo al Enum correspondiente
     * @return el enum correspondiente convertido en string
     */

    public String displayTipo() {

        out.println(" Ingrese tipo de usuario ");
        out.println(" 1 - Administrador       ");
        out.println(" 2 - Conserje            ");
        out.println(" 3 - Pasajero            ");


        String rta = "";
        switch (sc.nextInt()) {
            case 1:
                rta = Usuario.tipoUsuario.ADMIN.toString();
                break;
            case 2:
                rta = Usuario.tipoUsuario.CONSERJE.toString();
                break;
            case 3:
                rta = Usuario.tipoUsuario.PASAJERO.toString();
                break;
            default:
                break;

        }
        return rta;
    }
}


