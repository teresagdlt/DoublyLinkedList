package com.academicapproach.doublyLinkedList;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> sut;

    @Before
    public void beforeClass() throws Exception {

        sut = new DoublyLinkedList();
    }

    @Test
    public void givenEmptyList_whenGetSize_thenReturnsZero() {

        assertThat(sut.getSize()).isEqualTo(0);
    }

    @Test
    public void givenEmptyList_whenInvokeIsEmpty_thenReturnsTrue() {

        assertThat(sut.isEmpty()).isEqualTo(true);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyList_whenTryToGetFirst_thenReturnNoSuchElementException() {

        sut.getFirst();
    }

    @Test
    public void givenEmptyList_whenAddOneFirstElement_thenItWillConformTheList() {

        // act
        sut.addFirst(4);

        // assert
        assertThat(sut.getSize()).isEqualTo(1);
        assertThat(sut.getFirst()).isEqualTo(4);
    }

    @Test
    public void givenEmptyList_whenAddFirstForSeveralElements_thenTheLastOneWillBeFirst() {

        // act
        sut.addFirst(4);
        sut.addFirst(1);
        sut.addFirst(5);

        // assert
        assertThat(sut.getSize()).isEqualTo(3);
        assertThat(sut.getFirst()).isEqualTo(5);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void givenListWithElements_whenTryToGetByPosOutOfBounds_thenReturnIndexOutOfBoundsException() {

        // arrange
        int size = 5;

        for (int i = 0; i < size; i++) {
            sut.addFirst(i);
        }

        // act
        sut.getByPos(size + 1);
    }


    @Test
    public void givenListWithElements_whenGetByPosInFirstHalf_thenReturnTheCorrectOne() {

        // arrange [10,11,12,13]
        sut.addFirst(10);
        sut.addLast(11);
        sut.addLast(13);
        sut.addAtPos(2, 12);

        //act
        final Integer first = sut.getByPos(0);
        final Integer second = sut.getByPos(1);

        // assert
        assertThat(first).isEqualTo(10);
        assertThat(second).isEqualTo(11);
    }

    @Test
    public void givenListWithElements_whenGetByPosInSecondHalf_thenReturnTheCorrectOne() {

        // arrange [10,11,12,13]
        sut.addFirst(10);
        sut.addLast(11);
        sut.addLast(13);
        sut.addAtPos(2, 12);

        //act
        final Integer third = sut.getByPos(2);
        final Integer forth = sut.getByPos(3);

        // assert
        assertThat(third).isEqualTo(12);
        assertThat(forth).isEqualTo(13);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenListWithElements_whenTryToAddAtPosOutOfBounds_thenReturnIndexOutOfBoundsException() {

        // arrange [0,2,3]
        int elementToAdd = 5;
        int postToAddAt = 3;

        sut.addFirst(3);
        sut.addFirst(2);
        sut.addFirst(0);

        // act
        sut.addAtPos(postToAddAt, elementToAdd);
    }

    @Test
    public void givenListWithElements_whenAddAtPosInTheMiddle_thenInsertItOk() {

        // arrange [0,2,3]
        sut.addFirst(3);
        sut.addFirst(2);
        sut.addFirst(0);

        // act
        sut.addAtPos(1, 10);

        // assert
        assertThat(sut.getByPos(0)).isEqualTo(0);
        assertThat(sut.getByPos(1)).isEqualTo(10);
        assertThat(sut.getByPos(2)).isEqualTo(2);
        assertThat(sut.getSize()).isEqualTo(4);
    }

    @Test
    public void givenListWithElements_whenAddAtPosZero_thenInsertItOkAsFirstElement() {

        // arrange [0,2,3]
        sut.addFirst(3);
        sut.addFirst(2);
        sut.addFirst(0);

        // act
        sut.addAtPos(0, 10);

        // assert
        assertThat(sut.getByPos(0)).isEqualTo(10);
        assertThat(sut.getByPos(1)).isEqualTo(0);
        assertThat(sut.getSize()).isEqualTo(4);
    }

    @Test
    public void givenListWithElements_whenAddLast_thenInsertElementAtTheEnd() {

        // arrange [1,2,3]
        sut.addFirst(3);
        sut.addFirst(2);
        sut.addFirst(1);

        // act
        sut.addLast(4);

        // assert
        assertThat(sut.getByPos(3)).isEqualTo(4);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyList_whenTryToGetLast_thenReturnNoSuchElementException() {

        sut.getLast();
    }

    @Test
    public void givenListWithElements_whenGetLast_thenReturnIt() {

        // arrange [0,1,2]
        sut.addFirst(1);
        sut.addFirst(0);
        sut.addLast(2);

        // act
        final Integer lastElement = sut.getLast();

        // assert
        assertThat(lastElement).isEqualTo(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenListWithElements_whenRemoveFromPosOutOfBounds_thenReturnIndexOutOfBoundsException() {

        // arrange [0,1,2]
        sut.addFirst(2);
        sut.addFirst(1);
        sut.addFirst(0);

        // act
        sut.removeFromPos(3);

    }

    @Test
    public void givenListWithElements_whenRemoveFromPosInTheMiddle_thenReturnElementAndDeleteFromList() {

        // arrange [0,1,2]
        final Integer element = 1;
        final int pos = 1;

        sut.addFirst(0);
        sut.addLast(2);
        sut.addAtPos(pos, element);

        // act
        final Integer removed = sut.removeFromPos(pos);

        // assert
        assertThat(removed).isEqualTo(element);
        assertThat(sut.getSize()).isEqualTo(2);
        assertThat(sut.getByPos(pos)).isNotEqualTo(element);
    }

    @Test
    public void givenListWithElements_whenRemoveFromPosZero_thenReturnElementAndDeleteFromList() {

        // arrange [0,1,2]
        sut.addFirst(1);
        sut.addLast(2);
        sut.addAtPos(0, 0);

        // act
        final Integer removed = sut.removeFromPos(0);

        // assert
        assertThat(removed).isEqualTo(0);
        assertThat(sut.getSize()).isEqualTo(2);
        assertThat(sut.getFirst()).isEqualTo(1);
        assertThat(sut.getByPos(0)).isEqualTo(1);
    }

    @Test
    public void givenListWithElements_whenRemoveFromPosLast_thenReturnElementAndDeleteFromList() {

        // arrange [0,1,2]
        final Integer element = 2;
        final int pos = 2;

        sut.addFirst(0);
        sut.addLast(1);
        sut.addLast(element);

        // act
        final Integer removed = sut.removeFromPos(pos);

        // assert
        assertThat(removed).isEqualTo(element);
        assertThat(sut.getSize()).isEqualTo(2);
        assertThat(sut.getLast()).isEqualTo(1);
        assertThat(sut.getByPos(sut.getSize() - 1)).isEqualTo(1);
    }


    @Test(expected = NoSuchElementException.class)
    public void givenEmptyList_whenTryToRemoveFirst_thenReturnNoSuchElementException() {

        sut.removeFirst();
    }

    @Test
    public void givenListWithElements_whenRemoveFirst_thenReturnFirstElementAndDeleteFromList() {

        // arrange [10,11,12,13]
        sut.addFirst(10);
        sut.addLast(11);
        sut.addLast(13);
        sut.addAtPos(2, 12);

        //act
        final Integer removed = sut.removeFirst();

        // assert
        assertThat(removed).isEqualTo(10);
        assertThat(sut.getSize()).isEqualTo(3);
        assertThat(sut.getFirst()).isEqualTo(11);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyList_whenTryToRemoveLast_thenReturnNoSuchElementException() {

        sut.removeLast();
    }
    @Test
    public void givenListWithElements_whenRemoveLast_thenReturnLastElementAndDeleteFromList() {

        // arrange [10,11,12,13]
        sut.addFirst(10);
        sut.addLast(11);
        sut.addLast(13);
        sut.addAtPos(2, 12);

        //act
        final Integer removed = sut.removeLast();

        // assert
        assertThat(removed).isEqualTo(13);
        assertThat(sut.getSize()).isEqualTo(3);
        assertThat(sut.getLast()).isEqualTo(12);
    }


}