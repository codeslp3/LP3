package PracticalDAA;
import java.util.Scanner; // ✅ added for user input

public class Knapsack {
    
    public static int knapsack(int W, int n, int[] val, int[] wt) {
        // Base case
        if (n == 0 || W == 0) {
            return 0;
        }
        
        // Higher weight than available
        if (wt[n] > W) {
            return knapsack(W, n - 1, val, wt);
        }
        
        // Max of including or not including current item
        else {
            return Math.max(
                val[n] + knapsack(W - wt[n], n - 1, val, wt),
                knapsack(W, n - 1, val, wt)
            );
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // ✅ user input added

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.println("Enter the values of items:");
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }

        System.out.println("Enter the weights of items:");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }

        System.out.print("Enter the capacity of knapsack: ");
        int W = sc.nextInt();

        int result = knapsack(W, n - 1, val, wt);
        System.out.println("Maximum value: " + result);

        sc.close(); // ✅ close scanner
    }
}
