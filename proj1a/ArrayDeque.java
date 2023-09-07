public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int capacity;

    /**
     * Create an empty deque;
     */
    public ArrayDeque() {
        size = 0;
        capacity = 8;
        items = (T[]) new Object[capacity];
        nextFirst = prev(0);
        nextLast = 0;
    }


    public ArrayDeque(ArrayDeque<T> other) {
        size = 0;
        capacity = 8;
        items = (T[]) new Object[other.capacity];
        for (int i = 0; i < other.size; i++) {
            items[i] = other.get(i);
        }
    }

    /**
     * Resize the length of the array to cap, either increasing or decreasing it.
     *
     * @param cap An integer representing the target length.
     */
    public void resize(int cap) {
        T[] newItems = (T[]) new Object[cap];
        int pos = 0;
        for (int i = nextFirst + 1; i != nextLast; i++) {
            if (i == cap) {
                i = 0;
            }
            newItems[pos] = items[i];
            pos++;
        }
        items = newItems;
        capacity = cap;
        nextFirst = prev(0);
        nextLast = size;
    }

    private int next(int x) {
        if (x + 1 >= capacity) {
            return x + 1 - capacity;
        }
        return x + 1;
    }

    private int prev(int x) {
        if (x - 1 < 0) {
            return x - 1 + capacity;
        }
        return x - 1;
    }

    public void addFirst(T item) {
        if (size == capacity) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = prev(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = next(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = next(nextFirst); i != nextLast; i = next(i)) {
            System.out.print(items[i]);
        }
        System.out.println();
    }

    public T removeFirst() {
        T item = items[next(nextFirst)];
        nextFirst = next(nextFirst);
        size--;
        return item;
    }

    public T removeLast() {
        T item = items[prev(nextLast)];
        nextLast = prev(nextLast);
        size--;
        return item;
    }

    public T get(int index) {
        int Last = nextLast - 1;
        if (Last <= nextFirst) {
            Last += capacity;
        }
        if (index + nextFirst + 1 <= Last) {
            return items[(index + nextFirst + 1) % capacity];
        }
        return null;
    }


}
