package com.codecool.dungeoncrawl.logic.items;

import java.util.Arrays;
import java.util.Objects;

public enum ItemType {
    CROWN('d', "Game"),
    KEY('K',"Game"),
    SWORD('W', "Weapon"),
    BOW('B', "Weapon"),
    LIFE('H', "Game"),
    MEDICINE('M', "Medical"),
    BANDAGE('D', "Medical");

    private final char mapChar;
    private final String subtype;

    ItemType(char mapChar, String subtype) {
        this.mapChar = mapChar;
        this.subtype = subtype;
    }

    public static char getItemMapChar(ItemType item) {
        return item.mapChar;
    }

    public static ItemType getItemType(char mapChar) {
        try {
            return Arrays
                    .stream(ItemType.values())
                    .filter(v -> Objects.equals(v.getItemMapChar(v), mapChar))
                    .findFirst()
                    .orElseThrow(() ->
                            new Exception(String.format("Unknown cItemType.key: '%s'", mapChar)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
