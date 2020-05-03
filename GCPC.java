/*
 * CS2040 Data Structures and Algorithms
 * Lab 3B: Problem A Galactic Collegiate Programming Contest
 *  
 * This program reads in the number of teams and events in the GCPC 
 * and uses an AVL tree implementation to output the rank of my favorite
 * team after each ith event
 * 
 * A0199669X
 * Lim Tiang Jung, Timothy 
 */

import java.util.*;
import java.io.*;

public class GCPC {
    public static void main(String[] args) throws Exception {
        /* fast io */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // first line input
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // simulate all teams with a BST (AVL) tree
        BST tree = new BST();

        // hashmap to keep track of team objects
        HashMap<Integer, Team> map = new HashMap<Integer, Team>();

        // insert fresh teams into tree
        for (int i = 0; i < n; i++) {
            Team team = new Team(i+1);
            tree.insert(team);
            map.put(i+1, team);
        }

        /* main loop */
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            
            Team newTeam = map.get(t);
            tree.delete(newTeam);
            
            newTeam.score++;
            newTeam.penalty += p;
           
            tree.insert(newTeam);

            pw.println(tree.rank(tree.root, map.get(1)));
        }

        br.close();
        pw.close();
    }
}

class Team implements Comparable<Team> {
    // attributes
    protected int name;
    protected int score;
    protected int penalty;

    // constructor
    public Team(int name) {
        this.name = name;
        this.score = 0;
        this.penalty = 0;
    }

    /* overrides */
    @Override
    public int compareTo(Team another) {
        if(this.score == another.score) { // same score
            if(this.penalty == another.penalty) { // same penalty
                return this.name - another.name;
            } else {
                return this.penalty - another.penalty;   
            }   
        } else {
            return another.score - this.score;
        }       
    }
}

class BSTVertex {
    // attributes
    protected BSTVertex parent, left, right;
    protected Team key;
    protected int height; // AVL
    protected int size; // AVL

    // constructor
    BSTVertex(Team t) { 
        this.key = t; 
        this.parent = this.left = this.right = null; 
        this.height = 0; 
        this.size = 1;        
    }
}

class BST {
    // attributes
    protected BSTVertex root;
   
    // constructor
    public BST() {
        this.root = null; 
    }

    // search method, search for Team t
    public Team search(Team t) {
        BSTVertex res = search(root, t);
        return res == null ? null : res.key;
    }

    public BSTVertex search(BSTVertex T, Team t) {
        if (T == null) { // not found
            return null;  
        } else if (T.key == t) { // found
            return T;      
        } else if (T.key.compareTo(t) < 0) { // search to the right
            return search(T.right, t);
        } else { // search to the left
            return search(T.left, t);
        }
    }
  
    // findMin method, find miminum key value in BST
    public Team findMin() { 
        return findMin(root); 
    }

    public Team findMin(BSTVertex T) {
        if (T.left == null) { // this is the min
            return T.key;  
        } else { // go to the left
            return findMin(T.left);   
        }
    }

    // method to find successor of t in AVL
    public Team successor(Team t) { 
        BSTVertex vPos = search(root, t);
        return vPos == null ? null : successor(vPos);
    }

    public Team successor(BSTVertex T) {
        if (T.right != null) { // find successor in the right subtree
            return findMin(T.right);
        } else {
            BSTVertex par = T.parent;
            BSTVertex cur = T;
            
            // if par(ent) is not root and cur(rent) is its right children
            while ((par != null) && (cur == par.right)) {
                cur = par;                        
                par = cur.parent;
            }
            return par == null ? null : par.key;
        }
    }

    // get height of AVL
    public int height(BSTVertex T) {
        if (T == null) {
            return -1;
        } else {
            return T.height;
        }
    }

    // get size of AVL
    public int size(BSTVertex T) {
        if (T == null) {
            return 0;
        } else {
            return T.size;
        }
    }    

    // insert a new team into AVL
    public void insert(Team t) { 
        root = insert(root, t); 
    }

    public BSTVertex insert(BSTVertex T, Team t) {
        if (T == null) { // insertion point found
            return new BSTVertex(t);
        }

        if (T.key.compareTo(t) < 0) { // search right                          
            T.right = insert(T.right, t);
            T.right.parent = T;
        } else if (T.key.compareTo(t) > 0) { // search left                                   
            T.left = insert(T.left, t);
            T.left.parent = T;
        } else {
            T.key = t;
            return T;               
        }

        T.height = Math.max(height(T.left), height(T.right)) + 1;      
        T.size = size(T.left) + size(T.right) + 1;

        return balance(T);
    }  

    // delete a vertex containing Team key t from AVL
    public void delete(Team t) { 
        root = delete(root, t); 
    }

    public BSTVertex delete(BSTVertex T, Team t) {
        if (T == null) { // cannot find item
            return T;
        }

        if (T.key.compareTo(t) < 0) { // search right                 
            T.right = delete(T.right, t);
        } else if (T.key.compareTo(t) > 0) { // search left
            T.left = delete(T.left, t);
        } else { // this is the node to be deleted
            if (T.left == null && T.right == null) { // leaf
                T = null;  
                return T;
            } else if (T.left == null && T.right != null) { // only one child at right        
                T.right.parent = T.parent;
                T = T.right;                                             
            } else if (T.left != null && T.right == null) { // only one child at left        
                T.left.parent = T.parent;
                T = T.left;                                             
            } else { // has two children, find successor
                Team successorV = successor(t);
                T.key = successorV;        
                T.right = delete(T.right, successorV);     
            }
        }

        T.height = Math.max(height(T.left), height(T.right)) + 1;        
        T.size = size(T.left) + size(T.right) + 1;

        return balance(T);                                     
    }
    
    // AVL rotate left
    public BSTVertex rotateLeft(BSTVertex T) { 
        BSTVertex w = T.right; 
        w.parent = T.parent; 
        T.parent = w;
        T.right = w.left;
        
        if (w.left != null) {
            w.left.parent = T;
        }

        w.left = T;
        T.size = size(T.left) + size(T.right) + 1;
        T.height = Math.max(height(T.left), height(T.right))+ 1; 
        w.size = size(w.left) + size(w.right) + 1;
        w.height = Math.max(height(w.left), height(w.right)) + 1; 
         
        return w; 
    }    

    // AVL rotate right
    public BSTVertex rotateRight(BSTVertex T) {
        BSTVertex w = T.left;
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;

        if(w.right != null) {
            w.right.parent = T;
        }
        
        w.right = T;
        T.size = size(T.left) + size(T.right) + 1;
        T.height = Math.max(height(T.left), height(T.right))+ 1;
        w.size = size(w.left) + size(w.right) + 1;
        w.height = Math.max(height(w.left), height(w.right))+ 1; 
      
        return w;
    }

    // balance factor
    public int balanceFactor(BSTVertex T) { 
        if (T == null) { 
            return 0; 
        } else {
          return height(T.left) - height(T.right);
        }
    }

    // AVL balance function
    public BSTVertex balance(BSTVertex T) {
        int bf = balanceFactor(T);

        if (bf == 2) { // +2 out-of-balance
            if (balanceFactor(T.left) >= 0) {
                return rotateRight(T);
            }
            if (balanceFactor(T.left) == -1) {
                T.left = rotateLeft(T.left);
                return rotateRight(T);
            }
        } 
        
        if (bf == -2) { // -2 out-of-balance
            if (balanceFactor(T.right) <= 0) {
                return rotateLeft(T);
            } 
            if (balanceFactor(T.right) == 1) {
                T.right = rotateRight(T.right);
                return rotateLeft(T);
            }
        }

        return T; // balanced
    }
    
    // get rank of Team with respect to root
    public int rank(BSTVertex root, Team t) {
        if (root == null) {
            return 0;
        } else if (root.key.compareTo(t) > 0) {
            return rank(root.left, t);
        } else if (root.key.compareTo(t) < 0) {
            return size(root.left) + rank(root.right, t) + 1;
        } else {
            return size(root.left) + 1;
        }
    }    
}
