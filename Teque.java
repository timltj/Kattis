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

import java.text.*;
import java.util.*;
import java.io.*;

public class Teque {

	// create 2 hash maps to simulate the front and back of a queue
    	private static HashMap<Integer, Integer> front = new HashMap<>();
    	private static HashMap<Integer, Integer> back = new HashMap<>();
    	static int frontMin = 0;
    	static int frontMax = 0;
    	static int backMin = 0;
    	static int backMax = 0;

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
                	}  		
        	}
        	pw.flush();
    	}

	/* client methods */
	
    	// push_front 
    	private static void push_front(int x) {
        	frontMin -= 1;
        	front.put(frontMin, x);
        	balance();
    	}

    	// push_back
    	private static void push_back(int x) {
        	back.put(backMax, x);
       		backMax += 1;
        	balance();
    	}

    	// push_middle 
    	private static void push_middle(int x) {
        	if (front.size() == back.size()) {
            		backMin -= 1;
            		back.put(backMin, x);
        	}
        	else {
            		front.put(frontMax, back.get(backMin));
           	 	frontMax += 1;
            		back.put(backMin, x);
        	}
    	}

    	// get
    	private static int get(int index) {
        	if (index < frontMax - frontMin) {
           		return front.get(frontMin + index);
		}
        	index -= (frontMax - frontMin);
        	return back.get(backMin + index);
    	}

    	// balance size of front and back queues
    	private static void balance() {
        	if (front.size() > back.size()) {
            		frontMax -= 1;
            		backMin -= 1;
            		back.put(backMin, front.get(frontMax));
            		front.remove(frontMax);
         	}
        	if (back.size() > front.size()+1) {
            		front.put(frontMax, back.get(backMin));
            		frontMax += 1;
            		backMin += 1;
            		back.remove(backMin-1);
        	}
    	}
}
