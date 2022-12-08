package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.stream.IntStream;

public class MultiMap {

    private final String[] mapsNames = {"/mapT01.txt", "/mapT02.txt", "/mapT03.txt", "/mapT04.txt"};
    private GameMap[] maps = new GameMap[mapsNames.length];
    public MultiMap() {
        IntStream.range(0, mapsNames.length)
                .forEach(mapIndex -> maps[mapIndex] = MapLoader.loadMap(mapsNames[mapIndex]));
        maps[0].setPlayer(new Player(new Cell(maps[0],1,1,CellType.FLOOR)));
    }

    public GameMap getMapFromSet(int index) {
        if ((index > -1) && (index < this.maps.length)) {
            return maps[index];
        } else {
            return this.maps[0];
        }
    }



}
