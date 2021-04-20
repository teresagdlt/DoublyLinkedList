package com.academicapproach.doublyLinkedList;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {

    private NodeDL first;

    private int size;


    public DoublyLinkedList() {
        first = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T element) {

        final NodeDL<T> formerFirst = first;
        final NodeDL<T> newNode = new NodeDL<T>(element, null, formerFirst);
        first = newNode;

        if (formerFirst != null) {
            formerFirst.prev = first;
        }
        size++;

    }

    public T getFirst() {

        final NodeDL<T> firstNode = first;

        if (first == null) {
            throw new NoSuchElementException();
        }
        return firstNode.element;
    }

    private static class NodeDL<T> {

        final T element;

        NodeDL prev;

        NodeDL next;

        NodeDL(T element, NodeDL prev, NodeDL next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
