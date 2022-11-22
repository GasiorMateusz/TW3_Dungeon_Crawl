package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.enums.Direction;
import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Octopus extends Actor {
    public Octopus(Cell cell) {
        super(cell);
        setStrikeStrength(2);
    }

    @Override
    public String getTileName() {
        return "octopus";
    }



    public void moveRandom(){
        Direction direction = selectRandomDirection();
        int[] coordinates = convertDirectionToCoordinates(direction);
        move(coordinates[0],coordinates[1]);
    }



    private Direction selectRandomDirection(){
        List<Direction> directionList = Arrays.asList(Direction.RIGHT, Direction.LEFT, Direction.UP, Direction.DOWN);
        Random random = new Random();
        return directionList.get(random.nextInt(directionList.size()));
    }


    private int[] convertDirectionToCoordinates(Direction direction){
        int[] coordinates = new int[2];
        switch (direction){
            case RIGHT: coordinates[0]=0; coordinates[1]=1; break;
            case LEFT: coordinates[0]=0; coordinates[1]=-1; break;
            case UP: coordinates[0]=-1; coordinates[1]=0; break;
            case DOWN: coordinates[0]=1; coordinates[1]=0; break;
        }
        return coordinates;
    }


}