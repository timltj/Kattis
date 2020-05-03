/*
 * CS2040 Data Structures and Algorithms
 * Lab 6: Problem A Kattis's Quest
 *  
 * This program uses a tree set to simulate the Kattis's quest data and prints
 * out the amount of gold earned by kattis with each query
 *
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class KattissQuest {
    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
       
        int n = Integer.parseInt(br.readLine());

        // treeset to store quests
        TreeSet<Quest> ts = new TreeSet<>();

        /* main loop */
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("add")) {
                int energy = Integer.parseInt(st.nextToken());
                int gold = Integer.parseInt(st.nextToken());
                Quest quest = new Quest(energy, gold);
                int uniqueID = quest.uniqueID;

                if (ts.contains(quest)) { // duplicate quest
                    ts.add(new Quest(energy, gold, uniqueID+1));
                    continue;
                }

                ts.add(quest);
                continue;
            }
            
            long myGold = 0;
            int myEnergy = Integer.parseInt(st.nextToken());
            Quest tempQuest = ts.floor(new Quest(myEnergy, Integer.MAX_VALUE));

            while (tempQuest != null) { // create temp quest for query
                myGold += tempQuest.gold;
                myEnergy -= tempQuest.energy;
                ts.remove(tempQuest);
                tempQuest = ts.floor(new Quest(myEnergy, Integer.MAX_VALUE));
            }

            pw.println(myGold);
        }

        br.close();
        pw.close();
    }
}

class Quest implements Comparable<Quest> {
    // attributes
    protected final int energy;
    protected final int gold;
    protected final int uniqueID;

    // constructors
    public Quest(int energy, int gold) {
        this.energy = energy;
        this.gold = gold;
        this.uniqueID = this.hashCode();
    }
    
    public Quest(int energy, int gold, int uniqueID) {
        this.energy = energy;
        this.gold = gold;
        this.uniqueID = uniqueID;
    }   

    /* overrides */
    @Override
    public int compareTo(Quest another) {
        if (this.energy != another.energy) {
            return this.energy - another.energy;
        } else if (this.gold != another.gold) { // compare by gold
            return this.gold - another.gold;
        } else { // compare by uniqueID     
            return this.uniqueID - another.uniqueID;
        }            
    }
}
