package com.codecool.dungeoncrawl.logic;

public class MultiMap {

    private final String[] maps = {"/death.txt","/ghost.txt","/crocodile.txt","/octopus.txt","/items.txt","/doors.txt","/DEMO-longmap.txt"};

    public MultiMap() {
    }

    public String getMapFromSet(int index) {
        if ((index > -1) && (index < this.maps.length)) {
            return maps[maps.length-1-index];
        } else {
            return this.maps[0];
        }
    }
}
