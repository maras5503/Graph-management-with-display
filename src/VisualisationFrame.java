import javax.swing.*;
import org.graphstream.graph.*;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swingViewer.Viewer;

import java.util.ArrayList;

public class VisualisationFrame extends JFrame {
    public VisualisationFrame(int [][] matrix, ArrayList<String> criticalNodes, ArrayList<Integer> colours){
        Graph graph=new SingleGraph("Graph");
        Viewer viewer=graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        for (int i=0;i<matrix.length;i++){
            Node n=graph.addNode(String.valueOf(i));
            graph.getNode(String.valueOf(i)).setAttribute("ui.label",String.valueOf(i));
            int colour=colours.get(i);

            if(colour==0) {
                n.setAttribute("ui.style", "fill-color: red;");
            }
            else if(colour==1){
                n.setAttribute("ui.style", "fill-color: blue;");
            }
            else if(colour==2){
                n.setAttribute("ui.style", "fill-color: yellow;");
            }
            else if(colour==3){
                n.setAttribute("ui.style", "fill-color: green;");
            }
            else if(colour==4){
                n.setAttribute("ui.style", "fill-color: orange;");
            }
            else if(colour==5){
                n.setAttribute("ui.style", "fill-color: purple;");
            }
        }
        for (int i=0;i<matrix[0].length;i++){
            Integer v=null,w=null;
            for (int j=0;j<matrix.length;j++){
                if(matrix[j][i]==1){
                    if(v==null) {
                        v = j;
                    }
                    if(v!=null){
                        w=j;
                    }
                }

            }
            if(v!=null && w!=null) {
                Edge e=graph.addEdge(String.valueOf(v) + String.valueOf(w), String.valueOf(v), String.valueOf(w));
                for (String s : criticalNodes) {
                    s=s.replaceAll("-","");
                    String s2=String.valueOf(e.getId());
                    if (s.equals(s2)) {
                        e.addAttribute("ui.style", "fill-color: red;");
                    }
                }
            }
        }


    }
}
