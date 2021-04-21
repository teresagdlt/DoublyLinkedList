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

    public void addAtPos(int index, T element) {

        checkIndexPositionIsInBoundsAndThrowExceptionIfNot(index);

        NodeDL<T> nodeAtPos = getNode(index);
        addNodeBefore(element, nodeAtPos);
    }

    public T getFirst() {

        final NodeDL<T> firstNode = first;

        if (first == null) {
            throw new NoSuchElementException();
        }
        return firstNode.element;
    }

    public T getByPos(int index) {

        checkIndexPositionIsInBoundsAndThrowExceptionIfNot(index);
        return getNode(index).element;
    }

    private boolean checkIndexPositionIsInBoundsAndThrowExceptionIfNot(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index + ". List size is: " + size);
        } else return true;
    }

    private NodeDL<T> getNode(int index) {

        NodeDL<T> searchedNode = first;

        while (index > 0) {
            index--;
            searchedNode = searchedNode.next;
        }

        return searchedNode;
    }

    private void addNodeBefore(T element, NodeDL<T> node) {

        final NodeDL<T> previousNode = node.prev;
        final NodeDL<T> newNode = new NodeDL<T>(element, previousNode, node);

        if (previousNode == null) {
            first = newNode;
        } else {
            previousNode.next = newNode;
        }
        size++;
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
