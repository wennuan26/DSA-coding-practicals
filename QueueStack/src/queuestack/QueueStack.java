/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package queuestack;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author cheizhao
 */

public class QueueStack<T> {
    private Queue<T> queue;
    private LinkedList<T> stack;
    
    public QueueStack() {
        queue = new LinkedList<>();
        stack = new LinkedList<>();
    }
    
    // Push an element to the data structure
    public void push(T element) {
        // Add to both queue and stack
        queue.add(element);
        stack.push(element);
    }
    
    // Pop-friendly removal - checks stack first
    public T pop() {
        if (!stack.isEmpty()) {
            T element = stack.pop();
            queue.remove(element);
            return element;
        } else if (!queue.isEmpty()) {
            return queue.poll();
        }
        return null; // or throw exception for empty structure
    }
    
    // Peek at the next element to be popped
    public T peek() {
        if (!stack.isEmpty()) {
            return stack.peek();
        } else if (!queue.isEmpty()) {
            return queue.peek();
        }
        return null;
    }
    
    // Check if empty
    public boolean isEmpty() {
        return stack.isEmpty() && queue.isEmpty();
    }
    
    // Get size
    public int size() {
        return Math.max(queue.size(), stack.size());
    }
    
    // Main method for testing
    public static void main(String[] args) {
        QueueStack<Integer> queueStack = new QueueStack<>();
        
        System.out.println("=== Testing Pop-Friendly QueueStack ===\n");
        
        // Push elements
        System.out.println("Pushing elements: 10, 20, 30, 40");
        queueStack.push(10);
        queueStack.push(20);
        queueStack.push(30);
        queueStack.push(40);
        
        System.out.println("Size: " + queueStack.size());
        System.out.println("Peek: " + queueStack.peek());
        
        // First pop (from stack)
        System.out.println("\nFirst pop (stack friendly): " + queueStack.pop());
        System.out.println("Peek after first pop: " + queueStack.peek());
        
        // Push more elements
        System.out.println("\nPushing 50 and 60");
        queueStack.push(50);
        queueStack.push(60);
        
        // Multiple pops
        System.out.println("\nPopping all elements:");
        while (!queueStack.isEmpty()) {
            System.out.println("Popped: " + queueStack.pop());
        }
        
        System.out.println("Is empty? " + queueStack.isEmpty());
        
        // Test with Strings
        System.out.println("\n=== Testing with Strings ===");
        QueueStack<String> stringQueueStack = new QueueStack<>();
        
        stringQueueStack.push("First");
        stringQueueStack.push("Second");
        stringQueueStack.push("Third");
        
        System.out.println("Popped: " + stringQueueStack.pop());
        System.out.println("Popped: " + stringQueueStack.pop());
        System.out.println("Popped: " + stringQueueStack.pop());
        System.out.println("Is empty? " + stringQueueStack.isEmpty());
    }
}