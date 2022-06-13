import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSeleccionUsuario {
    private JButton pasajeroButton;
    private JButton administradorButton;
    private JButton conserjeButton;
    private JButton volverButton;

    public MenuSeleccionUsuario() {
        pasajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pasajero seleccionado");
            }
        });
        administradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Administrador seleccionado");
            }
        });
    }
}
