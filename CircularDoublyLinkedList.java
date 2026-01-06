/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circulardoublylinkedlist;

/**
 *
 * @author cheizhao
 */
class Node {
    int data;
    Node next;
    Node prev;
    
    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class CircularDoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;
    
    public CircularDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    // Add to front
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
        size++;
    }
    
    // Add to end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            addFirst(data);
        } else {
            newNode.next = head;
            newNode.prev = tail;
            tail.next = newNode;
            head.prev = newNode;
            tail = newNode;
            size++;
        }
    }
    
    // Remove from front
    public int removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        
        int data = head.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        size--;
        return data;
    }
    
    // Remove from end
    public int removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        
        int data = tail.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
        size--;
        return data;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public void displayForward() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        
        Node current = head;
        System.out.print("Forward: ");
        for (int i = 0; i < size; i++) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public void displayBackward() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        
        Node current = tail;
        System.out.print("Backward: ");
        for (int i = 0; i < size; i++) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }
    
    // Main method for testing
    public static void main(String[] args) {
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        
        System.out.println("=== Testing CircularDoublyLinkedList ===");
        
        // Adding elements
        list.addFirst(10);
        list.addLast(20);
        list.addFirst(5);
        list.addLast(30);
        
        // Display
        list.displayForward();
        list.displayBackward();
        System.out.println("Size: " + list.getSize());
        
        // Removing elements
        System.out.println("\nRemoved first: " + list.removeFirst());
        System.out.println("Removed last: " + list.removeLast());
        
        // Display after removal
        list.displayForward();
        System.out.println("Size: " + list.getSize());
        
        // Add more elements
        list.addLast(40);
        list.addFirst(2);
        
        // Final display
        list.displayForward();
        list.displayBackward();
    }
}
