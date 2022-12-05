package com.codecool.dungeoncrawl.logic.items;

public enum ItemType {
    CROWN('d'),
    KEY('K'),
    SWORD('W'),
    BOW('B'),
    NEWLIFE('H'),
    MEDICINE('M'),
    BANDAGE('D');

    private final char mapChar;

    ItemType(char mapChar) {
        this.mapChar = mapChar;
    }

    public static char getItemMapChar(ItemType item) {
        return item.mapChar;
    }
}
