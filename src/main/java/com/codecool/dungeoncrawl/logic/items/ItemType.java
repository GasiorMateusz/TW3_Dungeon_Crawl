package com.codecool.dungeoncrawl.logic.items;

public enum ItemType {
    CROWN('d', "Game"),
    KEY('K',"Game"),
    SWORD('W', "Weapon"),
    BOW('B', "Weapon"),
    NEWLIFE('H', "Game"),
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
    public static String getItemSubtype(ItemType item) {
        return item.subtype;
    }
}
