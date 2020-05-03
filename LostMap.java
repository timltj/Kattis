/*
 * CS2040 Data Structures and Algorithms
 * Lab 9: Problem A Lost Map
 *  
 * This program reads in an adjacency matrix of dimensions n by n,
 * representing the weights of edges between 2 adjacent vertices,
 * and outputs the connected edges of the MST formed
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class LostMap {
    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // get n
        int n = Integer.parseInt(br.readLine());

        // edgeList to store IntegerTriples
        List<IntegerTriple> edgeList = new ArrayList<IntegerTriple>();

        /* input loop */
        for (int row = 0; row < n; row++) {
            String[] strArr = br.readLine().split(" ");         
            for (int col = 0; col < n; col++) {
                edgeList.add(new IntegerTriple(Integer.parseInt(strArr[col]), row, col));
            }
        }

        // sort edgeList
        Collections.sort(edgeList);

        // create UFDS
        UFDS ufds = new UFDS(n);

        /* main loop */
        for (IntegerTriple edge : edgeList) {
            if (!ufds.isSameSet(edge.v1, edge.v2)) {
                ufds.unionSet(edge.v1, edge.v2);
                pw.println(Integer.toString(edge.v1 +1) + " " + Integer.toString(edge.v2 + 1));
            }
        }

        br.close();
        pw.close();
    }
}

class IntegerTriple implements Comparable<IntegerTriple> {
    // attributes
    protected final int weight;
    protected final int v1;
    protected final int v2;

    // constructor
    public IntegerTriple(int weight, int v1, int v2) {
        this.weight = weight;
        this.v1 = v1;
        this.v2 = v2;
    }

    /* overrides */
    @Override
    public int compareTo(IntegerTriple another) {
        if (this.weight != another.weight) {
            return this.weight - another.weight;
        } else if (this.v1 != another.v1) {
            return this.v1 - another.v1;
        } else {
            return this.v2 - another.v2;
        }
    }
}

class UFDS {
    // attributes
    protected int[] p;
    protected int[] rank;
    protected int[] setSize;
    protected int numSets;

    // constructor
    public UFDS(int N) {
        this.p = new int[N];
        this.rank = new int[N];
        this.setSize = new int[N];
        this.numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            setSize[1] = 1;
        }
    }

    // findSet
    public int findSet(int i) {
        if (p[i] == i) {
            return i;
        } else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    // isSameSet
    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    // unionSet
    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            numSets--;
            int x = findSet(i);
            int y = findSet(j);
            // rank is used to keep tree short
            if (rank[x] > rank[y]) {
                p[y] = x;
                setSize[x] = setSize[x] + setSize[y];
            } else {
                p[x] = y;
                setSize[y] = setSize[x] + setSize[y];
                if (rank[x] == rank[y]) {
                    rank[y] = rank[y] + 1;
                }
            }
        }
    }
}
