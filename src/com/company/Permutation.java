package com.company;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> strRQ = new RandomizedQueue<>();
        int n = Integer.parseInt(args[0]);

        for (int i = 0; i < n; i++)
        {
            strRQ.enqueue(StdIn.readString());
        }

        int iS = strRQ.size();
        for (int i = 0; i < iS; i++)
        {
            StdOut.println(strRQ.dequeue());
        }
    }

}
