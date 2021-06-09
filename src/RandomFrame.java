import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.Integer.parseInt;

public class RandomFrame extends JFrame {
    public RandomFrame() {
        super("Formularz");
        setLocation(700, 200);
        setSize(200, 400);
        setLayout(new FlowLayout());
        JLabel labelV=new JLabel("Ilość wierzchołków:");
        TextField tfV=new TextField();
        JLabel labelF=new JLabel("Ilość krawędzi:");
        TextField tfF=new TextField();
        JButton button=new JButton("Generuj graf");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Graph G=new Graph(parseInt(tfV.getText()),parseInt(tfF.getText()),1);
                new GraphFrame(G);
                dispose();
            }
        } );

        tfV.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = tfV.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    tfV.setEditable(true);
                } else {
                    tfV.setEditable(false);
                }
            }
        });

        tfF.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = tfF.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    tfF.setEditable(true);
                } else {
                    tfF.setEditable(false);
                }
            }
        });

        add(labelV);
        add(tfV);
        add(labelF);
        add(tfF);
        add(button);
        setVisible(true);

    }
}
