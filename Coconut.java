/*
 * CS2040 Data Structures and Algorithms
 * Lab 3: Problem A Coconut Splat
 *  
 * This program takes as integer input, the number of syllabes and number of players,
 * for the coconut splat game, and prints the last remaining player
 *
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.text.*;
import java.util.*;

public class Coconut {
	public static void main(String[] args) {

        	Scanner sc = new Scanner(System.in);
		
        	int s = sc.nextInt();
        	int n = sc.nextInt();

		// create a queue to store the hands
        	Queue<Hand> queue = new LinkedList<>();
		
        	for (int i = 1; i < n + 1; i++) {
            		queue.add(new Hand(i));
            		queue.add(new Hand(i));
        	}

		/* game simulation */
        	boolean split = false; // to check for spliting into fists
        	while (queue.size() > 1) {
	        	int count = s;

	    		// continue until last person
           		while(count > 1) {
				Hand hand = queue.peek();

                		if (hand.state == 1) {
		    			queue.add(queue.remove());
                			queue.add(queue.remove());
		                } 
				else {
                    			queue.add(queue.remove());
                		}

                		// if split, the next hand will have same state as previous 
				if (split) {
                    			queue.peek().updateState();
                    			split = false;
                		}
	
                		count--;
            		}

            		// last person
           		Hand hand = queue.peek();
		        if (hand.state == 1) {
                		hand.updateState();
		                split = true;
            		}
			else if (hand.state == 2) {
		                hand.updateState();
                		queue.add(queue.remove());
		        } 
			else {
                		queue.remove();
            		}
        	}

        	System.out.println(queue.peek().id);
    	}
}

class Hand {
	// attributes
    	int id;
    	int state;

	// constructor
    	public Hand(int id) {
        	this.id = id;
	        this.state = 1;
    	}
	
	// update the state of hand
    	public void updateState() {
        	this.state++;
    	}
}
