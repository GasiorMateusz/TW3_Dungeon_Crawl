package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.enums.Direction;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import java.util.Random;

public class Crocodile extends Actor {
    private int teleportationCount;

    public Crocodile(Cell cell) {
        super(cell);
        setStrikeStrength(2);
        setHealth(16);
    }

    @Override
    public String getTileName() {
        return "crocodile";
    }

    @Override
    public void monsterMove(GameMap map) {
        if (teleportationCount == 0) {
            teleportation(map);
        } else {
            Direction direction = selectRandomDirection();
            int[] coordinates = convertDirectionToCoordinates(direction);
            move(coordinates[0], coordinates[1]);
            teleportationCount--;
        }
    }

    private void teleportation(GameMap map) {
        Random random = new Random();
//todo rename teleportationCount
        //todo magic number
        teleportationCount = random.nextInt(6);
        int coordinateX = random.nextInt(map.getWidth());
        int coordinateY = random.nextInt(map.getHeight());

        Cell nextCell = map.getCell(coordinateX, coordinateY);
        if (isValidMove(getCell(), nextCell)) {
            getCell().setActor(null);
            setCell(nextCell);
            nextCell.setActor(this);
        }
    }
}