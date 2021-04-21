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

        // arrange
        sut.addFirst(13);
        sut.addFirst(12);
        sut.addFirst(11);
        sut.addFirst(10);

        //act
        final Integer first = sut.getByPos(0);
        final Integer second = sut.getByPos(1);


        // assert
        assertThat(first).isEqualTo(10);
        assertThat(second).isEqualTo(11);
    }

    @Test
    public void givenListWithElements_whenGetByPosInSecondHalf_thenReturnTheCorrectOne() {

        // arrange
        sut.addFirst(13);
        sut.addFirst(12);
        sut.addFirst(11);
        sut.addFirst(10);

        //act
        final Integer third = sut.getByPos(2);
        final Integer forth = sut.getByPos(3);

        // assert
        assertThat(third).isEqualTo(12);
        assertThat(forth).isEqualTo(13);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenListWithElements_whenTryToAddAtPosOutOfBounds_thenReturnIndexOutOfBoundsException() {

        // arrange
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

        // arrange
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

        // arrange
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

        // arrange
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

        // arrange
        sut.addFirst(4);
        sut.addFirst(1);
        sut.addLast(10);

        // act
        final Integer lastElement = sut.getLast();

        // assert
        assertThat(lastElement).isEqualTo(10);
    }

}