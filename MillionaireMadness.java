/*
 * CS2040 Data Structures and Algorithms
 * Lab 4B: Problem A Millionaire Madness
 *  
 * This program reads in a grid of dimensions m by n,
 * uses the prim's algorithm to find the MST and outputs 
 * the minimum height of the ladder needed to traverse between 
 * the 1st and last vertices of the MST
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class MillionaireMadness {
    public static void main(String[] args) throws Exception {
         /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // read first line
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        
        // priority queue to track current edges
        PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>();    

        // boolean list to track taken vertices
        List<Boolean> taken = new ArrayList<Boolean>();

        // 2D array to store grid
        int[][] grid = new int[m][n];

        /* input loop */      
        for (int row = 0; row < m; row++) {
            String[] strArr = br.readLine().split(" ");
            for (int col = 0; col < n; col++) {
                grid[row][col] = Integer.parseInt(strArr[col]);
                taken.add(false);                
            }
        }

        addToPQ(pq, grid, 0);
        taken.set(0, true);

        /* main loop */
        int min = 0;
        while (pq.size() != 0) {
            IntegerPair edge = pq.poll();
            if (untaken(taken, edge.vertex) && untaken(taken, m*n-1)) {
                min = Math.max(edge.weight, min);
                taken.set(edge.vertex, true);
                addToPQ(pq, grid, edge.vertex);
            }
        }
        
        pw.println(Integer.toString(min));

        br.close();
        pw.close();
    }

    public static boolean untaken(List<Boolean> taken, int vertex) {
        if (taken.get(vertex) == true) {
            return false;
        } else {
            return true;
        }
    }    

    public static void addToPQ(PriorityQueue<IntegerPair> pq, int[][] grid, int vertex) {
        int row = vertex / grid[0].length;
        int col = vertex % grid[0].length;

        if (row+1 < grid.length) { // down
            pq.add(new IntegerPair(grid[row+1][col] - grid[row][col], vertex+grid[0].length));
        }
        if (row-1 >= 0) { // up
            pq.add(new IntegerPair(grid[row-1][col] - grid[row][col], vertex-grid[0].length));
        }
        if (col+1 < grid[0].length) { // right
            pq.add(new IntegerPair(grid[row][col+1] - grid[row][col], vertex+1));
        }
        if (col-1 >= 0) { // left
            pq.add(new IntegerPair(grid[row][col-1] - grid[row][col], vertex-1));
        }          
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    // attributes
    protected final int weight;
    protected final int vertex;

    // constructor
    public IntegerPair(int weight, int vertex) {
        this.weight = weight;
        this.vertex = vertex;        
    }

    /* overrides */
    @Override
    public int compareTo(IntegerPair another) {
        if (this.weight != another.weight) {
            return this.weight - another.weight;
        } else {
            return this.vertex - another.vertex;
        }
    }
}
