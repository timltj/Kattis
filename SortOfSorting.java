/*
 * CS2040 Data Structures and Algorithms
 * Lab 2: Problem A Sort of Sorting
 * 
 * This program takes as integer input, the number of names,
 * each name being a string of 2-20 characters, 
 * and prints out the names sorted by their first 2 characters
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.text.*;
import java.util.*;

public class SortOfSorting {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		while (n != 0) {				
			// store names
			String[] arr = new String[n];
			for (int x = 0; x < n; x++) {
				arr[x] = sc.next();
			}

			bubbleSort(arr);
			
			for (int y = 0; y < n; y++) {
				System.out.println(arr[y]);
			}

			// update n
			n = sc.nextInt();
		}
	}

	// bubble sort algorithm
	public static void bubbleSort(String[] a) {
		for (int i = 1; i < a.length; i++) {
			boolean isSorted = true;
			for (int j = 0; j < a.length - i; j++) {
				// sort by first char
				if ((int) a[j].charAt(0) > (int) a[j+1].charAt(0)) { 
					String temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
					isSorted = false;
				}
				// sort by second char
				else if ((int) a[j].charAt(0) == (int) a[j+1].charAt(0)) { 
					if ((int) a[j].charAt(1) > (int) a[j+1].charAt(1)) { 
						String temp = a[j];
						a[j] = a[j+1];
						a[j+1] = temp;
						isSorted = false;
					}

				}
			}
			if (isSorted) return;
		}
	}

}
