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

import java.util.Scanner;

public class SortOfSorting {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        while (n != 0) {                           
            String[] arr = new String[n]; // store names
            for (int i = 0; i < n; i++) {
                arr[i] = sc.next();
            }

            bubbleSort(arr);
            
            for (int i = 0; i < n; i++) {
                System.out.println(arr[i]);
            }
           
            n = sc.nextInt(); // update n
        }
    }

    /* bubble sort algorithm */
    public static void bubbleSort(String[] a) {
        for (int i = 1; i < a.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < a.length - i; j++) {                
                if (a[j].charAt(0) > a[j+1].charAt(0)) { // sort by first char
                    String temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    isSorted = false;
                } else if (a[j].charAt(0) == a[j+1].charAt(0)) { // sort by second char
                    if (a[j].charAt(1) > a[j+1].charAt(1)) { 
                        String temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                        isSorted = false;
                    }
                }
            }
            if (isSorted) { 
                return;
            }
        }
    }
}
