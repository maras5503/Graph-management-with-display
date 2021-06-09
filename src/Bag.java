import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
        private int N;         // Liczba elementów w wielozbiorze.
        private Node first;    // Początek wielozbioru.

        // Klasa pomocnicza z listą powiązaną.
        private class Node {
            private Item item;
            private Node next;
        }

        /**
         * Tworzenie pustego wielozbioru.
         */
        public Bag() {
            first = null;
            N = 0;
        }

        /**
         * Czy wielozbiór jest pusty?
         */
        public boolean isEmpty() {
            return first == null;
        }

        /**
         * Zwracanie liczby elementów w wielozbiorze.
         */
        public int size() {
            return N;
        }

        /**
         * Dodawanie elementu do wielozbioru.
         */
        public void add(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }


        /**
         * Zwracanie iteratora, który przechodzi po elementach wielozbioru.
         */
        public Iterator<Item> iterator()  {
            return new ListIterator();
        }

        // Iterator (nie ma implementacji opcjonalnej operacji remove()).
        private class ListIterator implements Iterator<Item> {
            private Node current = first;

            public boolean hasNext()  { return current != null;                     }
            public void remove()      { throw new UnsupportedOperationException();  }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        }


}
