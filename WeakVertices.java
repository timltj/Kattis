/*
 * CS2040 Data Structures and Algorithms
 * Lab 7: Problem A Weak Vertices
 *  
 * This program reads in the adjacency matrix of graphs and
 * outputs the weak vertices in these graphs
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class WeakVertices {
    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // list to store output
        ArrayList<Integer> list = new ArrayList<Integer>();

        /* main loop */
        while (true) {
            int n = Integer.parseInt(br.readLine());
           
            if (n == -1) { // termination
                break;
            }

            // 2D array to store adjacency matrix
            int[][] mtx = new int[n][n];

            // boolean array to mark triangle vertex
            boolean[] marker = new boolean[n];

            // input
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    mtx[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // check all permutations
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    for (int z = 0; z < n; z++) {
                        if (x != y && x != z && y != z) { // check for distinct
                            if (mtx[x][y] == 1 && mtx[x][z] == 1 && mtx[y][z] == 1) { // update marker
                                marker[x] = true;
                                marker[y] = true;
                                marker[z] = true;
                            }
                        }
                    }
                }
            }

            // store non-triangle vertices
            for (int i = 0; i < marker.length; i++) {
                if (marker[i] == false) {
                    list.add(i);
                }
            }

            list.add(null); // represents new graph
        }
        
        /* output */
        for (Integer value : list) {
            if (value != null) {
                pw.write(Integer.toString(value));
                pw.write(" ");
            } else {
                pw.println();
            }
        }
        
        br.close();
        pw.close();
    }
}
