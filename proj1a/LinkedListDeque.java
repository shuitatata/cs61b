public class LinkedListDeque <T> {
    private int size;
    private TNode sentinel;

    public class TNode {
        public TNode prev;
        public TNode next;
        T item;

        public TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this. next = next;
        }
    }

    /**
     * Create an empty deque.
     */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        TNode node  = other.sentinel.next;
        while(node != other.sentinel) {
            this.addFirst(node.item);
            node = node.next;
        }
    }
    /**
     * Add an item of type T to the beginning of the deque.
     * @param item The element that is waiting to be added.
     */
    public void addFirst(T item) {
        TNode node = new TNode(item, sentinel.prev, sentinel.next);
        sentinel.next = node;
        if(this.isEmpty()) {
            sentinel.prev = node;
        }
        size++;
    }

    /**
     * Add an item of type T to the end of the deque;
     * @param item The element that is waiting to be added.
     */
    public void addLast(T item) {
        TNode node = new TNode(item, sentinel.prev.prev, sentinel);
        sentinel.prev = node;
        if(this.isEmpty()) {
            sentinel.next = node;
        }
        size++;
    }

    /**
     * Return whether the list is empty
     * @return A boolean value representing whether the deque is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items in the deque
     * @return An integer value representing the size of the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last.
     */
    public void printDeque() {
        TNode node = sentinel.next;
        while(node != sentinel) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * @return The item at the front of the deque. If no such item exists, return null.
     */
    public T removeFirst() {
        if(this.isEmpty()) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next.next = sentinel.next;
        sentinel.next.prev = sentinel;
        size--;
        return result;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * @return The item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if(this.isEmpty()) {
            return null;
        }
        T result = sentinel.prev.item;
        sentinel.prev.prev = sentinel.prev;
        sentinel.prev.next = sentinel;
        size--;
        return result;
    }

    /**
     * Gets the item at the given index.
     * @param index An integer.
     * @return The item at the given index. If no such item exists, returns null.
     */
    public T get(int index) {
        if(index >= size) {
            return null;
        }
        TNode node = sentinel.next;
        while(index > 0 ) {
            node = node.next;
            index--;
        }
        return node.item;
    }

    public T getRecursiveHelper(TNode begin, int index) {
        if(index==0){
            return begin.item;
        }
        return this.getRecursiveHelper(begin.next, index-1);
    }
    /**
     * Gets the
     */
    public T getRecursive(int index) {
        if(index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
}