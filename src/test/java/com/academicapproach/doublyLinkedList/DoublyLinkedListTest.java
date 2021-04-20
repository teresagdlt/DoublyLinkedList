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
    public void givenInitialList_whenGetSize_thenReturnsZero() {

        assertThat(sut.getSize()).isEqualTo(0);
    }

    @Test
    public void givenInitialList_whenInvokeEmpty_thenReturnsTrue() {

        assertThat(sut.isEmpty()).isEqualTo(true);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyList_whenTryToGetFirst_thenReturnNoSuchElementException() {

        sut.getFirst();
    }

    @Test
    public void givenAList_whenAddOneFirstElement_thenItWillConformTheList() {

        // act
        sut.addFirst(4);

        // assert
        assertThat(sut.getSize()).isEqualTo(1);
        assertThat(sut.getFirst()).isEqualTo(4);

    }


}