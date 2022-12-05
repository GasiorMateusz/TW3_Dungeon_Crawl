package com.codecool.dungeoncrawl.logic;

public enum CellType {

    EMPTY("empty", ' '),
    FLOOR("floor", '.'),
    WALL("wall",'#'),
    CLOSED_DOOR("closedDoor", 'C'),
    OPEN_DOOR("openDoor",'O'),
    STAIRS("stairs", 'X');

    private final String tileName;
    private final char mapChar;

    CellType(String tileName, char mapChar) {
        this.tileName = tileName;
        this.mapChar = mapChar;
    }

    public String getTileName() {
        return tileName;
    }

    public char getMapChar() {
        return mapChar;
    }
}
