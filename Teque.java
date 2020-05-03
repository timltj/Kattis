/*
 * CS2040 Data Structures and Algorithms
 * Lab 2B: Problem A Teque
 *  
 * This program uses hash maps to simulate 
 * the functions of a triple-ended queue
 *   
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class Teque {

    // create 2 hash maps to simulate the front and back of a queue
    private static HashMap<Integer, Integer> front = new HashMap<>();
    private static HashMap<Integer, Integer> back = new HashMap<>();
    private static int frontMin = 0;
    private static int frontMax = 0;
    private static int backMin = 0;
    private static int backMax = 0;

    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        /* main loop to read all user inputs */
        for (int i = 0; i < n; i++) {

            // split each input into command and value
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int x = Integer.parseInt(st.nextToken());

            // execute command
            switch(cmd) {
                case "push_front":
                    push_front(x);
                    break;
                case "push_back":
                    push_back(x);
                    break;
                case "push_middle":
                    push_middle(x);
                    break;
                case "get":
                    pw.write(Integer.toString(get(x)) + '\n');
                    break;
                default:
            }       
        }
        pw.flush();
    }

    /* client methods */           
    private static void push_front(int x) { // push_front 
        frontMin--;
        front.put(frontMin, x);
        balance();
    }
   
    private static void push_back(int x) { // push_back
        back.put(backMax, x);
        backMax++;
        balance();
    }

    private static void push_middle(int x) { // push_middle
        if (front.size() == back.size()) {
            backMin++;
            back.put(backMin, x);
        } else {
            front.put(frontMax, back.get(backMin));
            frontMax++;
            back.put(backMin, x);
        }
    }
    
    private static int get(int index) { // get
        if (index < frontMax - frontMin) {
            return front.get(frontMin + index);
        }
        index -= (frontMax - frontMin);
        return back.get(backMin + index);
    }

    // balance size of front and back queues
    private static void balance() {
        if (front.size() > back.size()) {
            frontMax--;
            backMin--;
            back.put(backMin, front.get(frontMax));
            front.remove(frontMax);
        } else {
            front.put(frontMax, back.get(backMin));
            frontMax++;
            backMin++;
            back.remove(backMin--);
        }
    }
}
