import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.StrictMath.sqrt;

public class AdjListFrame extends JFrame {
    public AdjListFrame(){
        super("Wprowadzanie grafu przez listę sąsiedztwa");
        setLocation(700, 200);
        setSize(300, 400);
        setLayout(new FlowLayout());
        JLabel label1=new JLabel("Ilość wierzchołków:");

        TextField vertexTextField=new TextField();

        add(label1);
        add(vertexTextField);
        setVisible(true);
        final int[] iter = new int[1];
        JButton button1=new JButton("Wprowadź dane");
        add(button1);
        JButton button= new JButton("Wprowadź graf");
        ArrayList <TextField> list=new ArrayList<TextField>();
        ArrayList <JLabel> labels=new ArrayList<JLabel>();

        vertexTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (!(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar()=='\b')) {

                        ke.consume();
                    }
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JLabel s :labels){
                    remove(s);
                }
                for (TextField t :list){
                    remove(t);
                }
                labels.clear();
                list.clear();
                vertexTextField.setEditable(true);
                iter[0] = parseInt(vertexTextField.getText());
                for (int i = 0; i < parseInt(vertexTextField.getText()); i++) {
                    labels.add(new JLabel(i + ":"));
                    list.add(new TextField());
                    add(labels.get(i));
                    add(list.get(i));
                    add(button);
                }
                setVisible(true);
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Graph G=new Graph(iter[0]);
                for(int i=0;i<iter[0];i++) {
                    String s = list.get(i).getText();
                    s = s.replaceAll(",", "");
                    char[] c = s.toCharArray();

                    for (int j = 0; j < c.length; j++) {
                        boolean edgeAdded=false;

                        if (G.E() == 0) {

                            G.addEdge(new Edge(i, parseInt(String.valueOf(c[j])), 1));
                        }
                        else {

                            for (Edge ed : G.edges()) {
                                if (ed.either() == i && ed.other(ed.either()) == parseInt(String.valueOf(c[j])) || ed.either() == parseInt(String.valueOf(c[j])) && ed.other(ed.either()) == i) {
                                    edgeAdded = true;
                                }
                            }
                            if (edgeAdded == false) {

                                    G.addEdge(new Edge(i, parseInt(String.valueOf(c[j])), 1));

                            }
                        }
                    }
                }

                new GraphFrame(G);
            }
        } );
    }

    public AdjListFrame(Graph G){
        super("Edytowanie przez listę sąsiedztwa");
        setLocation(700, 200);
        setSize(300, 400);
        setLayout(new FlowLayout());
        JButton button1=new JButton("Dodaj wierzchołek");
        JButton button2=new JButton("Usuń wierzchołek");
        add(button1);
        add(button2);
        setVisible(true);
        final int[] iter = new int[1];
        iter[0]=G.V();
        JButton button= new JButton("Wprowadź graf");
        ArrayList <TextField> list=new ArrayList<TextField>();
        ArrayList <JLabel> labels=new ArrayList<JLabel>();

        String s=G.toAdjList();
        s=s.replaceAll("\r","");
        String [] st=s.split("\n");
        for (int i=0;i<st.length;i++) st[i]=st[i].substring(2);

        for (int i = 0; i < G.V(); i++) {
            labels.add(new JLabel(i + ":"));
            list.add(new TextField(st[i]));
            add(labels.get(i));
            add(list.get(i));
        }
        add(button);


        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { ;
                labels.add(new JLabel(String.valueOf(iter[0])+":"));
                list.add(new TextField());
                add(labels.get(iter[0]-1));
                add(list.get(iter[0]-1));
                add(button);
                setVisible(true);
                iter[0]++;
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iter[0]--;
                remove(labels.get(iter[0]));
                remove(list.get(iter[0]));
                labels.remove(iter[0]);
                list.remove(iter[0]);
                setVisible(true);
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Graph G=new Graph(iter[0]);
                for(int i=0;i<iter[0];i++) {
                    String s = list.get(i).getText();
                    s = s.replaceAll(",", "");
                    s = s.replaceAll(" ","");
                    char[] c = s.toCharArray();

                    for (int j = 0; j < c.length; j++) {
                        boolean edgeAdded=false;

                        if (G.E() == 0) {

                            G.addEdge(new Edge(i, parseInt(String.valueOf(c[j])), 1));
                        }
                        else {

                            for (Edge ed : G.edges()) {
                                if (ed.either() == i && ed.other(ed.either()) == parseInt(String.valueOf(c[j])) || ed.either() == parseInt(String.valueOf(c[j])) && ed.other(ed.either()) == i) {
                                    edgeAdded = true;
                                }
                            }
                            if (edgeAdded == false) {

                                G.addEdge(new Edge(i, parseInt(String.valueOf(c[j])), 1));

                            }
                        }
                    }
                }

                new GraphFrame(G);
                dispose();
            }
        } );
    }
}
