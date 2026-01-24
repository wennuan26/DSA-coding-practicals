/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quicksort;

/**
 *
 * @author cheizhao
 */
public class QuickSort {
    
    // Quick Sort
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);
            
            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        // Choose pivot (using last element as pivot)
        int pivot = arr[high];
        
        // Index of smaller element
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    // Alternative: Quick Sort with middle element as pivot
    public static void quickSortMiddle(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSortMiddle(arr, 0, arr.length - 1);
    }
    
    private static void quickSortMiddle(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionMiddle(arr, low, high);
            
            quickSortMiddle(arr, low, pi - 1);
            quickSortMiddle(arr, pi, high);
        }
    }
    
    private static int partitionMiddle(int[] arr, int low, int high) {
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        
        int i = low, j = high;
        
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            
            while (arr[j] > pivot) {
                j--;
            }
            
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        
        return i;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        int[] arr1 = {10, 7, 8, 9, 1, 5};
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Original array 1:");
        printArray(arr1);
        quickSort(arr1);
        System.out.println("Sorted array 1 (Quick Sort with last element as pivot):");
        printArray(arr1);
        
        System.out.println("\nOriginal array 2:");
        printArray(arr2);
        quickSortMiddle(arr2);
        System.out.println("Sorted array 2 (Quick Sort with middle element as pivot):");
        printArray(arr2);
    }
    
    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}