package PracticalDAA;
import java.util.Scanner;


public class NQueens {
    
    // Print the board
    public static void printBoard(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // Check if position is safe
    public static boolean isSafe(String[][] board, int row, int col, int n) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col].equals("Q")) {
                return false;
            }
        }
        
        // Check upper-left diagonal
        int i = row - 1;
        int j = col - 1;
        while (i >= 0 && j >= 0) {
            if (board[i][j].equals("Q")) {
                return false;
            }
            i--;
            j--;
        }
        
        // Check upper-right diagonal
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < n) {
            if (board[i][j].equals("Q")) {
                return false;
            }
            i--;
            j++;
        }
        
        return true;
    }
    
    // Solve N-Queens
    public static boolean solveNQueens(String[][] board, int row, int n) {
        if (row == n) {
            printBoard(board);
            return true;
        }
        
        boolean res = false;
        
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = "Q";
                res = solveNQueens(board, row + 1, n) || res;
                board[row][col] = "0"; // backtrack
            }
        }
        
        return res;
    }
    
    // Main driver
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the board (N): ");
        int n = sc.nextInt();
        
        // Initialize board
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = "0";
            }
        }
        
        if (!solveNQueens(board, 0, n)) {
            System.out.println("No solution exists.");
        }
        
        sc.close();
    }
}