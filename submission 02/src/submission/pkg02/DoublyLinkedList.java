package submission.pkg02;

class DoublyLinkedList {
    Node head;

    class Node {
        int data;
        Node prev, next;
        Node(int data) { this.data = data; }
    }

    void insertFront(int x) {
        Node n = new Node(x);
        n.next = head;

        if (head != null)
            head.prev = n;

        head = n;
    }

    void insertEnd(int x) {
        Node n = new Node(x);

        if (head == null) {
            head = n;
            return;
        }

        Node t = head;
        while (t.next != null)
            t = t.next;

        t.next = n;
        n.prev = t;
    }

    void delete(int x) {
        Node t = head;

        while (t != null && t.data != x)
            t = t.next;

        if (t == null) return;

        if (t.prev != null)
            t.prev.next = t.next;
        else
            head = t.next;

        if (t.next != null)
            t.next.prev = t.prev;
    }
}
