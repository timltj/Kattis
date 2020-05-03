/*
 * CS2040 Data Structures and Algorithms
 * Lab 5: Problem A Assigning Workstations
 *  
 * This program uses 2 priority queues to simulate the researchers data and the 
 * next availiable time for each work station, returning the total number of unlockings saved
 *
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class WorkStations {
	public static void main(String[] args) throws Exception {
		/* fast io */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		// get the number of researchers and the minutes of inactivity
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// maintain 2 priority queues for researchers data and workstations next available time
		PriorityQueue<Researcher> pqR = new PriorityQueue<>();
		PriorityQueue<Integer> pqWS = new PriorityQueue<>();

		for (int x = 0; x < n; x++) {
			st = new StringTokenizer(br.readLine());
			pqR.add(new Researcher(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		// keep track of savings
		int count = 0;
		int doneTime = 0;
		int curVacancyTime = 0;
		while (!pqR.isEmpty()) {
			doneTime = pqR.peek().arrTime + pqR.peek().activity-1; // time when researcher is done
			for (int x = 0; x < pqWS.size()+1; x++) {
				if (pqWS.isEmpty()) { // first researcher
					pqWS.add(doneTime);
					pqR.poll();
					break;
				}
				else { 
					curVacancyTime = pqWS.poll();
					// next researcher arrives when workstations are in use, add new workstation
					if (pqR.peek().arrTime <= curVacancyTime) { 
						pqWS.add(doneTime);
						pqWS.add(curVacancyTime);
						pqR.poll();
						break;
					}
					// next researcher arrives when at least 1 workstation is vacant
					if (pqR.peek().arrTime > curVacancyTime && pqR.peek().arrTime <= curVacancyTime+m+1) {
						pqWS.add(doneTime);
						pqR.poll();
						count++;						
						break;
					}
				}
			}
		}
		
		pw.write(Integer.toString(count));
		br.close();
		pw.close();	
	}
}

class Researcher implements Comparable<Researcher> {
	// attributes
	protected final int arrTime;
	protected final int activity;

	// constructor 
	public Researcher(int arrTime, int activity) {
		this.arrTime = arrTime;
		this.activity = activity;
	}

	/* overrides */
	@Override
	public int compareTo(Researcher another) {
		if (this.arrTime < another.arrTime) {
			return -1;
		}
		else if (this.arrTime > another.arrTime) {
			return 1;
		}
		return 0;
	}
}


