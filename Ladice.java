/*
 * CS2040 Data Structures and Algorithms
 * Lab 3A: Problem A Ladice
 *  
 * This program reads N items and tries to store them in L drawers,
 * printing "LADICA" if possible and "SMECE" otherwise
 *  
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class Ladice {
    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // read first line
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        // simulate drawers as each node in UFDS
        UnionFind uf = new UnionFind(l+1);

        /* main loop */
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // union drawer A and B
            uf.unionSet(a, b);

            if (uf.exceedSize(a)) { // unable to find drawer
                pw.println("SMECE");
            } else { 
                pw.println("LADICA");
            }                        
        }

        br.close();
        pw.close();
    }   
}

class UnionFind {
    // attributes
    protected int[] p;
    protected int[] rank;
    protected int[] size;
    protected int[] maxSize;

    // constructor
    public UnionFind(int N) {
        this.p = new int[N];
        this.rank = new int[N];
        this.size = new int[N];
        this.maxSize = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
            maxSize[i] = 1;
        }
    }

    // findSet method
    public int findSet(int i) {
        if (p[i] == i) {
            return i;
        } else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    // isSameSet method
    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    // unionSet method
    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) {
                p[y] = x;
                size[x] += size[y];               
                maxSize[x] += maxSize[y];
            } else {
                p[x] = y;
                size[y] += size[x];               
                maxSize[y] += maxSize[x];
                if (rank[x] == rank[y]) {
                    rank[y] = rank[y]+1;
                }
            }
        }
    }

    // exceedSize method to check if set size can be increased
    public boolean exceedSize(int i) {
        int x = findSet(i);
        if (++size[x] > maxSize[x]) {
            size[x]--;
            return true;
        } else {      
            return false;
        }
    }
}
