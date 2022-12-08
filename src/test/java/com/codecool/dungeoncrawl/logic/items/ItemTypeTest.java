package com.codecool.dungeoncrawl.logic.items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTypeTest {

    @Test
    void getItemTypeWithWrongCharShouldThrowException() {
        assertThrows(RuntimeException.class, () -> ItemType.getItemType('!'));
    }

    @Test
    void getItemTypeWithRightCharShouldReturnType() {
        assertEquals(ItemType.getItemType('W'), ItemType.SWORD);
    }
}