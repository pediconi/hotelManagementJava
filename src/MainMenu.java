import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private Hotel hotel;
    private JButton aceptarButton;
    private JPanel panel1;
    private JTextArea BIENVENIDOTextArea;
    private JButton salirButton;

    public MainMenu() {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel miHotel = new Hotel();
                new MenuSeleccionUsuario();
                //miHotel.Abrir();


            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cerrando Sistema");
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
