package com.codecool.dungeoncrawl.logic;

public enum Direction {

    RIGHT(new Point(1, 0)),
    UP(new Point(0, -1)),
    LEFT(new Point(-1, 0)),
    DOWN(new Point(0, 1));

    private final Point direction;

    Direction(Point direction) {
        this.direction = direction;
    }

    public Point getValue() {
        return direction;
    }

}
