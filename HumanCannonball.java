/*
 * CS2040 Data Structures and Algorithms
 * Lab 10: Problem A Human Cannoball Run
 *  
 * This program reads in the starting and destination coordinates,
 * the number of cannons and the coordinates of each cannon, 
 * and uses the modified Dijkstra's algorithm to output the time
 * travelled for the shortest path
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class HumanCannonball {
    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // current location
        StringTokenizer st = new StringTokenizer(br.readLine());
        double curX = Double.parseDouble(st.nextToken());
        double curY = Double.parseDouble(st.nextToken());

        // destination
        st = new StringTokenizer(br.readLine());
        double desX = Double.parseDouble(st.nextToken());
        double desY = Double.parseDouble(st.nextToken());

        // num cannons
        int n = Integer.parseInt(br.readLine());

        // vertex list
        Vertex[] verList = new Vertex[n+2];
        verList[0] = new Vertex("Other", curX, curY);
        verList[1] = new Vertex("Other", desX, desY);

        // edge list
        List<IntegerTriple> edgeList = new ArrayList<IntegerTriple>();

        /* input loop */
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double xCor = Double.parseDouble(st.nextToken());
            double yCor = Double.parseDouble(st.nextToken());
            verList[i+2] = new Vertex("Cannon", xCor, yCor);
        }

        /* graph modelling */
        for (int i = 0; i < verList.length; i++) {
            for (int j = 0; j < verList.length; j++) {
                if (i != j) { // same vertex
                    double dist = Math.sqrt(Math.pow(verList[i].x - verList[j].x, 2) + Math.pow(verList[i].y - verList[j].y, 2));
                    double time1 = dist / 5;
                    double time2 = Integer.MAX_VALUE;
                    if (verList[i].type.equals("Cannon")) { // vertex is cannon
                        time2 = 2 + (Math.abs(dist - 50) / 5);
                    }
                    edgeList.add(new IntegerTriple(i, j, Math.min(time1, time2)));
                }
            }
        }      

        /* modified Dijkstra's */
        // distance array
        double[] distArr = new double[n+2];
        Arrays.fill(distArr, Double.MAX_VALUE);
        distArr[0] = 0;

        // priority queue of IntegerPairs
        PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>();
        pq.add(new IntegerPair(0, 0));

        while (!pq.isEmpty()) {
            IntegerPair top = pq.poll();
            if (distArr[top.vertex] == top.distEst) {
                for (IntegerTriple i : edgeList) {
                    if (i.u == top.vertex && (distArr[top.vertex] + i.w < distArr[i.v])) { 
                        distArr[i.v] = distArr[top.vertex] + i.w;
                        pq.add(new IntegerPair(distArr[i.v], i.v));
                    }
                }
            }
        }   

        // output 
        pw.write(Double.toString(distArr[1]));

        br.close();
        pw.close();
    }
}

class Vertex {
    // attributes
    protected final String type;
    protected final double x;
    protected final double y;   

    // constructor
    Vertex(String type, double x, double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    // attributes
    protected final double distEst;
    protected final int vertex;

    // constructor
    IntegerPair(double distEst, int vertex) {
        this.distEst = distEst;
        this.vertex = vertex;
    }
   
    /* overrides */
    @Override
    public int compareTo(IntegerPair another) {
        if (this.distEst != another.distEst) {
            return (int) (this.distEst - another.distEst);
        } else {
            return this.vertex - another.vertex;
        }
    }
}

class IntegerTriple { 
    // attributes
    protected final int u;
    protected final int v;
    protected final double w; // travel time

    // constructor
    IntegerTriple(int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
