package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int  nodeNum;

    private class Node {
        Item item;
        Node next;
        Node prev;

        private Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Deque() {
        this.first = null;
        this.last = null;
        this.nodeNum = 0;

    }// construct an empty deque

    public boolean isEmpty() {
        return first == null && last == null && nodeNum == 0;
    }// is the deque empty?

    public int size() {
        return this.nodeNum;
    }// return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The argument is empty.");
        }
        if (this.isEmpty()) {
            this.first = new Node(item, null, null);
            this.last = this.first;
            nodeNum++;
        } else {
            Node node = new Node(item, this.first, null);
            first.prev = node;
            first = node;
            nodeNum++;
        }
    }// add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The argument is empty.");
        }
        if (this.isEmpty()) {
            this.first = new Node(item, null, null);
            this.last = this.first;
            nodeNum++;
        } else {
            Node node = new Node(item, null, last);
            this.last.next = node;
            this.last = node;
            nodeNum++;
        }
    }// add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("This queue is empty.");
        }
        Item item = this.first.item;
        this.first = this.first.next;
        this.first.prev = null;
        if (this.nodeNum == 1) {
            this.last = null;
        }
        this.nodeNum--;
        return item;
    }// remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("This queue is empty.");
        }
        Item item = this.last.item;
        this.last = this.last.prev;
        this.last.next = null;
        if (this.size() == 1) {
            this.first = null;
        }
        this.nodeNum--;
        return item;
    }// remove and return the item from the end

    public Iterator<Item> iterator() {
        return new ListIterator();
    }// return an iterator over items in order from front to end

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The queue is empty");
            }
            Item item = current.item;

            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This method is not supported");
        }
    }

    public static void main(String[] args) {
        Deque<Integer> integers = new Deque<>();
        // System.out.println(integers.size());
        // integers.addFirst(5);
        integers.addLast(5);
        integers.addFirst(3);
        integers.addLast(6);
        System.out.println("Add");
        for (Integer i : integers) {
            System.out.println(i);
        }
        // integers.removeFirst();
        // System.out.println(integers.size());
        System.out.println(integers.removeLast());
        System.out.println("remove");
        // System.out.println(integers.size());
        for (Integer i : integers) {
            System.out.println(i);
        }

    } // unit testing (optional)
}
