import java.time.LocalDate;
import java.util.Scanner;

public class MenuConserje {

    private Scanner sc = new Scanner(System.in);

    public int menuPrincipal() {
        System.out.println(" ___________________________________________");
        System.out.println("| Elija una opcion:                         |");
        System.out.println("| 1 - Reservar                              |");
        System.out.println("| 2 - Check in                              |");
        System.out.println("| 3 - check Out                             |");
        System.out.println("| 4 - Listar habitaciones libres            |");
        System.out.println("| 5 - Listar habitaciones ocupadas          |");
        System.out.println("| 6 - Listar habitaciones en mantenimiento  |");
        System.out.println("| 7 - Listar pasajeros                      |");
        System.out.println("| 8 - Listar clientes                       |");
        System.out.println("| 0 - Volver al menu principal              |");
        System.out.println("|___________________________________________|");

        return sc.nextInt();
    }


}
