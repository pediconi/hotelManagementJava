import java.util.Scanner;

public class MenuPasajero {

    private Scanner sc = new Scanner(System.in);

    /**
     * menu que muestra todas las opciones que puede elegir un pasajero
     * @return el valor que se ingreso por pantalla
     */
    public int menuPrincipal(){
        System.out.println(" ___________________________________________");
        System.out.println("| Elija una opcion:                         |");
        System.out.println("| 1 - Servicio Habitacion                   |");
        System.out.println("| 2 - Solicitar limpieza                    |");
        System.out.println("| 0 - Volver al menu principal              |");
        System.out.println(" ___________________________________________");


        return sc.nextInt();
    }

}
