/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rabbitproblem;

/**
 *
 * @author cheizhao
 */
public class RabbitProblem {
    
    // Rabbit class to represent individual rabbits
    static class Rabbit {
        int age; // in months
        boolean isFemale;
        
        public Rabbit(int age, boolean isFemale) {
            this.age = age;
            this.isFemale = isFemale;
        }
        
        public void incrementAge() {
            this.age++;
        }
        
        public boolean canReproduce() {
            return isFemale && age >= 4; // Assuming rabbits can reproduce from 4 months
        }
    }
    
    // Simulate rabbit population growth
    public static void simulateRabbitGrowth(int initialPairs, int months, int reproductionAge) {
        System.out.println("\n=== Rabbit Population Simulation ===");
        System.out.println("Initial pairs: " + initialPairs);
        System.out.println("Simulation period: " + months + " months");
        System.out.println("Reproduction age: " + reproductionAge + " months");
        System.out.println("-----------------------------------");
        
        // Create initial population
        java.util.LinkedList<Rabbit> population = new java.util.LinkedList<>();
        
        // Add initial pairs (1 male, 1 female per pair)
        for (int i = 0; i < initialPairs; i++) {
            population.add(new Rabbit(2, false)); // Male, 2 months old
            population.add(new Rabbit(2, true));  // Female, 2 months old
        }
        
        // Simulate month by month
        for (int month = 1; month <= months; month++) {
            int newBabies = 0;
            int reproducingFemales = 0;
            
            // Count reproducing females and age all rabbits
            java.util.Iterator<Rabbit> iterator = population.iterator();
            while (iterator.hasNext()) {
                Rabbit rabbit = iterator.next();
                
                // Age the rabbit
                rabbit.incrementAge();
                
                // Count reproducing females
                if (rabbit.isFemale && rabbit.age >= reproductionAge) {
                    reproducingFemales++;
                }
            }
            
            // Each reproducing female produces 6 babies (3 males, 3 females)
            for (int i = 0; i < reproducingFemales; i++) {
                // Add 3 male babies
                for (int j = 0; j < 3; j++) {
                    population.add(new Rabbit(0, false));
                    newBabies++;
                }
                // Add 3 female babies
                for (int j = 0; j < 3; j++) {
                    population.add(new Rabbit(0, true));
                    newBabies++;
                }
            }
            
            // Calculate statistics
            int totalRabbits = population.size();
            int adultRabbits = 0;
            int babyRabbits = 0;
            int maleCount = 0;
            int femaleCount = 0;
            
            for (Rabbit rabbit : population) {
                if (rabbit.age < 2) {
                    babyRabbits++;
                } else {
                    adultRabbits++;
                }
                
                if (rabbit.isFemale) {
                    femaleCount++;
                } else {
                    maleCount++;
                }
            }
            
            // Print monthly report
            System.out.printf("Month %d:\n", month);
            System.out.printf("  Total rabbits: %d\n", totalRabbits);
            System.out.printf("  Adult rabbits: %d\n", adultRabbits);
            System.out.printf("  Baby rabbits: %d\n", babyRabbits);
            System.out.printf("  Male/Female ratio: %d/%d\n", maleCount, femaleCount);
            System.out.printf("  New babies born: %d\n", newBabies);
            System.out.println();
        }
        
        // Final summary
        System.out.println("=== Simulation Complete ===");
        System.out.println("Final population: " + population.size() + " rabbits");
    }
    
    // Alternative mathematical approach using Fibonacci-like sequence
    public static void fibonacciRabbitModel(int months, int litterSize) {
        System.out.println("\n=== Mathematical Rabbit Model (Fibonacci-like) ===");
        System.out.println("Months: " + months + ", Litter size: " + litterSize);
        System.out.println("-----------------------------------");
        
        if (months <= 0) {
            System.out.println("Invalid month count");
            return;
        }
        
        // Initialize arrays for mature and immature pairs
        int[] maturePairs = new int[months + 1];
        int[] immaturePairs = new int[months + 1];
        
        // Start with 1 immature pair
        immaturePairs[0] = 1;
        maturePairs[0] = 0;
        
        System.out.println("Month 0: 1 immature pair, 0 mature pairs");
        
        for (int i = 1; i <= months; i++) {
            // Mature pairs from previous month plus immature pairs that matured
            maturePairs[i] = maturePairs[i - 1] + immaturePairs[i - 1];
            
            // Each mature pair produces litterSize/2 new pairs (assuming 50% male/female)
            immaturePairs[i] = maturePairs[i - 1] * (litterSize / 2);
            
            System.out.printf("Month %d: %d mature pairs, %d immature pairs, Total: %d pairs\n",
                    i, maturePairs[i], immaturePairs[i], 
                    maturePairs[i] + immaturePairs[i]);
        }
        
        System.out.println("Total rabbit pairs after " + months + " months: " + 
                          (maturePairs[months] + immaturePairs[months]));
        System.out.println("Total rabbits (assuming 2 per pair): " + 
                          ((maturePairs[months] + immaturePairs[months]) * 2));
    }
    
    // Main method
    public static void main(String[] args) {
        System.out.println("=== Rabbit Problem Simulation ===\n");
        
        // Test simulation approach
        simulateRabbitGrowth(1, 12, 4);
        
        // Test mathematical approach
        fibonacciRabbitModel(12, 6);
        
        // Test with different parameters
        System.out.println("\n\n=== Alternative Scenario ===");
        simulateRabbitGrowth(2, 8, 3);
        fibonacciRabbitModel(8, 8);
    }
}