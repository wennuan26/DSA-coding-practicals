/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cheizhao
 */
class StackNode {
    int data;
    StackNode next;
    
    public StackNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListStack {
    private StackNode top;
    private int size;
    
    public LinkedListStack() {
        top = null;
        size = 0;
    }
    
    // Push operation
    public void push(int data) {
        StackNode newNode = new StackNode(data);
        newNode.next = top;
        top = newNode;
        size++;
    }
    
    // Pop operation
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        
        int data = top.data;
        top = top.next;
        size--;
        return data;
    }
    
    // Peek operation
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        
        return top.data;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
    public int getSize() {
        return size;
    }
    
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        
        System.out.print("Stack (top to bottom): ");
        StackNode current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    // Main method for testing
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        
        System.out.println("=== Testing LinkedListStack ===");
        
        // Pushing elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        
        // Display stack
        stack.display();
        System.out.println("Top element: " + stack.peek());
        System.out.println("Size: " + stack.getSize());
        
        // Popping elements
        System.out.println("\nPopped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
        
        // Display after popping
        stack.display();
        System.out.println("Top element: " + stack.peek());
        System.out.println("Size: " + stack.getSize());
        
        // More operations
        stack.push(50);
        stack.push(60);
        
        // Final display
        stack.display();
        System.out.println("Is empty? " + stack.isEmpty());
        
        // Pop all elements
        System.out.println("\nPopping all elements:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println("\nIs empty? " + stack.isEmpty());
    }
}