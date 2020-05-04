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

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Coconut {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        int s = sc.nextInt();
        int n = sc.nextInt();

        // queue to store player hands
        Queue<Hand> queue = new LinkedList<>();
        
        for (int i = 1; i < n + 1; i++) {
            queue.add(new Hand(i));
            queue.add(new Hand(i));
        }

        /* game simulation */
        boolean split = false; // to check for spliting into fists
        while (queue.size() > 1) {
            int count = s;
                while(count > 1) { // continue until last person
                    Hand hand = queue.peek();
                    if (hand.state == 1) {
                        queue.add(queue.remove());
                        queue.add(queue.remove());
                    } else {
                        queue.add(queue.remove());
                    }
                   
                    if (split) { // split, the next hand will have same state as previous
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
                } else if (hand.state == 2) {
                    hand.updateState();
                    queue.add(queue.remove());
                } else {
                    queue.remove();
                }
        }

        System.out.println(queue.peek().id);
    }
}

class Hand {
    // attributes
    protected final int id;
    protected int state;

    // constructor
    public Hand(int id) {
        this.id = id;
        this.state = 1;
    }
        
    public void updateState() {
        this.state++;
    }
}
