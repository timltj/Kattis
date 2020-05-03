/*
 * CS2040 Data Structures and Algorithms
 * Lab 1B: Problem A Best Relay Team
 *
 * This program takes as integer input, the number of shortlisted runners,
 * as well as their String names, totalTime to run first leg and totalTime for subsequent legs (of type double).
 * It then outputs the totalTime of the best team, and the names of the runners in that team.
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;

public class BestRelayTeam {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        // array of runners
        Runner[] runnerArr = new Runner[n];

        /* input */
        for (int x = 0 ; x < n; x++) {
            String[] input = sc.nextLine().split(" ");
            String runnerName = input[0];
            double timeFirst = Double.parseDouble(input[1]);
            double timeOther = Double.parseDouble(input[2]);
            Runner runner = new Runner(runnerName, timeFirst, timeOther);
            runnerArr[x] = runner;
        }

        Arrays.sort(runnerArr);

        double minTime = Double.MAX_VALUE;
        Runner minTimeRunner = new Runner();

        for (Runner runner : runnerArr) {
            double totalTime = runner.getFirstLeg();
            int vacancy = 3;
            for (Runner runner2 : runnerArr) {
                if (runner2 == runner) {
                    continue;
                }
                totalTime += runner2.getOtherLeg();
                vacancy -= 1;
                if (vacancy == 0) {
                    break;
                }
            }

            if (totalTime < minTime) {
                minTime = totalTime;
                minTimeRunner = runner;
            }
        }
    
        /* output */
        System.out.println(String.format("%.2f", minTime));
        System.out.println(minTimeRunner.getName());
        
        int vacancy = 3;
        for (Runner runner : runnerArr) {
            if (runner == minTimeRunner) {
                continue;
            }
            System.out.println(runner.getName());
            vacancy--;
            if (vacancy == 0) { 
                break;
            }
        }

    }
}

class Runner implements Comparable<Runner> {
    // attributes
    protected String name;
    protected double firstLeg;
    protected double otherLeg;

    // constructor
    public Runner() {}
      
    public Runner(String name, double firstLeg, double otherLeg) {
        this.name = name;
        this.firstLeg = firstLeg;
        this.otherLeg = otherLeg;
    }

    /* getters */
    public String getName() {   
        return this.name;
    }

    public double getFirstLeg() {
        return this.firstLeg;
    }

    public double getOtherLeg() {
        return this.otherLeg;
    }

    /* overrides */
    @Override
    public int compareTo(Runner another) {
        if (this.otherLeg - another.otherLeg > 0) {
            return 1;
        }
        if (this.otherLeg - another.otherLeg < 0) {
            return -1;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
