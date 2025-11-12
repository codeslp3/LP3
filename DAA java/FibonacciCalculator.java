
import java.util.Scanner;

public class FibonacciCalculator { 
    // Recursive method to calculate Fibonacci
    public static int recursiveFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
        }
    }

    // Non-recursive method to calculate Fibonacci
    public static void nonRecursiveFibonacci(int n) {
        int first = 0;
        int second = 1;
        
        System.out.println(first);
        System.out.println(second);
        
        while ((n-2) > 0) {
            int third = first + second;
            first = second;
            second = third;
            System.out.println(third);
            n -= 1;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // 🔹 Added input
        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();  // 🔹 Take user input
        
        // Print Fibonacci sequence using recursive method
        System.out.println("Recursive Fibonacci:");
        for (int i = 0; i < n; i++) {
            System.out.println(recursiveFibonacci(i));
        }

        // Print Fibonacci sequence using non-recursive method
        System.out.println("\nNon-Recursive Fibonacci:");
        nonRecursiveFibonacci(n);   

        sc.close(); // 🔹 Close scanner
    }
}
