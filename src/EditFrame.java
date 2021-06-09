import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFrame extends JFrame {
    public EditFrame(Graph G){
        super("Edytuj");
        setLocation(700, 200);
        setSize(500, 400);
        setLayout(new GridLayout(7,1,10,10));
        JButton formBtn=new JButton("Formularz");
        JButton adjMatrixBtn=new JButton("Macierz sąsiedztwa");
        JButton incMatrixBtn=new JButton("Macierz incydencji");
        JButton adjListBtn=new JButton("Lista sąsiedztwa");
        add(formBtn);
        add(adjMatrixBtn);
        add(incMatrixBtn);
        add(adjListBtn);
        setVisible(true);

        formBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FormFrame(G);
                dispose();
            }
        });

        adjMatrixBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdjMatrixFrame(G);
                dispose();
            }
        });

        incMatrixBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IncMatrixFrame(G);
                dispose();
            }
        });

        adjListBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdjListFrame(G);
                dispose();
            }
        });


    }
}
