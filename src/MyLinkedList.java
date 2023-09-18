public class MyLinkedList<T> {
    private Node head;
    private Node tail;

    public boolean add(T newItem) {
        Node newNode = new Node(newItem);
        if(head==null) {
            head = newNode;
            return true;
        }
        else {
            Node current = head;

            while(current.next!=null) {
                current = current.next;
            }
            current.next = newNode;
            tail = current.next;
        }
        return true;
    }


    // [1,2,3,4,5,6] idx = 2
    public void addIndex(int index, T newItem) {
        Node newNode = new Node(newItem);
        Node current = head;
        if (index==0) {
            Node second_half = current.next;
            head = newNode;
            head.setNext(current);
            newNode.setNext(second_half);
        }
        int idx = 0;
        while(idx + 1 < index) {
            current = current.next;
            idx++;
        }
        Node second_half = current.next;
        current.next = newNode;
        newNode.setNext(second_half);
    }

    public void addFirst(T newItem) {
        addIndex(0, newItem);
    }

    public void addLast(T newItem) {
        Node newNode = new Node(newItem);
        tail.setNext(newNode);
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public boolean contains(T newItem) {
        Node current = head;
        while (current != null) {
            if (current.getData().equals(newItem)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
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

    public T getFirst() {
        return head.getData();
    }

    public T getLast() {
        return tail.getData();
    }

    public int indexOf(T item) {
        Node current = head;
        int idx = 0;
        while (current != null) {
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
        while (current != null) {
            if (current.getData().equals(item)) {
                recentIdx = idx;
            }
            current = current.next;
            idx++;
        }
        return recentIdx;
    }

    public T poll() {
        T item = head.getData();
        head = head.next;
        return item;
    }

    public T pollLast() {
        T item = tail.getData();
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        tail = current;
        return item;
    }

    public T remove(int index) {
        int idx = 0;
        Node current = head;
        if (index == 0) {
            head = current.next;
            return current.getData();
        }
        while (idx + 1 < index) {
            if (current.next == null) {
                return null;
            }
            current = current.next;
            idx++;
        }
        if (current.next == null) {
            return null;
        }

        T obj = current.next.getData();
        current.next = current.next.next;
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

    public int size() {
        Node current = head;
        int idx = 0;
        while (current != null) {
            current = current.next;
            idx++;
        }
        return idx;
    }

    public T get(T item) {
        Node current = head;
        while (current != null) {
            if (current.getData().equals(item)) {
                return item;
            }
            current = current.next;
        }
        return null;
    }
//        c
//        6 --> 5 --> 1 --> 2 --> 3 --> 4

    public void reverse() {
        Node current = tail;
        int idx = 0;
        int s = size();
        while (idx < s) {
            System.out.println(getLast());
            set(idx, pollLast());
            idx++;
        }
    }





    public class Node {
        private Node next;
        private T data;

        public Node(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }



}
