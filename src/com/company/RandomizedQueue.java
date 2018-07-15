package com.company;

import edu.princeton.cs.algs4.StdRandom;

import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int  size;
    private Node first;

    private class Node {
        Item item;
        Node next;
        Node prev;

        private Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        private Node() {
            this.item = null;
            this.next = null;
            this.prev = null;
        }
    }

    public RandomizedQueue() {
        size = 0;
        first = null;
    }        // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0 && first == null;
    }             // is the randomized queue empty?

    public int size() {
        return this.size;
    }           // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (this.isEmpty()) {
            this.first = new Node(item, null, null);
        } else {
            Node node = new Node(item, first, null);
            this.first.prev = node;
            first = node;
        }
        this.size++;
    }          // add the item

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The randomized queue is empty");
        }
        Node tmp = first;
        int  i   = StdRandom.uniform(size) - 1;
        for (int n = 0; n < i; n++) {
            tmp = tmp.next;
        }
        if (tmp.next != null) tmp.next.prev = tmp.prev;
        if (tmp.prev != null) tmp.prev.next = tmp.next;
        tmp.next = null;
        tmp.prev = null;
        size--;
        return tmp.item;
    }         // remove and return a random item

    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The randomized queue is empty");
        }
        Node tmp = first;
        int  i   = StdRandom.uniform(size);
        for (int n = 0; n < i; n++) {
            tmp = tmp.next;
        }
        return tmp.item;
    }        // return a random item (but do not remove it)

    public Iterator<Item> iterator() {
        return new RandomListIterator();
    }       // return an independent iterator over items in random order

    private class RandomListIterator implements Iterator<Item> {
        private int    index;
        private Item[] items;

        private RandomListIterator() {
            items = (Item[]) new Object[size()];
            Node node = first;
            int  i    = 0;
            while (node != null) {
                items[i++] = node.item;
                node = node.next;
            }
            StdRandom.shuffle(items);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This method is not supported");
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return items[index++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> integers = new RandomizedQueue<>();
        integers.enqueue(5);
        integers.enqueue(7);
        integers.enqueue(10);
        integers.enqueue(13);
        integers.enqueue(2);
        integers.enqueue(49);
        integers.enqueue(50);
        // System.out.println(integers.dequeue());
        // System.out.println(integers.size());
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }// unit testing (optional)
}
