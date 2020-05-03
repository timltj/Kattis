/*
 * CS2040 Data Structures and Algorithms
 * Lab 4A: Problem A 10 Kinds of People
 *  
 * This program reads in a grid of dimensions r by c,
 * labels the grid by islands, then
 * reads n queries to generate the output 
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class KindsOfPeople {
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

        // queue to keep track of blocks
        Queue<Block> queue = new LinkedList<Block>();
        
        // 2D array grid
        Block[][] grid = new Block[r][c];

        /* input loop */
        int assigner = 0;
        for (int row = 0; row < r; row++) {
            char[] charArr = br.readLine().toCharArray();
            for (int col = 0; col < c; col++) {
                grid[row][col] = new Block(Integer.parseInt(String.valueOf(charArr[col])), assigner, row, col);
                assigner++;
            }
        }   

        /* loop to label islands */
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                queue.add(grid[row][col]);
                visitArr[row][col] = true;
                BFS(queue, grid, visitArr);
            }
        }        

        // get n
        int n = Integer.parseInt(br.readLine());   

        /* main loop */
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;            
            int c2 = Integer.parseInt(st.nextToken()) - 1;
  
            Block curCell = grid[r1][c1];      
            Block desCell = grid[r2][c2];
                        
            if (curCell.value == 0 
                    && desCell.value == 0 
                    && curCell.island == desCell.island) { // binary                     
                pw.println("binary");
            } else if (curCell.value == 1 
                    && desCell.value == 1 
                    && curCell.island == desCell.island)  { // decimal
                pw.println("decimal");
            } else { // neither
                pw.println("neither");
            }
        }    

        br.close();
        pw.close();
    }

    public static void BFS(Queue<Block> queue, Block[][] grid, boolean[][] visitArr) {
        while (!queue.isEmpty()) {
            Block cellToCheck = queue.poll();

            int row = cellToCheck.row;
            int col = cellToCheck.col;
            int value = cellToCheck.value;
            int island = cellToCheck.island;

            if ((row + 1 < grid.length)
                    && (grid[row + 1][col].value == value)
                    && unvisited(visitArr, row + 1, col)) { // down
                island = grid[row + 1][col].island = Math.min(island, grid[row + 1][col].island);
                queue.add(grid[row + 1][col]);
                visitArr[row + 1][col] = true;
                    }

            if ((row - 1 >= 0) 
                    && (grid[row - 1][col].value == value)
                    && unvisited(visitArr, row - 1, col)) { // up
                island = grid[row - 1][col].island = Math.min(island, grid[row - 1][col].island);
                queue.add(grid[row - 1][col]);
                visitArr[row - 1][col] = true;          
                    }

            if ((col + 1 < grid[0].length) 
                    && (grid[row][col + 1].value == value)
                    && unvisited(visitArr, row, col + 1)) { // right
                island = grid[row][col + 1].island = Math.min(island, grid[row][col + 1].island);
                queue.add(grid[row][col + 1]);
                visitArr[row][col + 1] = true;                
                    }


            if ((col - 1 >= 0) 
                    && (grid[row][col - 1].value == value)
                    && unvisited(visitArr, row, col - 1)) { // left
                island = grid[row][col - 1].island = Math.min(island, grid[row][col - 1].island);
                queue.add(grid[row][col - 1]);
                visitArr[row][col - 1] = true;                
                    }            
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

class Block {
    // attributes
    protected final int value;
    protected int island; // keep track of which island this block is on
    protected final int row;
    protected final int col;

    // constructor
    public Block(int value, int island, int row, int col) {
        this.value = value;
        this.island = island;
        this.row = row;
        this.col = col;
    }
}
