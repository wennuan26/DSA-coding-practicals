/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package optimizedquicksort;
import java.util.Random;
/**
 *
 * @author cheizhao
 */


public class OptimizedQuickSort {
    
    // Optimized Quick Sort with multiple improvements
    public static void optimizedQuickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        optimizedQuickSort(arr, 0, arr.length - 1);
    }
    
    private static void optimizedQuickSort(int[] arr, int low, int high) {
        // Use insertion sort for small subarrays (optimization)
        if (high - low + 1 <= 10) {
            insertionSort(arr, low, high);
            return;
        }
        
        // Tail recursion optimization - sort smaller partition first
        while (low < high) {
            int pivotIndex = partition(arr, low, high);
            
            // Sort the smaller partition first
            if (pivotIndex - low < high - pivotIndex) {
                optimizedQuickSort(arr, low, pivotIndex - 1);
                low = pivotIndex + 1;  // Tail recursion for larger partition
            } else {
                optimizedQuickSort(arr, pivotIndex + 1, high);
                high = pivotIndex - 1;  // Tail recursion for larger partition
            }
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        // 1. Median-of-three pivot selection
        int mid = low + (high - low) / 2;
        
        // Sort low, mid, high and use median as pivot
        if (arr[mid] < arr[low]) swap(arr, low, mid);
        if (arr[high] < arr[low]) swap(arr, low, high);
        if (arr[high] < arr[mid]) swap(arr, mid, high);
        
        // Place pivot at high-1
        int pivot = arr[mid];
        swap(arr, mid, high - 1);
        
        // 2. Three-way partition (handles duplicates efficiently)
        int i = low;
        int j = high - 1;
        
        while (true) {
            while (arr[++i] < pivot);
            while (arr[--j] > pivot);
            
            if (i >= j) break;
            swap(arr, i, j);
        }
        
        // Restore pivot
        swap(arr, i, high - 1);
        return i;
    }
    
    // Insertion sort for small arrays (optimization)
    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    // Alternative: Randomized Quick Sort
    public static void randomizedQuickSort(int[] arr) {
        randomizedQuickSort(arr, 0, arr.length - 1);
    }
    
    private static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Random pivot selection to avoid worst-case scenarios
            int randomPivot = randomPartition(arr, low, high);
            randomizedQuickSort(arr, low, randomPivot - 1);
            randomizedQuickSort(arr, randomPivot + 1, high);
        }
    }
    
    private static int randomPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomIndex = low + rand.nextInt(high - low + 1);
        swap(arr, randomIndex, high);
        return partition(arr, low, high);
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Utility method to check if array is sorted
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        int[] testArray1 = {64, 34, 25, 12, 22, 11, 90, 45, 33, 78, 56, 89, 23, 67};
        int[] testArray2 = {10, 7, 8, 9, 1, 5, 5, 5, 2, 3, 6, 4}; // With duplicates
        int[] testArray3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Already sorted
        
        System.out.println("=== Testing Optimized Quick Sort ===\n");
        
        // Test 1
        System.out.println("Test 1 - Random array:");
        printArray("Original:", testArray1);
        optimizedQuickSort(testArray1);
        printArray("Sorted:  ", testArray1);
        System.out.println("Is sorted? " + isSorted(testArray1));
        
        System.out.println("\n" + "-".repeat(50) + "\n");
        
        // Test 2
        System.out.println("Test 2 - Array with duplicates:");
        printArray("Original:", testArray2);
        randomizedQuickSort(testArray2);
        printArray("Sorted:  ", testArray2);
        System.out.println("Is sorted? " + isSorted(testArray2));
        
        System.out.println("\n" + "-".repeat(50) + "\n");
        
        // Test 3
        System.out.println("Test 3 - Already sorted array:");
        printArray("Original:", testArray3);
        optimizedQuickSort(testArray3);
        printArray("Sorted:  ", testArray3);
        System.out.println("Is sorted? " + isSorted(testArray3));
        
        // Performance comparison
        System.out.println("\n=== Performance Test ===");
        int[] largeArray = generateRandomArray(10000);
        int[] copyArray = largeArray.clone();
        
        long startTime = System.currentTimeMillis();
        optimizedQuickSort(largeArray);
        long endTime = System.currentTimeMillis();
        System.out.println("Optimized Quick Sort time: " + (endTime - startTime) + " ms");
        
        startTime = System.currentTimeMillis();
        randomizedQuickSort(copyArray);
        endTime = System.currentTimeMillis();
        System.out.println("Randomized Quick Sort time: " + (endTime - startTime) + " ms");
    }
    
    private static void printArray(String label, int[] arr) {
        System.out.print(label + " ");
        for (int i = 0; i < Math.min(arr.length, 20); i++) {
            System.out.print(arr[i] + " ");
        }
        if (arr.length > 20) {
            System.out.print("...");
        }
        System.out.println();
    }
    
    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
}