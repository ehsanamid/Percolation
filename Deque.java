import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    // the pointer to the front of the deque
    private Node first;
    // the pointer to the back of the deque
    private Node last;

    // the number of items in the deque
    private int size;

    // construct an empty deque
    public Deque() {
        // TODO
        first = null;
        last = null;
    }

    private class Node {
        Item item;
        Node next;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /* not supported */
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
        if (isEmpty()) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item)

    // remove and return the item from the front
    public Item removeFirst()

    // remove and return the item from the back
    public Item removeLast()

    // unit testing (required)
    public static void main(String[] args)

}
