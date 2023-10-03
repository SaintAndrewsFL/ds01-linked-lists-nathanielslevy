public class MyLinkedList<T> {
    private Node head;
    private Node tail;
    private boolean circular;
    private boolean doublyLinked;
    private boolean normal;
    private boolean both;

    public MyLinkedList() {
        this.circular = false;
        this.doublyLinked = false;
    }

    public MyLinkedList(boolean circular, boolean doublyLinked) {
        this.circular = circular;
        this.doublyLinked = doublyLinked;
        normal = !circular && !doublyLinked;
        both = circular && doublyLinked;
    }

    public boolean add(T newItem) { // updated
        Node newNode = new Node(newItem);
        int idx = 0;
        if (head == null) {
            head = newNode;
        }
        else if (! circular && ! doublyLinked) {
            if (head.next == null) {
                tail = newNode;
                head.setNext(tail);
            }
            else {
                tail.setNext(newNode);
                tail = newNode;
            }
            }
        else if (! circular) {
            if (head.next == null) {
                tail = newNode;
                head.setNext(tail);
                tail.setBack(head);
            }
            else {
                tail.setNext(newNode);
                newNode.setBack(tail);
                tail = newNode;
            }
        }
        else if (! doublyLinked) {
            if (head.next == null) {
                tail = newNode;
                head.setNext(tail);
                tail.setNext(head);
            }
            else {
                tail.setNext(newNode);
                newNode.setNext(head);
                tail = newNode;
            }
        }
        else {
            if (head.next == null) {
                tail = newNode;
                head.setNext(tail);
                tail.setBack(head);
                tail.setNext(head);
                head.setBack(tail);
            }
            tail.setNext(newNode);
            newNode.setBack(tail);
            newNode.setNext(head);
            head.setBack(newNode);
            tail = newNode;
        }
        return true;
    }
    public void addIndex(int index, T newItem) { // updated
        Node newNode = new Node(newItem);
        Node current = head;
        if (index==0) {
            addFirst(newItem);
        }
        if (index == size() - 1) {
            addLast(newItem);
        }
        int idx = 0;
        while(idx + 1 < index) {
            current = current.next;
            idx++;
        }
        Node second_half = current.next;
        current.next = newNode;
        newNode.setNext(second_half);
        if (doublyLinked) {
            second_half.setBack(newNode);
            newNode.setBack(current);
        }
    }


    public void addFirst(T newItem) { // updated
        Node newNode = new Node(newItem);
        if (head == null) {
            head = newNode;
        }
        else {
            newNode.setNext(head);
            head = newNode;
            if (size() == 2) {
                tail = head.next;
            }
            if (both) {
                head.next.setBack(head);
                head.setBack(tail);
                tail.setNext(head);
            }
            if (circular) {
                tail.setNext(head);
            }
            if (doublyLinked) {
                head.next.setBack(head);
            }
        }

    }

    public void addLast(T newItem) { // updated
        Node newNode = new Node(newItem);
        if (head == null) {
            head = newNode;
        }
        else if (size() == 1) {
            tail = newNode;
        }
        else {
            Node pen = tail;
            tail.setNext(newNode);
            tail = newNode;
            if (both) {
                tail.setNext(head);
                head.setBack(tail);
                tail.setBack(pen);
            }
            else if (circular) {
                tail.setNext(head);
            }
            else if (doublyLinked) {
                tail.setBack(pen);
            }
        }
    }

    public void clear() { // updated
        head = null;
        tail = null;
    }

    public boolean contains(T newItem) { // updated
        Node current = head;
        int idx = 0;
        while (idx < size()) {
            if (current.getData().equals(newItem)) {
                return true;
            }
            current = current.next;
            idx++;
        }
        return false;
    }

    public T get(int index) { // updated
        if (index >= size()) {
            return null;
        }
        Node current = head;
        int idx = 0;
        while (idx < index) {
            current = current.next;
            idx++;
        }
        if (current != null) {
            return current.getData();
        }
        return null;
    }

    public T getFirst() { // updated
        return head.getData();
    }

    public T getLast() { // updated
        return tail.getData();
    }


    public int indexOf(T item) { // updated
        Node current = head;
        int idx = 0;
        while (idx < size()) {
            if (current.getData().equals(item)) {
                return idx;
            }
            current = current.next;
            idx++;
        }
        return -1;
    }

    public int lastIndexOf(T item) {
        Node current = head;
        int idx = 0;
        int recentIdx = -1;
        while (idx < size()) {
            if (current.getData().equals(item)) {
                recentIdx = idx;
            }
            current = current.next;
            idx++;
        }
        return recentIdx;
    }

    public T poll() { // updated
        T item = head.getData();
        head = head.next;
        if (normal) {
            System.out.println("normal");
            return item;
        }
        else if (both) {
            System.out.println("both for poll");
            head.setBack(tail);
            tail.setNext(head);
        }
        else if (circular) {
            tail.next = head;
        }
        else if (doublyLinked) {
            head.next.setBack(head);
        }
        return item;
    }

    public T pollLast() { // updated
        T item = tail.getData();
        Node current = head;
        int idx = 0;
        while (idx + 3 < size()) {
            current = current.next;
            idx++;
        }
        Node pen = current;
        tail = current.next;
        if (both) {
            tail.setNext(head);
            tail.setBack(pen);
            head.setBack(tail);
        }
        else if (doublyLinked) {
            tail.setBack(pen);
            tail.next = null;
        }
        else if (circular) {
            tail.next = head;
        }
        else {
            tail.next = null;
        }
        return item;
    }

    public T remove(int index) {
        if (index >= size()) {
            return null;
        }
        int idx = 0;
        Node current = head;
        if (index == 0) {
            head = current.next;
            if (both) {
                tail.setNext(head);
                head.setBack(tail);
            }
            if (circular) {
                tail.setNext(head);
            }
            return current.getData();
        }
        while (idx + 1 < index) {
            System.out.println("Begin Moving");
            current = current.next;
            idx++;
        }//
        System.out.println(current.getData());
        System.out.println("Idx is " + idx);
        T obj = current.next.getData();
        if (both) {
            current.setNext(current.next.next);
            current.next.setBack(current);
        }
        if (doublyLinked) {
            current.next.next.setBack(current);
        }
        if (circular) {
            current.setNext(current.next.next);
        }
        if (normal) {
            if (current == tail) {
                current = null;
            }
            if (current.next == tail) {
                current.next = null;
                tail = current;
            }
            else {
                current.setNext(current.next.next);
            }
        }
        return obj;
    }

    public T remove(T item) {
        return remove(indexOf(item));
    }

    public T set(int index, T item) {
        T replacedObject;
        replacedObject = remove(index);
        addIndex(index, item);
        return replacedObject;
    }

    public int size() { // updated
        Node current = head;
        int idx = 0;
        if (head == null) {
            return 0;
        }
        while (current.next != null && current.next != head) {
            current = current.next;
            idx++;
    }
        return idx + 1;
    }

    public T get(T item) { // updated
        int idx = 0;
        Node current = head;
        while (idx < size()) {
            if (current.getData().equals(item)) {
                return item;
            }
            current = current.next;
            idx++;
        }
        return null;
    }

    public boolean isCircular() {
        Node hare = head;
        Node tortoise = head;
        while (tortoise != null && hare!= null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
            if (tortoise == hare) {
                return true;
            }
            if (tortoise == head) {
                return false;
            }
        }
        return false;
    }


    public class Node {
        private Node next;
        private T data;
        private Node back;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, boolean circular, boolean doublyLinked) {
            this.data = data;


        }

        public Node getNext() {
            return next;
        }

        public Node getBack() { return back; }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setBack(Node back) {this.back = back; }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }
}
