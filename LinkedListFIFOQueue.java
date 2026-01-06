/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cheizhao
 */
class QueueNode {
    int data;
    QueueNode next;
    
    public QueueNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListFIFOQueue {
    private QueueNode front;
    private QueueNode rear;
    private int size;
    
    public LinkedListFIFOQueue() {
        front = null;
        rear = null;
        size = 0;
    }
    
    // Enqueue operation
    public void enqueue(int data) {
        QueueNode newNode = new QueueNode(data);
        
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }
    
    // Dequeue operation
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        
        int data = front.data;
        front = front.next;
        
        if (front == null) {
            rear = null;
        }
        
        size--;
        return data;
    }
    
    // Peek operation
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        
        return front.data;
    }
    
    public boolean isEmpty() {
        return front == null;
    }
    
    public int getSize() {
        return size;
    }
    
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        
        System.out.print("Queue (front to rear): ");
        QueueNode current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    // Main method for testing
    public static void main(String[] args) {
        LinkedListFIFOQueue queue = new LinkedListFIFOQueue();
        
        System.out.println("=== Testing LinkedListFIFOQueue ===");
        
        // Enqueuing elements
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        
        // Display queue
        queue.display();
        System.out.println("Front element: " + queue.peek());
        System.out.println("Size: " + queue.getSize());
        
        // Dequeuing elements
        System.out.println("\nDequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        
        // Display after dequeuing
        queue.display();
        System.out.println("Front element: " + queue.peek());
        System.out.println("Size: " + queue.getSize());
        
        // More operations
        queue.enqueue(50);
        queue.enqueue(60);
        
        // Final display
        queue.display();
        System.out.println("Is empty? " + queue.isEmpty());
        
        // Dequeue all elements
        System.out.println("\nDequeuing all elements:");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println("\nIs empty? " + queue.isEmpty());
    }
}