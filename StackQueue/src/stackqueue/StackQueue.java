/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stackqueue;
import java.util.Stack;

/**
 *
 * @author cheizhao
 */

public class StackQueue {
    private Stack<Integer> enqueueStack;
    private Stack<Integer> dequeueStack;
    
    public StackQueue() {
        enqueueStack = new Stack<>();
        dequeueStack = new Stack<>();
    }
    
    // Enqueue friendly: O(1) time complexity
    public void enqueue(int x) {
        enqueueStack.push(x);
    }
    
    // Dequeue less efficient: O(n) worst case
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        
        // If dequeueStack is empty, transfer all elements from enqueueStack
        if (dequeueStack.isEmpty()) {
            transferElements();
        }
        
        return dequeueStack.pop();
    }
    
    // Peek at front element
    public int front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        
        // If dequeueStack is empty, transfer all elements from enqueueStack
        if (dequeueStack.isEmpty()) {
            transferElements();
        }
        
        return dequeueStack.peek();
    }
    
    // Transfer elements from enqueueStack to dequeueStack
    private void transferElements() {
        while (!enqueueStack.isEmpty()) {
            dequeueStack.push(enqueueStack.pop());
        }
    }
    
    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }
    
    public int size() {
        return enqueueStack.size() + dequeueStack.size();
    }
    
    // Display queue elements
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        
        System.out.print("Queue (front to rear): ");
        
        // First display elements in dequeueStack (which are already in correct order)
        Stack<Integer> temp1 = new Stack<>();
        while (!dequeueStack.isEmpty()) {
            int val = dequeueStack.pop();
            System.out.print(val + " ");
            temp1.push(val);
        }
        // Restore dequeueStack
        while (!temp1.isEmpty()) {
            dequeueStack.push(temp1.pop());
        }
        
        // Then display elements in enqueueStack (in reverse order)
        Stack<Integer> temp2 = new Stack<>();
        while (!enqueueStack.isEmpty()) {
            temp2.push(enqueueStack.pop());
        }
        // Print and restore enqueueStack
        while (!temp2.isEmpty()) {
            int val = temp2.pop();
            System.out.print(val + " ");
            enqueueStack.push(val);
        }
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        StackQueue queue = new StackQueue();
        
        System.out.println("=== Enqueue-Friendly StackQueue Implementation ===");
        
        // Test enqueue operations
        System.out.println("\nEnqueuing elements:");
        queue.enqueue(10);
        queue.display();
        System.out.println("Front element: " + queue.front());
        
        queue.enqueue(20);
        queue.display();
        System.out.println("Front element: " + queue.front());
        
        queue.enqueue(30);
        queue.display();
        System.out.println("Queue size: " + queue.size());
        
        // Test dequeue operations
        System.out.println("\nDequeuing elements:");
        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();
        System.out.println("Front element: " + queue.front());
        
        // Add more elements
        System.out.println("\nEnqueuing more elements:");
        queue.enqueue(40);
        queue.enqueue(50);
        queue.display();
        
        // Dequeue remaining elements
        System.out.println("\nDequeuing all elements:");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
            queue.display();
        }
        
        // Test edge cases
        System.out.println("\nTesting edge cases:");
        try {
            queue.dequeue();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Is queue empty? " + queue.isEmpty());
    }
}