package com.codecool.dungeoncrawl.logic;

public class MultiMap {

    private final String[] maps = {"/ghost.txt","/crocodile.txt","/octopus.txt","/items.txt","/DEMO-longmap.txt", "/doors.txt"};

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
