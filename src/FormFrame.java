import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class FormFrame extends JFrame {
    public FormFrame(){
        super("Formularz");
        setLocation(700, 200);
        setSize(145, 400);
        setLayout(new FlowLayout());
        JLabel label1=new JLabel("Ilość krawędzi:");

        TextField edgeTextField=new TextField();



        add(label1);
        add(edgeTextField);

        setVisible(true);
        JButton button= new JButton("Wprowadź graf");
        ArrayList<JLabel> labels=new ArrayList<JLabel>();
        ArrayList<TextField> list1=new ArrayList<TextField>();
        ArrayList<TextField> list2=new ArrayList<TextField>();
        ArrayList<TextField> list3=new ArrayList<TextField>();
        final int[] iter = new int[1];

        edgeTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() == '\n') {
                    for (JLabel s :labels){
                        remove(s);
                    }
                    for (TextField t :list1){
                        remove(t);
                    }
                    for (TextField t :list2){
                        remove(t);
                    }
                    for (TextField t :list3){
                        remove(t);
                    }
                    labels.clear();
                    list1.clear();
                    list2.clear();
                    list3.clear();
                    iter[0] =parseInt(edgeTextField.getText());
                    for(int i=0;i<parseInt(edgeTextField.getText());i++){
                        labels.add(new JLabel(i+":  "));
                        list1.add(new TextField());
                        list2.add(new TextField());
                        list3.add(new TextField());
                        add(labels.get(i));
                        labels.get(i).setLabelFor(list1.get(i));
                        add(list1.get(i));
                        add(list2.get(i));
                        add(list3.get(i));
                        add(button);

                    }
                    setVisible(true);
                } else if (!(ke.getKeyChar()>='0' && ke.getKeyChar()<='9' || ke.getKeyChar()=='\b' || ke.getKeyChar()=='\n')){
                    ke.consume();
                }
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int j=0;
                for (TextField t : list1){
                    if(parseInt(t.getText())>j) j=parseInt(t.getText());
                }
                for (TextField t :list2){
                    if(parseInt(t.getText())>j) j=parseInt(t.getText());
                }
                Graph G=new Graph(j+1);

                for(int i=0;i<iter[0];i++) {
                int v=parseInt(list1.get(i).getText());
                int w=parseInt(list2.get(i).getText());
                int weight=parseInt(list3.get(i).getText());
                G.addEdge(new Edge(v,w,weight));
                }

                new GraphFrame(G);
            }
        } );
    }

    public FormFrame(Graph G){
        super("Formularz");
        setLocation(700, 200);
        setSize(165, 400);
        setLayout(new FlowLayout());
        JButton button1=new JButton("  Dodaj krawędź  ");
        JButton button2=new JButton("  Usuń krawędź  ");



        add(button1);
        add(button2);

        setVisible(true);
        JButton button= new JButton("Wprowadź graf");
        ArrayList<JLabel> labels=new ArrayList<JLabel>();
        ArrayList<TextField> list1=new ArrayList<TextField>();
        ArrayList<TextField> list2=new ArrayList<TextField>();
        ArrayList<TextField> list3=new ArrayList<TextField>();
        final int[] iter = new int[1];
        iter[0]=G.E();
        int i=0;
        for(Edge e : G.edges()){
            labels.add(new JLabel(i + ":  "));
            list1.add(new TextField(String.valueOf(e.either())));
            list2.add(new TextField(String.valueOf(e.other(e.either()))));
            list3.add(new TextField(String.valueOf(e.weight())));
            add(labels.get(i));
            labels.get(i).setLabelFor(list1.get(i));
            add(list1.get(i));
            add(list2.get(i));
            add(list3.get(i));
            add(button);
            i++;
        }
        setVisible(true);



        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int j=0;
                for (TextField t : list1){
                    if(parseInt(t.getText())>j) j=parseInt(t.getText());
                }
                for (TextField t :list2){
                    if(parseInt(t.getText())>j) j=parseInt(t.getText());
                }
                Graph G=new Graph(j+1);

                for(int i=0;i<iter[0];i++) {
                    int v=parseInt(list1.get(i).getText());
                    int w=parseInt(list2.get(i).getText());
                    int weight=parseInt(list3.get(i).getText());
                    G.addEdge(new Edge(v,w,weight));
                }

                new GraphFrame(G);
            }
        } );

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                labels.add(new JLabel(String.valueOf(iter[0])+":      "));
                list1.add(new TextField());
                list2.add(new TextField());
                list3.add(new TextField());
                add(labels.get(iter[0]-1));
                add(list1.get(iter[0]-1));
                add(list2.get(iter[0]-1));
                add(list3.get(iter[0]-1));
                add(button);
                setVisible(true);
                iter[0]++;
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iter[0]--;
                remove(labels.get(iter[0]));
                remove(list1.get(iter[0]));
                remove(list2.get(iter[0]));
                remove(list3.get(iter[0]));
                labels.remove(iter[0]);
                list1.remove(iter[0]);
                list2.remove(iter[0]);
                list3.remove(iter[0]);
                setVisible(true);
            }
        });
    }
}
