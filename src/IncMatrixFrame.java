import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;
import static java.lang.StrictMath.sqrt;

public class IncMatrixFrame extends JFrame {
    public IncMatrixFrame(){
        super("Wprowadzanie grafu przez macierz incydencji");
        setLocation(700, 200);
        setSize(200, 400);
        setLayout(new FlowLayout());
        JLabel label1=new JLabel("Macierz incydencji:");

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
                dispose();
            }
        } );
    }
    public IncMatrixFrame(Graph G){
        super("Edycja grafu przez macierz incydencji");
        setLocation(700, 200);
        setSize(200, 400);
        setLayout(new FlowLayout());
        JLabel label1=new JLabel("Macierz incydencji:");

        JTextArea adjMatrixTextArea=new JTextArea(G.displayAdjMatrix(G.toIncMatrix()));
        adjMatrixTextArea.setPreferredSize(new Dimension(120,120));

        JButton addBtn=new JButton("Edytuj graf");

        add(label1);
        add(adjMatrixTextArea);
        add(addBtn);

        setVisible(true);

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s=adjMatrixTextArea.getText();
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
                dispose();
            }
        } );
    }
}
