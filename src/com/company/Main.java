package com.company;

import edu.princeton.cs.algs4.*;

import java.util.Iterator;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        Deque<String> strD = new Deque<>();

        strD.addFirst("1");
        strD.addFirst("2");

        Iterator<String> its = strD.iterator();

        while (its.hasNext())
        {
            StdOut.println(its.next());
        }

        RandomizedQueue<String> strRQ = new RandomizedQueue<>();
        int n = parseInt(args[0]);

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
