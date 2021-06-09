import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

import static java.lang.Integer.parseInt;

public class GraphFrame extends JFrame {
    public GraphFrame(Graph G) {
        super("Graf");
        setLocation(700, 200);
        setSize(600, 800);
        setLayout(new FlowLayout());
        JLabel label1 = new JLabel("Lista krawędzi:");
        JLabel label2 = new JLabel("Lista sąsiedztwa:");
        JLabel label3 = new JLabel("Macierz sąsiedztwa:");
        JLabel label4 = new JLabel("Macierz incydencji:");
        JButton editBtn = new JButton("Edytuj");
        JButton saveBtn = new JButton("Zapisz do pliku");
        JButton drawBtn = new JButton("Wyświetl");
        JButton dfsBtn = new JButton("Przeszukiwanie w głąb");
        TextField startDfsTextField = new TextField();
        JLabel criticalNodeLabel = new JLabel("Krawędzie krytyczne");
        JTextArea criticalNodeTextField = new JTextArea();

        TextArea edgeListLabel = new TextArea(G.toEdgeList());
        JTextArea adjListTextField = new JTextArea(G.toAdjList());
        JTextArea adjMatrixTextField = new JTextArea(G.displayAdjMatrix(G.toAdjMatrix()));
        JTextArea incMatrixTextField = new JTextArea(G.displayAdjMatrix(G.toIncMatrix()));
        JTextArea dfsTextField = new JTextArea();

        add(label1);
        add(edgeListLabel);
        add(label2);
        add(adjListTextField);
        add(label3);
        add(adjMatrixTextField);
        add(label4);
        add(incMatrixTextField);
        add(editBtn);
        add(saveBtn);
        add(drawBtn);
        add(criticalNodeLabel);
        add(criticalNodeTextField);
        add(dfsBtn);
        add(startDfsTextField);


        G.bridge();
        String str = "";
        for (String s : G.criticalNodes) {
            str += s + " ";
        }
        if (str == "") {
            criticalNodeTextField.setText("Brak");
        } else {
            criticalNodeTextField.setText(str);
        }
        setVisible(true);


        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Zapisz graf");

                int userSelection = fileChooser.showSaveDialog(getParent());

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        PrintWriter zapis = new PrintWriter(fileToSave.getAbsolutePath());
                        zapis.println(G.displayAdjMatrix(G.toIncMatrix()));
                        zapis.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });

        editBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditFrame(G);
                dispose();
            }
        });



        drawBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog=new JDialog();
                JButton button=new JButton("Dalej");
                JLabel label=new JLabel("Wybiesz sposób kolorowania wierzchołków:");
                JCheckBox checkBox=new JCheckBox("Zaznacz krawędzie krytyczne");
                ButtonGroup buttonGroup=new ButtonGroup();
                JRadioButton j1=new JRadioButton("Według kolejności w macierzy sąsiedztwa                    ");
                JRadioButton j2=new JRadioButton("Według nierosnącej kolejności stopni wierzchołków  ");
                JRadioButton j3=new JRadioButton("Losowo                                                                                    ");
                JRadioButton j4=new JRadioButton("Bez kolorowania                                          ");
                buttonGroup.add(j1);
                buttonGroup.add(j2);
                buttonGroup.add(j3);
                buttonGroup.add(j4);
                dialog.add(label);
                dialog.add(j1);
                dialog.add(j2);
                dialog.add(j3);
                dialog.add(j4);
                dialog.add(checkBox);
                dialog.add(button);
                dialog.setLayout(new FlowLayout());
                dialog.setSize(340,220);
                dialog.setVisible(true);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        G.criticalNodes.clear();
                        if (checkBox.isSelected()){
                            G.bridge();
                        }

                        if (j1.isSelected()) {

                            G.greedyColoring();
                            new VisualisationFrame(G.toIncMatrix(),G.criticalNodes,G.colours);
                        }

                        else if (j2.isSelected()) {

                            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                            LinkedList<Integer>[]list2=new LinkedList[G.V()];
                            int i = 0;
                            for (LinkedList<Integer> list : G.adj2) {

                                map.put(i, list.size());
                                i++;
                            }
                            Map<Integer, Integer> map2 = sortByValues((HashMap) map);
                            ArrayList<Integer> list3=new ArrayList<>();
                            Iterator it = map2.entrySet().iterator();
                            i=0;
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
                                list2[i]=G.adj2[parseInt(pair.getKey().toString())];
                                list3.add(parseInt(pair.getKey().toString()));
                                it.remove();
                                i++;
                            }
                            G.greedyColoring(list3);
                            new VisualisationFrame(G.toIncMatrix(), G.criticalNodes, G.colours);
                        }
                        else if (j3.isSelected()){

                            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                            LinkedList<Integer>[]list2=new LinkedList[G.V()];
                            int i = 0;
                            for (LinkedList<Integer> list : G.adj2) {

                                map.put(i, list.size());
                                i++;
                            }
                            Map<Integer, Integer> map2 = sortByValues((HashMap) map);
                            ArrayList<Integer> list3=new ArrayList<>();
                            Iterator it = map2.entrySet().iterator();
                            i=0;
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
                                list2[i]=G.adj2[parseInt(pair.getKey().toString())];
                                list3.add(parseInt(pair.getKey().toString()));
                                it.remove();
                                i++;
                            }
                            Collections.shuffle(list3);
                            G.greedyColoring(list3,0);
                            new VisualisationFrame(G.toIncMatrix(), G.criticalNodes, G.colours);
                        }
                        else if(j4.isSelected()){
                            for (int i=0;i<G.V();i++){
                                G.colours.add(0);
                            }
                            new VisualisationFrame(G.toIncMatrix(),G.criticalNodes,G.colours);
                        }
                    }
                });
            }
        });

        dfsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = Integer.parseInt(startDfsTextField.getText());
                G.DFS(i);
                dfsTextField.setText(G.dfs);
                add(dfsTextField);
            }
        });

    }
    public static HashMap sortByValues (HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        Collections.reverse(list);
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
