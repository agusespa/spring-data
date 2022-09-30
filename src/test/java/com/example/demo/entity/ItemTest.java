package com.example.demo.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemTest {

    private Item referenceItem;

    private Item testedItem;

    @BeforeAll
    void setUp() {
        referenceItem = new Item("Chair");
        referenceItem.setId("B_58");

        testedItem = new Item("Chair");
        testedItem.setId("B_58");
    }

    @Test
    void equalsShouldReturnTrueForEqualObjects() {
        assertTrue(referenceItem.equals(testedItem));
        assertTrue(testedItem.equals(referenceItem));
        assertTrue(testedItem.equals(testedItem));
    }

    @Test
    void equalsShouldReturnFalseForDifferentObjects() {
        assertFalse(referenceItem.equals(testedItem));
        assertFalse(testedItem.equals(referenceItem));
    }

    @Test
    void testHashCode() {
        Set<Item> set = new HashSet<>();
        set.add(referenceItem);
        assertFalse(set.add(testedItem));
    }
}
