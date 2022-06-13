import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);

    public int menuConserje(){

        System.out.println("ELIJA UNA OPCION");
        System.out.println("1 - Reservar");
        System.out.println("2 - Check In");
        System.out.println("3 - Check out");

        return sc.nextInt();
    }


}
