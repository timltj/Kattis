/*
 * CS2040 Data Structures and Algorithms
 * Lab 2A: Problem A Join Strings
 *  
 * This program uses fast io methods to take in a stream of
 * user input of strings, concatenate them, and output the final string
 *
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.text.*;
import java.util.*;
import java.io.*;

public class JoinStrings {
    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

            int n = Integer.parseInt(br.readLine());

        // array to store strings
        String[] arr = new String[n];
        for (int x = 0; x < n; x++) {
                arr[x] = br.readLine();
        }

        int[] idxMap = new int[n+1];
        int[] cache = new int[n+1];

        int a = 1;
        int b = 1;
        int x = a;

        /* output */
        if (n == 1) { // only 1 word
            pw.write(arr[0]);
        } else {
            for (int i = 0; i < n-1; i++) {
                // read in the integer inputs
                StringTokenizer st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                // map the string to be appended to idxMap
                if (idxMap[a] == 0) {
                    idxMap[a] = b;
                    cache[a] = b;
                    continue;
                }
            

                x = a; // set x = a by default
                while (cache[x] != 0) { 
                    x = cache[x];
                }
                cache[a] = b;
                idxMap[x] = b;              
            }
    
            // append the new string using a string builder
            StringBuilder sb = new StringBuilder();
            while (idxMap[a] != 0) {
                sb.append(arr[a-1]);
                a = idxMap[a];
            }
            sb.append(arr[a-1]);
            
            // print
            pw.write(sb.toString());
        }     
        
        pw.flush();
    }
}
