package PracticalDAA;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    int freq;
    char symbol;
    Node left, right;
    String huff = "";
    
    Node(int freq, char symbol, Node left, Node right) {
        this.freq = freq;
        this.symbol = symbol;
        this.left = left;
        this.right = right;
    }
}

public class HuffmanTree {
    
    static void printNodes(Node node, String val) {
        String newVal = val + node.huff;
        
        if (node.left != null) printNodes(node.left, newVal);
        if (node.right != null) printNodes(node.right, newVal);
        
        if (node.left == null && node.right == null) {
            System.out.println(node.symbol + " -> " + newVal);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        char[] chars = new char[n];
        int[] freq = new int[n];

        System.out.println("Enter the characters:");
        for (int i = 0; i < n; i++) {
            chars[i] = sc.next().charAt(0);
        }

        System.out.println("Enter the frequencies:");
        for (int i = 0; i < n; i++) {
            freq[i] = sc.nextInt();
        }

        PriorityQueue<Node> nodes = new PriorityQueue<>((a, b) -> a.freq - b.freq);
        
        for (int i = 0; i < chars.length; i++) {
            nodes.add(new Node(freq[i], chars[i], null, null));
        }
        
        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            
            left.huff = "0";
            right.huff = "1";
            
            Node newNode = new Node(left.freq + right.freq, '\0', left, right);
            nodes.add(newNode);
        }
        
        printNodes(nodes.peek(), "");

        sc.close();
    }
}
