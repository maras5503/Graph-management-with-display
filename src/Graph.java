import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.*;
import java.io.*;


public class Graph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    LinkedList<Integer> adj2[];
    String dfs = "";
    static final int NIL = -1;
    int time = 0;
    ArrayList<Integer> colours=new ArrayList<>();
    ArrayList<String> criticalNodes=new ArrayList<>();

    /**
     * Tworzenie pustego grafu ważonego o V wierzchołkach.
     */
    public Graph(int V) {
        if (V < 0) throw new RuntimeException("Liczba wierzcholkow musi byc nieujemna");
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        adj2 = new LinkedList[V];
        for (int i = 0; i < V; ++i) {
            adj2[i] = new LinkedList();
        }
        for (int v = 0; v < V; v++) adj[v] = new Bag<Edge>();
    }

    /**
     * Tworzenie losowego grafu ważonego o V wierzchołkach i E krawędziach.
     * Oczekiwany czas wykonania jest proporcjonalny do V + E.
     */
    public Graph(int V, int E, int weight) {
        this(V);
        if (E < 0) throw new RuntimeException("Liczba krawedzi musi byc nieujemna");
        adj2 = new LinkedList[V];
        for (int i = 0; i < V; ++i) {
            adj2[i] = new LinkedList();
        }
        for (int i = 0; i < E; i++) {
            int v = i;
            int w = i;
            while (v == w) {
                v = (int) (Math.random() * V);
                w = (int) (Math.random() * V);
            }
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }


    /**
     * Zwraca liczbę wierzchołków w grafie.
     */
    public int V() {
        return V;
    }

    /**
     * Zwracanie liczby krawędzi w grafie.
     */
    public int E() {
        return E;
    }


    /**
     * Dodawanie krawędzi e do grafu.
     */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        adj2[v].add(w);
        adj2[w].add(v);
        E++;
    }


    /**
     * Zwracanie krawędzi incydentnych do wierzchołka v jako obiektu Iterable.
     * Aby przejść po krawędziach incydentnych do wierzchołka v należy użyć zapisu foreach:
     * <tt>for (Edge e : graph.adj(v))</tt>.
     */
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * Zwracanie wszystkich krawędzi grafu jako obiektu Iterable.
     * Aby przejść po krawędziach, należy użyć zapisu foreach:
     * <tt>for (Edge e : graph.edges())</tt>.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // Dodawanie tylko jednej kopii każdej pętli zwrotnej
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }


    /**
     * Zwracanie łańcucha znaków reprezentującego dany graf
     */
    public String toEdgeList() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public String toAdjList() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            s.append(i + ": ");
            for (Edge e : adj[i]) {
                l.add(e.other(i));
            }
            Collections.sort(l);
            for (Integer j : l) {
                s.append(j + " ");
            }
            s.append(NEWLINE);
            l.clear();
        }
        return s.toString();
    }

    public int[][] toAdjMatrix() {
        int[][] matrix = new int[][]{};
        int N = V;
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                matrix[i][j] = 0;
        }

        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                matrix[v][e.other(v)] += 1;
            }
        }
        return matrix;

    }

    public String displayAdjMatrix(int[][] matrix) { // To display the adjacency matrix
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                s.append(matrix[i][j] + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    public int[][] toIncMatrix() {
        int[][] matrix = new int[][]{};
        matrix = new int[V][E];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < E; j++)
                matrix[i][j] = 0;
        }

        int i = 0;
        for (Edge ed : this.edges()) {
            matrix[ed.either()][i] = 1;
            matrix[ed.other(ed.either())][i] = 1;
            i++;
        }


        return matrix;

    }

    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        dfs += v + " ";

        Iterator<Integer> i = adj2[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v) {
        dfs = "";
        boolean visited[] = new boolean[V];
        DFSUtil(v, visited);
    }

    void bridgeUtil(int u, boolean visited[], int disc[],
                    int low[], int parent[])
    {

        visited[u] = true;

        disc[u] = low[u] = ++time;

        Iterator<Integer> i = adj2[u].iterator();
        while (i.hasNext())
        {
            int v = i.next();


            if (!visited[v])
            {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent);

                low[u]  = Math.min(low[u], low[v]);

                if (low[v] > disc[u])
                    criticalNodes.add(u+"-"+v);
            }

            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }



    void bridge()
    {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];


        for (int i = 0; i < V; i++)
        {
            parent[i] = NIL;
            visited[i] = false;
        }

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                bridgeUtil(i, visited, disc, low, parent);
    }



    void greedyColoring()
    {
        colours.clear();
        int result[] = new int[V];

        Arrays.fill(result, -1);
        result[0]  = 0;
        boolean available[] = new boolean[V];
        Arrays.fill(available, true);

        for (int u = 1; u < V; u++)
        {
            Iterator<Integer> it = adj2[u].iterator() ;
            while (it.hasNext())
            {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = false;
            }

            int cr;
            for (cr = 0; cr < V; cr++){
                if (available[cr])
                    break;
            }

            result[u] = cr;

            Arrays.fill(available, true);
        }

        for (int u = 0; u < V; u++)
            colours.add(result[u]);
    }

    void greedyColoring(ArrayList<Integer> list2)
    {
        colours.clear();
        int result[] = new int[V];

        Arrays.fill(result, -1);
        result[list2.get(0)]  = 0;
        boolean available[] = new boolean[V];
        Arrays.fill(available, true);

        for (int u = 1; u < V; u++)
        {
            int j=list2.get(u);
            Iterator<Integer> it = adj2[j].iterator() ;
            while (it.hasNext())
            {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = false;
            }

            int cr;
            for (cr = 0; cr < V; cr++){
                if (available[cr])
                    break;
            }

            result[j] = cr;

            Arrays.fill(available, true);
        }

        for (int u = 0; u < V; u++)
            colours.add(result[u]);
    }
    void greedyColoring(ArrayList<Integer> list2,int overload)
    {
        colours.clear();
        int result[] = new int[V];

        Arrays.fill(result, -1);
        result[list2.get(0)]  = 0;
        boolean available[] = new boolean[V];
        Arrays.fill(available, true);

        for (int u = 1; u < V; u++)
        {
            int j=list2.get(u);
            Iterator<Integer> it = adj2[j].iterator() ;
            while (it.hasNext())
            {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = false;
            }

            int cr;
            for (cr = 0; cr < V; cr++){
                if (available[cr])
                    break;
            }

            result[j] = cr;

            Arrays.fill(available, true);
        }

        for (int u = 0; u < V; u++)
            colours.add(result[u]);
    }
}



