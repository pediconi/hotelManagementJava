import java.util.Scanner;

public class Display  {

    private MenuConserje menuConserje;
    private MenuAdministrador menuAdministrador;
    private MenuPasajero menuPasajero;
    private Scanner sc = new Scanner(System.in);

    public Display() {
        this.menuConserje = new MenuConserje();
        this.menuAdministrador = new MenuAdministrador();
        this.menuPasajero = new MenuPasajero();
    }

    public MenuConserje getMenuConserje() {
        return menuConserje;
    }

    public MenuAdministrador getMenuAdministrador() {
        return menuAdministrador;
    }

    public MenuPasajero getMenuPasajero() {
        return menuPasajero;
    }

    public int menuPrincipal(){
        System.out.println(" ______________________________________________________________________________________________________________________");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                          BIENVENIDO AL GRAN HOTEL HOTELITO                                           |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                 1 - Ingresar                                                         |");
        System.out.println("|                                                 0 - Salir                                                            |");
        System.out.println("|                                                                                                                      |");
        System.out.println(" _____________________________________________________________________________________________________________________");
        return sc.nextInt();
    }

    public int menuSeleccionTipoUsuario(){
        System.out.println(" ______________________________________________________________________________________________________________________");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                 1 - Modo Pasajero                                                    |");
        System.out.println("|                                                 2 - Modo Administrador                                               |");
        System.out.println("|                                                 3 - Modo Conserje                                                    |");
        System.out.println("|                                                 0 - Menu Principal                                                   |");
        System.out.println("|                                                                                                                      |");
        System.out.println(" _____________________________________________________________________________________________________________________");
        return sc.nextInt();
    }


    public String nombreDeUsuario(){
        sc.nextLine();
        System.out.println(" ____________________________");
        System.out.println("|                            |");
        System.out.println("| Ingrese nombre de usuario: |");
        System.out.println("|                            |");
        System.out.println(" ____________________________");
        return sc.nextLine();
    }

    public String contrasenia(){
        System.out.println(" ____________________________");
        System.out.println("|                            |");
        System.out.println("|        Contraseña:         |");
        System.out.println("|                            |");
        System.out.println(" ____________________________");
        return sc.nextLine();
    }

    public void usuarioNoEncontrado() {
        System.out.println("Usuario no Encontrado");
    }
    public void displayMensaje(String msg) {
        System.out.println("=====================================================");
        System.out.println(msg);
        System.out.println("_____________________________________________________");
        System.out.println("=====================================================");
        sc.nextLine();
    }
}


