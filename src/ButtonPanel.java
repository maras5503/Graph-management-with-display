import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener{

    public static final int HEIGHT = 100;
    public static final int WIDTH = 300;
    private JButton formBtn;
    private JButton randomBtn;
    private JButton adjMatrixBtn;
    private JButton incMatrixBtn;
    private JButton adjListBtn;
    private JButton loadGrapthBtn;

    public ButtonPanel() {
        formBtn = new JButton("Formularz");
        randomBtn=new JButton("Losowy graf");
        adjMatrixBtn = new JButton("Macierz sąsiedztwa");
        incMatrixBtn = new JButton("Macierz incydencjii");
        adjListBtn = new JButton("Lista sąsiedztwa");
        loadGrapthBtn = new JButton("Wczytaj");

        formBtn.addActionListener(this);
        randomBtn.addActionListener(this);
        adjMatrixBtn.addActionListener(this);
        incMatrixBtn.addActionListener(this);
        adjListBtn.addActionListener(this);
        loadGrapthBtn.addActionListener(this);

        setLayout(new GridLayout(7,1,10,10));
        formBtn.setSize(150,30);
        adjMatrixBtn.setSize(150,30);
        incMatrixBtn.setSize(150,30);
        adjListBtn.setSize(150,30);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(new JLabel("           Wybierz sposób wprowadzenia grafu"));

        add(formBtn);
        add(randomBtn);
        add(adjMatrixBtn);
        add(incMatrixBtn);
        add(adjListBtn);
        add(loadGrapthBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == formBtn){
            new FormFrame();
        }

        else if(source == randomBtn){
            new RandomFrame();
        }

        else if(source == adjMatrixBtn) {
            new AdjMatrixFrame();
        }

        else if(source == incMatrixBtn){
            new IncMatrixFrame();
        }

        else if(source == adjListBtn){
            new AdjListFrame();
        }

        else if(source == loadGrapthBtn){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Wczytaj graf");

            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    Scanner odczyt=new Scanner(file);
                    String s=new String();
                    while (odczyt.hasNextLine()){
                        s+=odczyt.nextLine()+"\n";
                    }
                    String [] st=s.split("\n");
                    st[0]=st[0].replaceAll("\\s","");
                    char [][] ch=new char[st.length][st[0].length()];
                    for (int i=0;i<st.length;i++){
                        String sr=st[i].replaceAll("\\s","");
                        char[] c=sr.toCharArray();
                        for(int j=0;j<c.length;j++){
                            ch[i][j]=c[j];
                        }

                    }

                    Graph G=new Graph(st.length);
                    for (int i=0;i<ch[0].length;i++){
                        Integer v=null,w=null;
                        for (int j=0;j<ch.length;j++){
                            if(ch[j][i]=='1'){
                                if(v==null) {
                                    v = j;
                                }
                                if(v!=null){
                                    w=j;
                                }
                            }

                        }
                        if(v!=null && w!=null)
                            G.addEdge(new Edge(v,w,1));
                    }

                    new GraphFrame(G);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        }


    }
}
