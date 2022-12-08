package com.codecool.dungeoncrawl.logic;

import java.util.Arrays;
import java.util.Objects;

public enum CellType {

    EMPTY("empty", ' '),
    FLOOR("floor", '.'),
    WALL("wall", '#'),
    CLOSED_DOOR("closedDoor", 'C'),
    OPEN_DOOR("openDoor", 'O'),
    STAIRSDOWN("stairsDown", 'X'),
    STAIRSUP("stairsUp", 'Y');

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

    public static CellType getCellType(char mapChar) {
        try {
            return Arrays
                    .stream(CellType.values())
                    .filter(v -> Objects.equals(v.getMapChar(), mapChar))
                    .findFirst()
                    .orElseThrow(() ->
                            new Exception(String.format("Unknown CellType.key: '%s'", mapChar)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

