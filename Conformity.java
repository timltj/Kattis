/*
 * CS2040 Data Structures and Algorithms
 * Lab 4: Problem A Conformity
 *  
 * This program uses a hashmap containing hashsets of data
 * and prints out the frequency of the most repeated set
 *
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.text.*;
import java.util.*;
import java.io.*;

public class Conformity {
	public static void main(String[] args) throws Exception {
		/* fast io */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());
		
		/* hashmap to map the sets to their frequencies */
		Map<Set, Integer> map = new HashMap<>();

		// main loop to map set data
		for (int x = 0; x < n; x++) {
			// create hashset to store data of each frosh
			Set<Integer> set = new HashSet<>();
		
			// add each frosh data to set
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 5; i++) {
				set.add(Integer.parseInt(st.nextToken()));
			}

			if (map.containsKey(set)) { // add same set with +1 frequency
				map.put(set, map.get(set)+1);
			}
			else {
				map.put(set, 1);
			}
			
		}

		// compute the frequency of highest repeated set
		int max = Collections.max(map.values());
		int count = Collections.frequency(map.values(), max);
		
		pw.write(Integer.toString(max*count));

		br.close();
		pw.close();
	}
}
