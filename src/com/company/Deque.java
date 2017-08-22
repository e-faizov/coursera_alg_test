package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node firstNode;
    private Node lastNode;
    private Integer iSize;

    public Deque()
    {
        firstNode = null;
        lastNode = null;
        iSize = 0;
    }
    public boolean isEmpty()
    {
        return iSize == 0;
    }
    public int size()
    {
        return  iSize;
    }
    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        if (firstNode == null)
        {
            Node n = new Node();
            n.value = item;
            firstNode = n;
            lastNode = n;
            iSize++;
        }
        else
        {
            Node n = new Node();
            n.value = item;
            n.prev = firstNode;
            firstNode.next = n;
            firstNode = n;
            iSize++;
        }

    }
    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        if (lastNode == null)
        {
            Node n = new Node();
            n.value = item;
            firstNode = n;
            lastNode = n;
            iSize++;
        }
        else
        {
            Node n = new Node();
            n.value = item;
            n.next = lastNode;
            lastNode.prev = n;
            lastNode = n;
            iSize++;
        }
    }
    public Item removeFirst()
    {
        if (size() == 0)
            throw new NoSuchElementException();

        if(firstNode.prev == null)
        {
            Item ret = firstNode.value;
            firstNode = null;
            iSize--;
            if(lastNode != null)
                lastNode = null;
            return ret;
        }
        else
        {
            Item ret = firstNode.value;
            firstNode = firstNode.prev;
            firstNode.next = null;
            iSize--;
            return ret;
        }
    }
    public Item removeLast()
    {
        if (size() == 0)
            throw new NoSuchElementException();

        if(lastNode.next== null)
        {
            Item ret = lastNode.value;
            if(firstNode != null)
                firstNode = null;
            lastNode = null;
            iSize--;
            return ret;
        }
        else
        {
            Item ret = lastNode.value;
            lastNode = lastNode.next;
            lastNode.prev = null;
            iSize--;
            return ret;
        }
    }
    public Iterator<Item> iterator()
    {
        return new MyIterate();
    }

    private class Node
    {
        private Node next = null;
        private Node prev = null;
        private Item value;
    }

    private class MyIterate implements Iterator<Item>
    {
        private Node current = firstNode;
        public boolean hasNext()
        {
            return current != null;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        public Item next()
        {
            if (current == null)
                throw new NoSuchElementException();
            Item ret = current.value;
            current = current.prev;
            return ret;
        }
    }
}
