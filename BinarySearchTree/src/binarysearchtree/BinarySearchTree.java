/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarysearchtree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author cheizhao
 */


class BSTNode {
    int data;
    BSTNode left;
    BSTNode right;
    
    public BSTNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    private BSTNode root;
    
    public BinarySearchTree() {
        this.root = null;
    }
    
    // Insert a value into BST
    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    
    private BSTNode insertRecursive(BSTNode node, int value) {
        if (node == null) {
            return new BSTNode(value);
        }
        
        if (value < node.data) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.data) {
            node.right = insertRecursive(node.right, value);
        }
        // Duplicate values are not inserted
        
        return node;
    }
    
    // Search for a value in BST
    public boolean search(int value) {
        return searchRecursive(root, value);
    }
    
    private boolean searchRecursive(BSTNode node, int value) {
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
    
    // Delete a value from BST
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }
    
    private BSTNode deleteRecursive(BSTNode node, int value) {
        if (node == null) {
            return null;
        }
        
        if (value < node.data) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.data) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            
            // Node with two children: Get inorder successor
            node.data = minValue(node.right);
            
            // Delete the inorder successor
            node.right = deleteRecursive(node.right, node.data);
        }
        
        return node;
    }
    
    private int minValue(BSTNode node) {
        int minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }
    
    // Tree Traversals
    public void inorder() {
        System.out.print("Inorder Traversal: ");
        inorderRecursive(root);
        System.out.println();
    }
    
    private void inorderRecursive(BSTNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + " ");
            inorderRecursive(node.right);
        }
    }
    
    public void preorder() {
        System.out.print("Preorder Traversal: ");
        preorderRecursive(root);
        System.out.println();
    }
    
    private void preorderRecursive(BSTNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }
    
    public void postorder() {
        System.out.print("Postorder Traversal: ");
        postorderRecursive(root);
        System.out.println();
    }
    
    private void postorderRecursive(BSTNode node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.data + " ");
        }
    }
    
    // Level Order Traversal (BFS)
    public void levelOrder() {
        if (root == null) {
            return;
        }
        
        System.out.print("Level Order Traversal: ");
        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            BSTNode current = queue.poll();
            System.out.print(current.data + " ");
            
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println();
    }
    
    // Find minimum value in BST
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMinRecursive(root);
    }
    
    private int findMinRecursive(BSTNode node) {
        if (node.left == null) {
            return node.data;
        }
        return findMinRecursive(node.left);
    }
    
    // Find maximum value in BST
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMaxRecursive(root);
    }
    
    private int findMaxRecursive(BSTNode node) {
        if (node.right == null) {
            return node.data;
        }
        return findMaxRecursive(node.right);
    }
    
    // Get height of tree
    public int getHeight() {
        return getHeightRecursive(root);
    }
    
    private int getHeightRecursive(BSTNode node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // Check if tree is valid BST
    public boolean isValidBST() {
        return isValidBSTRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean isValidBSTRecursive(BSTNode node, int min, int max) {
        if (node == null) {
            return true;
        }
        
        if (node.data <= min || node.data >= max) {
            return false;
        }
        
        return isValidBSTRecursive(node.left, min, node.data) &&
               isValidBSTRecursive(node.right, node.data, max);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        
        System.out.println("=== Binary Search Tree Operations ===\n");
        
        // Insert values
        int[] values = {50, 30, 70, 20, 40, 60, 80, 15, 25, 35, 45, 55, 65, 75, 85};
        System.out.print("Inserting values: ");
        for (int value : values) {
            System.out.print(value + " ");
            bst.insert(value);
        }
        System.out.println("\n");
        
        // Traversals
        bst.inorder();
        bst.preorder();
        bst.postorder();
        bst.levelOrder();
        
        System.out.println();
        
        // Search operations
        System.out.println("Search 40: " + bst.search(40));
        System.out.println("Search 90: " + bst.search(90));
        
        // Min and Max
        System.out.println("\nMinimum value: " + bst.findMin());
        System.out.println("Maximum value: " + bst.findMax());
        
        // Height
        System.out.println("Tree height: " + bst.getHeight());
        
        // Check if valid BST
        System.out.println("Is valid BST? " + bst.isValidBST());
        
        // Delete operations
        System.out.println("\n--- Delete Operations ---");
        System.out.println("Deleting 20 (leaf node)");
        bst.delete(20);
        bst.inorder();
        
        System.out.println("\nDeleting 30 (node with one child)");
        bst.delete(30);
        bst.inorder();
        
        System.out.println("\nDeleting 50 (node with two children)");
        bst.delete(50);
        bst.inorder();
        
        // After deletions
        System.out.println("\nTree height after deletions: " + bst.getHeight());
        System.out.println("Is valid BST? " + bst.isValidBST());
    }
}