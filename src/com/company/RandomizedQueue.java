package com.company;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node firstNode;
    private Integer iSize;

    public RandomizedQueue()
    {
        firstNode = null;
        iSize = 0;
    }
    public boolean isEmpty()
    {
        return iSize == 0;
    }
    public int size()
    {
        return iSize;
    }
    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        Node n = new Node();
        n.value = item;
        iSize++;
        if (firstNode == null)
        {
            firstNode = n;
        }
        else
        {
            n.next = firstNode;
            firstNode = n;
        }
    }

    private Item getRnd(boolean removed)
    {
        if (size() == 0)
            throw new NoSuchElementException();

        int iR = StdRandom.uniform(iSize);

        Node n = firstNode;
        Node nPrev = null;

        for (int i = 0; i < iR; i++)
        {
            nPrev = n;
            n = n.next;
        }
        if (removed ) {
            if (nPrev != null)
                nPrev.next = n.next;
            else
                firstNode = firstNode.next;
        }
        return n.value;

    }

    public Item dequeue()
    {
        Item ret = getRnd(true);
        iSize--;
        return ret;
    }
    public Item sample()
    {
        return getRnd(false);
    }
    public Iterator<Item> iterator()
    {
        return new MyIterate();
    }

    private class Node
    {
        private Item value;
        private Node next;
    }

    private class MyIterate implements Iterator<Item>
    {
        Integer[] arr = new Integer[iSize];
        Integer iLen = 0;
        public boolean hasNext()
        {
            return arr[iSize - 1] == null;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        public Item next()
        {
            if (iLen.equals(iSize))
                throw new java.util.NoSuchElementException();

            Integer iR = -1;
            for (;;)
            {
                iR = StdRandom.uniform(iSize);
                if (!contains(iR))
                    break;
            }
            arr[iLen] = iR;
            iLen++;
            Node n = firstNode;
            for (int i = 0; i < iR; i++)
            {
                n = n.next;
            }
            return n.value;
        }

        private boolean contains(int iR)
        {
            for (int i = 0; i < iLen; i++)
            {
                if (arr[i] == iR)
                    return true;

                if (arr[i] == null)
                    break;
            }
            return false;
        }
    }
}