package PracticalDAA;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int weight;
    int value;
    
    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class FractionalKnapsack {
    
    static double fractionalKnapsack(int[] weights, int[] values, int capacity) {
        double res = 0;
        
        // Create array of items
        Item[] items = new Item[weights.length];
        for (int i = 0; i < weights.length; i++) {
            items[i] = new Item(weights[i], values[i]);
        }
        
        // Sort by value/weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                double r1 = (double) a.value / a.weight;
                double r2 = (double) b.value / b.weight;
                return Double.compare(r2, r1);
            }
        });
        
        // Process each item
        for (Item item : items) {
            if (capacity <= 0) {
                break; // Bag fully filled
            }
            
            if (item.weight > capacity) {
                // Take fraction
                res += (capacity * ((double) item.value / item.weight));
                capacity = 0;
            } else {
                // Take the whole item
                res += item.value;
                capacity -= item.weight;
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 🔹 Input number of items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        // 🔹 Input weights
        System.out.println("Enter weights of items:");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        // 🔹 Input values
        System.out.println("Enter values of items:");
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        // 🔹 Input knapsack capacity
        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        // 🔹 Calculate maximum value
        double result = fractionalKnapsack(weights, values, capacity);
        System.out.println("Maximum value: " + result);

        sc.close();
    }
}
