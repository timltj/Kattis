/*
 * CS2040 Data Structures and Algorithms
 * Lab 1A: Problem A Train Passengers
 *
 * This program takes as 2 integer inputs, the capacity of a train, C, and the number of stations, n.
 * The program then takes n lines of 3 integer inputs, the number of passengers exiting, entering and staying,
 * and returns a string "possible" or "impossible" to signify if such an arrangement is possible or not.
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.text.*;
import java.util.*;

public class TrainPassengers {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int C = sc.nextInt();
		int n = sc.nextInt();
		sc.nextLine();

		// record validity
		boolean valid = true;

		// record passenger count
		int count = 0;	

		for (int x = 0; x < n; x++) {
			int exit = sc.nextInt();
			int enter = sc.nextInt();
			int stay = sc.nextInt();

			count -= exit;
			count += enter;

			/* check validity */
			if (count < 0 || count > C || count - stay < 0) {
				valid = false;
			}
			if (stay > 0 && stay + count <= C) {
				valid = false;
			}
			if ((x == n-1 && count != 0) || (x == n-1 && stay != 0) || (x == n-1 && enter != 0)) {
				valid = false;
			}
		}

		// print
		if (valid == true) {
			System.out.println("possible");
		}
		else {
			System.out.println("impossible");
		}
	}
}
