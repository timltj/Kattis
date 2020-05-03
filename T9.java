/*
 * CS2040 Data Structures and Algorithms
 * Lab 1: Problem A T9 Spelling
 *
 * This program takes as integer input, the number of cases,
 * each case being a string of characters a-z and ' ',
 * and returns a string, representing the T9 key presses for each case
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.text.*;
import java.util.*;

public class T9 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		// create array of size 26 to store a-z
		String arr[] = {"a2", "b22", "c222", "d3", "e33", "f333", "g4", "h44", "i444", "j5", "k55", "l555", "m6", "n66", "o666", "p7", "q77", "r777", "s7777", "t8", "u88", "v888", "w9", "x99", "y999", "z9999"};

		// get number of cases
		int cases = sc.nextInt();
		sc.nextLine(); // consume next line

		// loop through the cases
		for (int x = 0; x < cases; x++) {
			String curCase = sc.nextLine();
			String output = "";
			String prevOutput = " ";
			// loop through entire word
			for (int y = 0; y < curCase.length(); y++) {
				// compare with array
				for (int k = 0; k < arr.length; k++) {
					if (curCase.charAt(y) == arr[k].charAt(0)) { // compare to check char 
						if (y > 0 && arr[k].charAt(1) == prevOutput.charAt(0)) { // add a space if the prev char was on the same button	
							output += " " + arr[k].substring(1);
						}
						else {
							output += arr[k].substring(1);
						}
						prevOutput = arr[k].substring(1);
						break;
					}
					if (curCase.charAt(y) == ' ') { // if current char is space
						if (y > 0 && prevOutput == "0") { // add a space if the prev char was on the same button	
							output += " 0";
						}
						else {
							output += "0";
						}
						prevOutput = "0";
						break;
					}
				}
			}
			System.out.println("Case #" + (x+1) + ": " + output);
		}

	}
}

