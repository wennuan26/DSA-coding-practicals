/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package unboundedarraystack;
import java.util.EmptyStackException;
import java.util.Arrays;
/**
 *
 * @author cheizhao
 */

public class UnboundedArrayStack<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 2.0;
    private static final double SHRINK_FACTOR = 0.5;
    private static final double SHRINK_THRESHOLD = 0.25;
    
    private T[] stackArray;
    private int top;
    private int capacity;
    
    /**
     * Constructor to initialize the stack with default capacity
     */
    @SuppressWarnings("unchecked")
    public UnboundedArrayStack() {
        this.capacity = DEFAULT_CAPACITY;
        this.stackArray = (T[]) new Object[capacity];
        this.top = -1; // Stack is initially empty
    }
    
    /**
     * Constructor to initialize the stack with specified initial capacity
     * @param initialCapacity the initial capacity of the stack
     */
    @SuppressWarnings("unchecked")
    public UnboundedArrayStack(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.capacity = initialCapacity;
        this.stackArray = (T[]) new Object[capacity];
        this.top = -1;
    }
    
    /**
     * Pushes an element onto the stack
     * @param element the element to be pushed
     */
    public void push(T element) {
        // Check if array is full, then resize
        if (isFull()) {
            resize((int)(capacity * GROW_FACTOR));
        }
        
        stackArray[++top] = element;
        System.out.println("Pushed: " + element + " (Capacity: " + capacity + ")");
    }
    
    /**
     * Pops an element from the stack
     * @return the popped element
     * @throws EmptyStackException if stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        
        T element = stackArray[top];
        stackArray[top--] = null; // Clear reference to help garbage collection
        
        // Check if array is too empty, then shrink (but not below default capacity)
        double loadFactor = (double)(top + 1) / capacity;
        if (loadFactor < SHRINK_THRESHOLD && capacity > DEFAULT_CAPACITY) {
            int newCapacity = Math.max(DEFAULT_CAPACITY, (int)(capacity * SHRINK_FACTOR));
            resize(newCapacity);
        }
        
        System.out.println("Popped: " + element + " (Capacity: " + capacity + ")");
        return element;
    }
    
    /**
     * Returns the top element without removing it
     * @return the top element
     * @throws EmptyStackException if stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[top];
    }
    
    /**
     * Checks if the stack is empty
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * Returns the current size of the stack
     * @return the number of elements in the stack
     */
    public int size() {
        return top + 1;
    }
    
    /**
     * Returns the current capacity of the internal array
     * @return the current capacity
     */
    public int capacity() {
        return capacity;
    }
    
    /**
     * Clears all elements from the stack and resets to default capacity
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        capacity = DEFAULT_CAPACITY;
        stackArray = (T[]) new Object[capacity];
        top = -1;
        System.out.println("Stack cleared. Capacity reset to: " + capacity);
    }
    
    /**
     * Displays the stack contents
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        
        System.out.print("Stack (top to bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stackArray[i]);
            if (i > 0) {
                System.out.print(" <- ");
            }
        }
        System.out.println(" | Size: " + size() + ", Capacity: " + capacity);
    }
    
    /**
     * Checks if the internal array is full
     * @return true if array is full, false otherwise
     */
    private boolean isFull() {
        return top == capacity - 1;
    }
    
    /**
     * Resizes the internal array to the specified new capacity
     * @param newCapacity the new capacity for the array
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        System.out.println("Resizing from " + capacity + " to " + newCapacity);
        
        // Create new array with the new capacity
        T[] newArray = (T[]) new Object[newCapacity];
        
        // Copy elements to the new array
        for (int i = 0; i <= top; i++) {
            newArray[i] = stackArray[i];
        }
        
        // Update reference and capacity
        stackArray = newArray;
        capacity = newCapacity;
    }
    
    // Main method to test the implementation
    public static void main(String[] args) {
        System.out.println("=== Testing Unbounded Array Stack ===\n");
        
        // Create a stack with initial capacity of 5
        UnboundedArrayStack<Integer> stack = new UnboundedArrayStack<>(5);
        
        // Test pushing elements (should trigger resizing)
        System.out.println("--- Pushing elements ---");
        for (int i = 1; i <= 12; i++) {
            stack.push(i * 10);
        }
        
        System.out.println("\n--- Current stack state ---");
        stack.display();
        System.out.println("Top element: " + stack.peek());
        System.out.println("Stack size: " + stack.size());
        System.out.println("Stack capacity: " + stack.capacity());
        
        // Test popping elements (should trigger shrinking)
        System.out.println("\n--- Popping elements ---");
        try {
            while (!stack.isEmpty()) {
                stack.pop();
                
                // Display intermediate state every few pops
                if (stack.size() % 4 == 0 && !stack.isEmpty()) {
                    System.out.println("\nIntermediate state:");
                    stack.display();
                }
            }
        } catch (EmptyStackException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Test edge cases
        System.out.println("\n--- Testing edge cases ---");
        
        // Try to pop from empty stack
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Correctly caught exception when popping from empty stack");
        }
        
        // Test with String type
        System.out.println("\n--- Testing with String type ---");
        UnboundedArrayStack<String> stringStack = new UnboundedArrayStack<>(3);
        
        stringStack.push("Hello");
        stringStack.push("World");
        stringStack.push("Java");
        stringStack.push("Stack"); // Should trigger resize
        
        stringStack.display();
        
        while (!stringStack.isEmpty()) {
            stringStack.pop();
        }
        
        // Test clear functionality
        System.out.println("\n--- Testing clear functionality ---");
        UnboundedArrayStack<Character> charStack = new UnboundedArrayStack<>();
        
        for (char c = 'A'; c <= 'Z'; c++) {
            charStack.push(c);
        }
        
        System.out.println("Before clear:");
        charStack.display();
        
        charStack.clear();
        
        System.out.println("After clear:");
        charStack.display();
        
        System.out.println("\n=== Testing completed ===");
    }
}