/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package queuestack.push;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author cheizhao
 */


public class QueueStackPush {
    private Queue<Integer> mainQueue;
    private Queue<Integer> tempQueue;
    
    public QueueStackPush() {
        mainQueue = new LinkedList<>();
        tempQueue = new LinkedList<>();
    }
    
    // Push-friendly: O(1) time complexity
    public void push(int value) {
        mainQueue.add(value);
        System.out.println("Pushed: " + value);
    }
    
    // Pop: O(n) time complexity
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        
        // Move all elements except the last one to tempQueue
        while (mainQueue.size() > 1) {
            tempQueue.add(mainQueue.remove());
        }
        
        // The last element is our stack's top
        int poppedValue = mainQueue.remove();
        
        // Swap the queues
        Queue<Integer> temp = mainQueue;
        mainQueue = tempQueue;
        tempQueue = temp;
        
        System.out.println("Popped: " + poppedValue);
        return poppedValue;
    }
    
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        
        int peekValue = -1;
        
        // Move all elements to tempQueue, keeping track of the last one
        while (!mainQueue.isEmpty()) {
            peekValue = mainQueue.remove();
            tempQueue.add(peekValue);
        }
        
        // Swap the queues back
        Queue<Integer> temp = mainQueue;
        mainQueue = tempQueue;
        tempQueue = temp;
        
        System.out.println("Peek: " + peekValue);
        return peekValue;
    }
    
    public boolean isEmpty() {
        return mainQueue.isEmpty();
    }
    
    public int size() {
        return mainQueue.size();
    }
    
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        
        System.out.print("Stack (top to bottom): ");
        // Create a copy to display without modifying the stack
        Queue<Integer> displayQueue = new LinkedList<>(mainQueue);
        int[] elements = new int[displayQueue.size()];
        
        // Store elements in array to display in LIFO order (stack-like)
        for (int i = elements.length - 1; i >= 0; i--) {
            elements[i] = displayQueue.remove();
        }
        
        for (int element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Main method to test the QueueStack
    public static void main(String[] args) {
        QueueStackPush stack = new QueueStackPush();
        
        System.out.println("=== Testing Push-Friendly QueueStack ===\n");
        
        // Test pushing elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();
        
        // Test peek
        stack.peek();
        
        // Test pop
        stack.pop();
        stack.display();
        
        // Test more operations
        stack.push(40);
        stack.push(50);
        stack.display();
        
        System.out.println("\n=== Performing multiple operations ===");
        stack.peek();
        stack.pop();
        stack.pop();
        stack.display();
        
        // Test edge cases
        System.out.println("\n=== Testing edge cases ===");
        stack.pop();
        stack.pop();
        stack.pop(); // Trying to pop from empty stack
        
        // Final state
        System.out.println("\nFinal stack size: " + stack.size());
        stack.display();
    }
}