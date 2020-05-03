/*
 * CS2040 Data Structures and Algorithms
 * Lab 8: Problem A Islands
 *  
 * This program reads in a grid of dimensions r by c,
 * and outputs the minimum number of islands
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class Islands3 {
    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        // read first line
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // boolean array to track visitation
        boolean[][] visitArr = new boolean[r][c];
        
        // 2D array grid
        char[][] grid = new char[r][c];

        /* input loop */
        for (int row = 0; row < r; row++) {
            char[] charArr = br.readLine().toCharArray();
            for (int col = 0; col < c; col++) {
                grid[row][col] = charArr[col];
            }
        }

        int count = 0;

        /* main loop */
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                char curCell = grid[row][col];
                if (curCell == 'L' && unvisited(visitArr, row, col)) {
                    count++;
                    DFS(row, col, grid, visitArr);
                }
            }
        }
        
        // output
        pw.write(Integer.toString(count)); 

        br.close();
        pw.close();
    }

    public static void DFS(int row, int col, char[][] grid, boolean[][] visitArr) {
        visitArr[row][col] = true; // mark visited
        
        if ((row+1 < grid.length) 
                && (grid[row+1][col] == 'C' || grid[row+1][col] == 'L') 
                && unvisited(visitArr, row+1, col)) { // down
            DFS(row+1, col, grid, visitArr);
        }
        if ((row-1 >= 0) 
                && (grid[row-1][col] == 'C' || grid[row-1][col] == 'L') 
                && unvisited(visitArr, row-1, col)) { // up
            DFS(row-1, col, grid, visitArr);
        }
        if ((col+1 < grid[0].length) 
                && (grid[row][col+1] == 'C' || grid[row][col+1] == 'L') 
                && unvisited(visitArr, row, col+1)) { // right
            DFS(row, col+1, grid, visitArr);
        }
        if ((col-1 >= 0) 
                && (grid[row][col-1] == 'C' || grid[row][col-1] == 'L') 
                && unvisited(visitArr, row, col-1)) { // left
            DFS(row, col-1, grid, visitArr);
        }  
    }

    public static boolean unvisited(boolean[][] visitArr, int row, int col) {
        if (visitArr[row][col] == true) {
            return false;            
        } else {
            return true;
        }
    }
}
