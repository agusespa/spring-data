package com.example.demo.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemTest {

    private Item referenceItem;

    @BeforeAll
    void setUp() {
        referenceItem = new Item("Chair");
        referenceItem.setId("B_58");
    }

    @Test
    void equalsShouldReturnTrueForEqualObjects() {
        Item testedItem = new Item("Chair");
        testedItem.setId("B_58");
        assertTrue(referenceItem.equals(testedItem));
        assertTrue(testedItem.equals(referenceItem));
        assertTrue(testedItem.equals(testedItem));
    }

    @Test
    void equalsShouldReturnFalseForDifferentObjects() {
        Item testedItem = new Item("Chairs");
        testedItem.setId("B_54");
        assertFalse(referenceItem.equals(testedItem));
        assertFalse(testedItem.equals(referenceItem));
    }

    @Test
    void testHashCode() {
    }
}
