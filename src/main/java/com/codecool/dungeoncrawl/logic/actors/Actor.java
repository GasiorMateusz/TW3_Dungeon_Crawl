package com.codecool.dungeoncrawl.logic.actors;
// =================test007===test008==ania===Dominika
import com.codecool.dungeoncrawl.enums.Direction;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int attack;

    private int health = 10;


    private int strikeStrength;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);

        if (isValidMove(cell, nextCell)) {


            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void monsterMove(){
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


    private boolean isValidMove(Cell origin, Cell cellTested) {

//    ==================================    to będzie do walidowania ruchu
//    ==================================    i triggerowania akcji np. walki
//    ==================================    do rozbudowania w miarę rozwoju programu

        boolean accessibility = false;
        if (cellTested != null) {
            //   if (cellTested.isOutOfMap(cellTested.getX(), cellTested.getY()))

            if (cellTested.isActor()) {
                checkActorsCollision(cell, cellTested);
                return accessibility;
            }
            if (cellTested.isFloor()) {
                accessibility = true;
            }

            return accessibility;
        } else return accessibility;
    }


    private void checkActorsCollision(Cell origin, Cell cellTested) {
        if (cell.isPlayer()) {
            fight(cellTested.getActor());
        }
    }

    private void fight(Actor opponent) {
        System.out.println(" FIGHT:  " + this.getClass().getSimpleName() + " health:" + this.getHealth() +
                " vs. " + opponent.getClass().getSimpleName() + " health:" + opponent.getHealth());
        opponent.setHealth(opponent.getHealth() - this.getStrikeStrength());
        if (opponent.getHealth() > 0) {
            this.setHealth(this.getHealth() - opponent.getStrikeStrength());
            if (this.getHealth() < 0) {
                defeated(this);
            }
        } else {
            defeated(opponent);
            opponent.getCell().setActor(null);
        }
    }

    private void defeated(Actor killedInAction) {
        System.out.println(killedInAction.getClass().getSimpleName() + " is dead ");
    }

    public void setHealth(int health) {
        this.health = health;}

    public boolean isDead(){
        return false;
    }



    public void updateHealth(int healthChange){

    }

    public boolean tryToMove(int dx, int dy){
        return false;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    protected int getStrikeStrength() {
        return strikeStrength;
    }

    protected void setStrikeStrength(int strikeStrength) {
        this.strikeStrength = strikeStrength;
    }
}
