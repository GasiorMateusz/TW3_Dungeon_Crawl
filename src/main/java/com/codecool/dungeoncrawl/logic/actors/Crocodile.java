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
    }

    @Override
    public String getTileName() {
        return "crocodile";
    }

    @Override
    public void monsterMove(GameMap map) {
        int mapWidth = map.getWidth();
        int mapHeight = map.getHeight();
        if(true){
//        if (teleportationCount == 0) {
            Random random = new Random();
            teleportationCount = random.nextInt(6);
            int coordinateX = random.nextInt(mapWidth);
            int coordinateY = random.nextInt(mapHeight);
            System.out.println(coordinateX+ " " + coordinateY );

//            Cell nextCell = map.getCell(coordinateX,coordinateY);
            Cell cell = getCell();

            if (isValidMove(cell, map.getCell(coordinateX,coordinateY))) {


                cell.setActor(null);
                Cell nextCell = map.getCell(coordinateX,coordinateY);
                nextCell.setActor(this);
//                 = map.getCell(coordinateX,coordinateY);
                cell = nextCell;
            }



//            move(coordinateX,coordinateY);

//        } else {
//            Direction direction = selectRandomDirection();
//            int[] coordinates = convertDirectionToCoordinates(direction);
//            move(coordinates[0], coordinates[1]);
//        teleportationCount--;
        }
    }
}