public class Edge {

        private final int v;
        private final int w;
        private final double weight;

        /**
         * Tworzenie krawędzi o danej wadze między v a w.
         */
        public Edge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        /**
         * Zwracanie wagi krawędzi.
         */
        public double weight() {
            return weight;
        }

        /**
         * Zwracanie jednego z punktów końcowych krawędzi.
         */
        public int either() {
            return v;
        }

        /**
         * Zwracanie punktu końcowego krawędzi innego niż podany wierzchołek
         * (chyba że krawędź tworzy pętlę zwrotną).
         */
        public int other(int vertex) {
            if      (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new RuntimeException("Niedozwolony punkt koncowy");
        }

        /**
         * Porównywanie krawędzi na podstawie wag.
         */
        public int compareTo(Edge that) {
            if      (this.weight() < that.weight()) return -1;
            else if (this.weight() > that.weight()) return +1;
            else                                    return  0;
        }

        /**
         * Zwracanie łańcucha znaków reprezentującego krawędź.
         */
        public String toString() {
            return String.format("%d-%d %.2f", v, w, weight);
        }

}
