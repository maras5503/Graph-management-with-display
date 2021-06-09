import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;
import static java.lang.StrictMath.sqrt;

public class AdjMatrixFrame extends JFrame {
    public AdjMatrixFrame(){
        super("Wprowadzanie grafu przez macierz sąsiedztwa");
        setLocation(700, 200);
        setSize(200, 400);
        setLayout(new FlowLayout());
        JLabel label1=new JLabel("Macierz sąsiedztwa:");

        JTextArea adjMatrixTextArea=new JTextArea();
        adjMatrixTextArea.setPreferredSize(new Dimension(120,120));

        JButton addBtn=new JButton("Wprowadź graf");

        add(label1);
        add(adjMatrixTextArea);
        add(addBtn);

        setVisible(true);

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s=adjMatrixTextArea.getText();
                String sr=s.replaceAll("\\s","");
                char [][] ch=new char[(int) sqrt(sr.length())][(int) sqrt(sr.length())];
                char [] c=sr.toCharArray();
                int k=0;
                for(int i=0;i<sqrt(sr.length());i++){
                    for(int j=0;j<sqrt(sr.length());j++){
                        ch[i][j]=c[k];
                        k++;

                    }
                }
                Graph G=new Graph((int) sqrt(sr.length()));
                for (int i=0;i<sqrt(sr.length());i++){
                    for (int j=0;j<sqrt(sr.length());j++){
                        boolean edgeAdded=false;
                        if(ch[i][j]!='0') {
                            if(G.E()==0){
                                for(int n=0;n<parseInt(String.valueOf(ch[i][j]));n++) {
                                    G.addEdge(new Edge(i, j, 1));
                                }
                            }
                            else {

                                for (Edge ed : G.edges()) {
                                    if (ed.either() == i && ed.other(ed.either()) == j || ed.either() == j && ed.other(ed.either()) == i) {
                                         edgeAdded=true;
                                    }
                                }
                                if(edgeAdded==false) {
                                    for (int n = 0; n < parseInt(String.valueOf(ch[i][j])); n++) {
                                        G.addEdge(new Edge(i, j, 1));
                                    }
                                }

                            }
                        }
                    }
                }
                new GraphFrame(G);
            }
        } );
    }
    public AdjMatrixFrame(Graph G){
        super("Edycja grafu przez macierz sąsiedztwa");
        setLocation(700, 200);
        setSize(200, 400);
        setLayout(new FlowLayout());
        JLabel label1=new JLabel("Macierz sąsiedztwa:");

        JTextArea adjMatrixTextArea=new JTextArea(G.displayAdjMatrix(G.toAdjMatrix()));
        adjMatrixTextArea.setPreferredSize(new Dimension(120,120));

        JButton addBtn=new JButton("Edytuj graf");

        add(label1);
        add(adjMatrixTextArea);
        add(addBtn);

        setVisible(true);

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s=adjMatrixTextArea.getText();
                String sr=s.replaceAll("\\s","");
                char [][] ch=new char[(int) sqrt(sr.length())][(int) sqrt(sr.length())];
                char [] c=sr.toCharArray();
                int k=0;
                for(int i=0;i<sqrt(sr.length());i++){
                    for(int j=0;j<sqrt(sr.length());j++){
                        ch[i][j]=c[k];
                        k++;

                    }
                }
                Graph G=new Graph((int) sqrt(sr.length()));
                for (int i=0;i<sqrt(sr.length());i++){
                    for (int j=0;j<sqrt(sr.length());j++){
                        boolean edgeAdded=false;
                        if(ch[i][j]!='0') {
                            if(G.E()==0){
                                for(int n=0;n<parseInt(String.valueOf(ch[i][j]));n++) {
                                    G.addEdge(new Edge(i, j, 1));
                                }
                            }
                            else {

                                for (Edge ed : G.edges()) {
                                    if (ed.either() == i && ed.other(ed.either()) == j || ed.either() == j && ed.other(ed.either()) == i) {
                                        edgeAdded=true;
                                    }
                                }
                                if(edgeAdded==false) {
                                    for (int n = 0; n < parseInt(String.valueOf(ch[i][j])); n++) {
                                        G.addEdge(new Edge(i, j, 1));
                                    }
                                }

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
