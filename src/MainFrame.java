import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Grafy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 200);
        setSize(300, 400);

        JPanel buttonPanel = new ButtonPanel();
        add(buttonPanel);




        setVisible(true);

    }


}
