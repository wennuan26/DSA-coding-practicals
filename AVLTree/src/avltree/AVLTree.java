/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package avltree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author cheizhao
 */

class AVLNode {
    int data;
    int height;
    AVLNode left;
    AVLNode right;
    
    public AVLNode(int data) {
        this.data = data;
        this.height = 1;  // New node is initially added at leaf
        this.left = null;
        this.right = null;
    }
}

public class AVLTree {
    private AVLNode root;
    
    public AVLTree() {
        this.root = null;
    }
    
    // Get height of node
    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }
    
    // Get balance factor of node
    private int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }
    
    // Right rotate
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // Perform rotation
        x.right = y;
        y.left = T2;
        
        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        return x;
    }
    
    // Left rotate
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        // Perform rotation
        y.left = x;
        x.right = T2;
        
        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        
        return y;
    }
    
    // Insert a value into AVL tree
    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    
    private AVLNode insertRecursive(AVLNode node, int value) {
        // 1. Perform normal BST insertion
        if (node == null) {
            return new AVLNode(value);
        }
        
        if (value < node.data) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.data) {
            node.right = insertRecursive(node.right, value);
        } else {
            // Duplicate values not allowed in this implementation
            return node;
        }
        
        // 2. Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));
        
        // 3. Get the balance factor
        int balance = getBalance(node);
        
        // 4. If unbalanced, then there are 4 cases
        
        // Left Left Case
        if (balance > 1 && value < node.left.data) {
            return rightRotate(node);
        }
        
        // Right Right Case
        if (balance < -1 && value > node.right.data) {
            return leftRotate(node);
        }
        
        // Left Right Case
        if (balance > 1 && value > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Left Case
        if (balance < -1 && value < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
    
    // Delete a value from AVL tree
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }
    
    private AVLNode deleteRecursive(AVLNode node, int value) {
        // 1. Perform standard BST delete
        if (node == null) {
            return node;
        }
        
        if (value < node.data) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.data) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Node with only one child or no child
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                
                // No child case
                if (temp == null) {
                    node = null;
                } else {
                    // One child case
                    node = temp;
                }
            } else {
                // Node with two children: Get inorder successor
                AVLNode temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = deleteRecursive(node.right, temp.data);
            }
        }
        
        // If the tree had only one node then return
        if (node == null) {
            return node;
        }
        
        // 2. Update height of the current node
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        
        // 3. Get the balance factor
        int balance = getBalance(node);
        
        // 4. If unbalanced, then there are 4 cases
        
        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        
        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        
        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
    
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    // Search for a value in AVL tree
    public boolean search(int value) {
        return searchRecursive(root, value);
    }
    
    private boolean searchRecursive(AVLNode node, int value) {
        if (node == null) {
            return false;
        }
        
        if (value == node.data) {
            return true;
        } else if (value < node.data) {
            return searchRecursive(node.left, value);
        } else {
            return searchRecursive(node.right, value);
        }
    }
    
    // Tree Traversals
    public void inorder() {
        System.out.print("Inorder Traversal: ");
        inorderRecursive(root);
        System.out.println();
    }
    
    private void inorderRecursive(AVLNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + "(" + getBalance(node) + ") ");
            inorderRecursive(node.right);
        }
    }
    
    public void preorder() {
        System.out.print("Preorder Traversal: ");
        preorderRecursive(root);
        System.out.println();
    }
    
    private void preorderRecursive(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + "(" + getBalance(node) + ") ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }
    
    // Level Order Traversal to show tree structure
    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        
        System.out.println("\nAVL Tree Structure (Level Order):");
        System.out.println("(Format: value[height,balance])");
        
        Queue<AVLNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + ": ");
            
            for (int i = 0; i < levelSize; i++) {
                AVLNode current = queue.poll();
                System.out.print(current.data + "[" + current.height + "," + getBalance(current) + "] ");
                
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            System.out.println();
            level++;
        }
    }
    
    // Check if tree is balanced
    public boolean isBalanced() {
        return isBalancedRecursive(root);
    }
    
    private boolean isBalancedRecursive(AVLNode node) {
        if (node == null) {
            return true;
        }
        
        int balance = getBalance(node);
        if (Math.abs(balance) > 1) {
            return false;
        }
        
        return isBalancedRecursive(node.left) && isBalancedRecursive(node.right);
    }
    
    // Get tree height
    public int getHeight() {
        return height(root);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();
        
        System.out.println("=== AVL Tree Operations ===\n");
        
        // Test Case 1: Insertion causing rotations
        System.out.println("Test Case 1: Right Rotation (LL Case)");
        int[] test1 = {30, 20, 10};
        for (int value : test1) {
            System.out.println("Inserting " + value);
            avl.insert(value);
        }
        avl.printTree();
        System.out.println("Tree is balanced: " + avl.isBalanced());
        
        // Reset tree
        avl = new AVLTree();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Test Case 2: Left Rotation
        System.out.println("Test Case 2: Left Rotation (RR Case)");
        int[] test2 = {10, 20, 30};
        for (int value : test2) {
            System.out.println("Inserting " + value);
            avl.insert(value);
        }
        avl.printTree();
        System.out.println("Tree is balanced: " + avl.isBalanced());
        
        // Reset tree
        avl = new AVLTree();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Test Case 3: Left-Right Rotation
        System.out.println("Test Case 3: Left-Right Rotation (LR Case)");
        int[] test3 = {30, 10, 20};
        for (int value : test3) {
            System.out.println("Inserting " + value);
            avl.insert(value);
        }
        avl.printTree();
        System.out.println("Tree is balanced: " + avl.isBalanced());
        
        // Reset tree
        avl = new AVLTree();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Test Case 4: Right-Left Rotation
        System.out.println("Test Case 4: Right-Left Rotation (RL Case)");
        int[] test4 = {10, 30, 20};
        for (int value : test4) {
            System.out.println("Inserting " + value);
            avl.insert(value);
        }
        avl.printTree();
        System.out.println("Tree is balanced: " + avl.isBalanced());
        
        // Reset tree
        avl = new AVLTree();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Comprehensive test
        System.out.println("Comprehensive Test with multiple operations:");
        int[] values = {50, 30, 70, 20, 40, 60, 80, 15, 25, 35, 45, 55, 65, 75, 85};
        
        System.out.print("Inserting values: ");
        for (int value : values) {
            System.out.print(value + " ");
            avl.insert(value);
        }
        System.out.println("\n");
        
        avl.printTree();
        System.out.println("\nTree height: " + avl.getHeight());
        System.out.println("Tree is balanced: " + avl.isBalanced());
        
        // Traversals
        avl.inorder();
        avl.preorder();
        
        // Search operations
        System.out.println("\nSearch operations:");
        System.out.println("Search 40: " + avl.search(40));
        System.out.println("Search 90: " + avl.search(90));
        
        // Delete operations
        System.out.println("\n--- Delete Operations ---");
        System.out.println("Deleting 20");
        avl.delete(20);
        avl.printTree();
        
        System.out.println("\nDeleting 30");
        avl.delete(30);
        avl.printTree();
        
        System.out.println("\nDeleting 50 (root)");
        avl.delete(50);
        avl.printTree();
        
        System.out.println("\nFinal tree is balanced: " + avl.isBalanced());
        System.out.println("Final tree height: " + avl.getHeight());
    }
}