package com.codecool.dungeoncrawl.logic;

public class MultiMap {

    private final String[] maps = {"/mapT01.txt", "/mapT02.txt", "/mapT03.txt"};

    public MultiMap() {
    }

    public String getMapFromSet(int index) {
        if ((index > -1) && (index < this.maps.length)) {
            return maps[index];
        } else {
            return this.maps[0];
        }
    }
}
