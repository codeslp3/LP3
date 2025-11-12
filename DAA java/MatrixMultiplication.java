package PracticalDAA;

import java.util.*;


public class MatrixMultiplication {
    static double[][] multiply(double[][] A, double[][] B) {
        int n = A.length, m = B[0].length, p = A[0].length;
        double[][] R = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = 0; k < p; k++)
                    R[i][j] += A[i][k] * B[k][j];
        return R;
    }

    static double[][] multiplyRowThreads(double[][] A, double[][] B) throws InterruptedException {
        int n = A.length, m = B[0].length, p = A[0].length;
        double[][] R = new double[n][m];
        Thread[] t = new Thread[n];
        for (int i = 0; i < n; i++) {
            int row = i;
            t[i] = new Thread(() -> {
                for (int j = 0; j < m; j++)
                    for (int k = 0; k < p; k++)
                        R[row][j] += A[row][k] * B[k][j];
            });
            t[i].start();
        }
        for (Thread th : t) th.join();
        return R;
    }

    static double[][] multiplyCellThreads(double[][] A, double[][] B) throws InterruptedException {
        int n = A.length, m = B[0].length, p = A[0].length;
        double[][] R = new double[n][m];
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int r = i, c = j;
                Thread t = new Thread(() -> {
                    for (int k = 0; k < p; k++)
                        R[r][c] += A[r][k] * B[k][c];
                });
                list.add(t);
            }
        for (Thread t : list) t.start();
        for (Thread t : list) t.join();
        return R;
    }

    public static void main(String[] a) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("A rows cols: ");
        int r1 = sc.nextInt(), c1 = sc.nextInt();
        double[][] A = new double[r1][c1];
        System.out.println("Enter A:");
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c1; j++) A[i][j] = sc.nextDouble();

        System.out.print("B rows cols: ");
        int r2 = sc.nextInt(), c2 = sc.nextInt();
        double[][] B = new double[r2][c2];
        System.out.println("Enter B:");
        for (int i = 0; i < r2; i++)
            for (int j = 0; j < c2; j++) B[i][j] = sc.nextDouble();

        if (c1 != r2) { System.out.println("Incompatible!"); return; }

        long s = System.nanoTime(); var R1 = multiply(A, B);
        double t1 = (System.nanoTime() - s) / 1e9;

        s = System.nanoTime(); multiplyRowThreads(A, B);
        double t2 = (System.nanoTime() - s) / 1e9;

        s = System.nanoTime(); multiplyCellThreads(A, B);
        double t3 = (System.nanoTime() - s) / 1e9;

        System.out.printf("Sequential: %.4fs\nRow threads: %.4fs\nCell threads: %.4fs\n", t1, t2, t3);
        for (double[] r : R1) { for (double v : r) System.out.printf("%.2f ", v); System.out.println(); }
    }
}
