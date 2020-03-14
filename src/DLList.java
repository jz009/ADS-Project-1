import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

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
       return add(0, x);
    }

    @Override
    public boolean addBack(T x) {
       return add(n, x);
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
    public void zip(List<T> other) { //JAMIE
        Node<T> p = dummy.next;
        while (!other.isEmpty()) {
            if (p.next == dummy){
                this.concatenate(other);
            }
            else {
                Node<T> newNode = new Node<T>(other.removeFront(), p.next, p);
                p.next = newNode;
                newNode.next.prev = newNode;
                n++;
                p = p.next.next;
            }
        }
    }

    @Override
    public void removeDuplicates() { //EVAN
        Node<T> cur = dummy;
        Node<T> ref;
        int i;

        if(cur.next == null) {
            return;
        }

        for(cur = dummy.next; cur.x != null; cur = cur.next){
            for(ref = cur.next; ref.x != null; ref = ref.next){
                if(cur.x == ref.x){
                    ref.next.prev = ref.prev;
                    ref.prev.next = ref.next;
                    n--;
                }
            }
        }
    }

    @Override
    public void concatenate(List<T> other) { //EVAN
        while(!other.isEmpty()){
            addBack(other.removeFront());
        }
    }
    @Override
    public List<T> prefix(int i) //JAMIE
    {
        if (i >= n) {
            throw new IndexOutOfBoundsException();
        }
        DLList<T> ret = new DLList<>();
        Node<T> p = dummy.next;
        ret.dummy.next = p;
        p.prev = ret.dummy;
        for (int j = 0; j < i - 1; j++) {
            p = p.next;
        }
        dummy.next = p.next;
        p.next.prev = dummy;
        ret.dummy.prev = p;
        p.next = ret.dummy;
        n-= i;
        ret.n = i;
        return ret;
    }

    @Override
    public void promote(int i) {

    }

    @Override
    public void diff(List<T> A, List<T> B) {

    }
}
