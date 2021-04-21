package com.academicapproach.doublyLinkedList;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {

    private NodeDL first;
    private NodeDL last;

    private int size;


    public DoublyLinkedList() {
        first = null;
        last = null;
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
        } else {
            last = newNode;
        }
        size++;
    }

    public void addLast(T element) {

        final NodeDL<T> formerLast = last;
        final NodeDL<T> newNode = new NodeDL(element, formerLast, null);
        last = newNode;

        if (formerLast != null) {
            formerLast.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    public void addAtPos(int index, T element) {

        checkIndexPositionIsInBoundsAndThrowExceptionIfNot(index);

        NodeDL<T> nodeAtPos = getNode(index);
        addNodeBefore(element, nodeAtPos);
    }

    public T getLast() {
        final NodeDL<T> lastNode = last;

        if (last == null) {
            throw new NoSuchElementException();
        }
        return lastNode.element;
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

    public T removeFromPos(int index) {
        checkIndexPositionIsInBoundsAndThrowExceptionIfNot(index);
        NodeDL<T> nodeToDelete = getNode(index);

        final T elementToDelete = nodeToDelete.element;
        final NodeDL<T> nextNode = nodeToDelete.next;
        final NodeDL<T> previousNode = nodeToDelete.prev;

        if (previousNode == null) {
            first = nextNode;
        } else {
            previousNode.next = nextNode;
            nodeToDelete.prev = null;
        }

        if (nextNode == null) {
            last = previousNode;
        } else {
            nextNode.prev = previousNode;
            nodeToDelete.next = null;
        }

        nodeToDelete.element = null;
        size--;
        return elementToDelete;
    }



    private void checkIndexPositionIsInBoundsAndThrowExceptionIfNot(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index + ". List size is: " + size);
        }
    }

    private NodeDL<T> getNode(int index) {

        NodeDL<T> searchedNode = null;

        if (index < size / 2) {

            searchedNode = first;

            for (int i = 0; i < index; i++) {
                searchedNode = searchedNode.next;
            }
        } else {

            searchedNode = last;

            for (int i = size - 1; i > index; i--) {
                searchedNode = searchedNode.prev;
            }

        }


        return searchedNode;
    }

    private void addNodeBefore(T element, NodeDL<T> node) {

        final NodeDL<T> previousNode = node.prev;
        final NodeDL<T> newNode = new NodeDL<T>(element, previousNode, node);
        node.prev = newNode;
        if (previousNode == null) {
            first = newNode;
        } else {
            previousNode.next = newNode;
        }

        size++;
    }

    private static class NodeDL<T> {

        T element;

        NodeDL prev;

        NodeDL next;

        NodeDL(T element, NodeDL prev, NodeDL next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
