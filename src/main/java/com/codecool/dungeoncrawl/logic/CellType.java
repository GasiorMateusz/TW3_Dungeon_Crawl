package com.codecool.dungeoncrawl.logic;

public enum CellType {

    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall");

    // =========================================================================
//     chyba nie potrzebujemy tego xtra stringa i konstruktora (może w przyszłości jakieś wartości doklejone
//                                                              do enuma się przydadzą ¯\_(ツ)_/¯ )
//     skoro można go załatwić przez CellType cellType.name().toLowerCase() ... czyli:
//    public String getTileName() {
//        return this.name().toLowerCase();
//    }
//     dodatkowe typy:
//    KEY
//    DOOR
//    SWORD
//    STAIRS
//    ...
//    TRAP (oraz mmilion innych pomysłów >>na kiedyś, jak będzie czas<<)
//


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
