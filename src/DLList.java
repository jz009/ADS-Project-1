public class DLList<T> implements List<T> {

    private int n;

    private class Node<T>{
        T x;
        Node<T> next, prev;

        public Node(T x, Node<T> next, Node<T> prev){
            this.x = x;
            this.next = next;
            this.prev = prev;
        }

        public Node(){
            this.x = null;
            this.next = null;
            this.prev = null;
        }
    }
    private Node<T> dummy;

    public DLList(){
        dummy = new Node<>();
        dummy.prev = dummy;
        dummy.next = dummy;
        n = 0;
    }
    @Override
    public boolean add(int i, T x) {
        if (i > n || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> addNode = new Node<>();
        addNode.x = x;
        if (i < n/2) {
            Node<T> p = dummy.next;
            for (int j = 0; j < i; j++) {
                p = p.next;
            }
            addNode.prev = p.prev;
            addNode.next = p;
            p.prev.next = addNode;
            p.prev = addNode;
        }
        else {
            Node<T> p = dummy;
            for( int j = n; j > i; j--) {
                p = p.prev;
            }
            addNode.prev = p.prev;
            addNode.next = p;
            p.prev.next = addNode;
            p.prev = addNode;
        }
        n++;
        return true;
    }

    @Override
    public void set(int i, T x) {
        if (i >= n || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i < n/2) {
            Node<T> p = dummy.next;
            for (int j = 0; j < i; j++) {
                p = p.next;
            }
            p.x = x;
        }
        else {
            Node<T> p = dummy;
            for( int j = n; j > i; j--) {
                p = p.prev;
            }
            p.x = x;
        }
    }

    @Override
    public T remove(int i) {
        if (i > n || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i < n/2) {
            Node<T> p = dummy.next;
            for (int j = 0; j < i; j++) {
                p = p.next;
            }
            p.prev.next = p.next;
            p.next.prev = p.prev;
            n--;
            return p.x;
        }
        else {
            Node<T> p = dummy;
            for (int j = n; j > i; j--) {
                p = p.prev;
            }
            p.prev.next = p.next;
            p.next.prev = p.prev;
            n--;
            return p.x;
        }
    }

    @Override
    public boolean addFront(T x) {
        add(0, x);
        return true;
    }

    @Override
    public boolean addBack(T x) {
        add(n, x);
        return true;
    }

    @Override
    public T removeFront() {
        return remove(0);
    }

    @Override
    public T removeBack() {
        return remove(n - 1);
    }

    @Override
    public T find(T x) {
        Node<T> cur = dummy.next;
        for(int i = 0; i < n; i++){
            System.out.println(cur.x);
            if(cur.x == x)
                return cur.x;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {return n;}

    @Override
    public void clear() {
        dummy.prev = dummy;
        dummy.next = dummy;
        n = 0;
    }

    @Override
    public void zip(List<T> other) {

    }

    @Override
    public void removeDuplicates() {

    }

    @Override
    public void concatenate(List<T> other) {

    }

    @Override
    public List<T> prefix(int i) {
        return null;
    }

    @Override
    public void promote(int i) {

    }

    @Override
    public void diff(List<T> A, List<T> B) {

    }
}
