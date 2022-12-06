package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.enums.Direction;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Actor implements Drawable {

    public boolean isAlive = true;
    public int teleport = 0;
    private Cell cell;
    private int health = 7;
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

    public void monsterMove(GameMap map) {
        Direction direction = selectRandomDirection();
        int[] coordinates = convertDirectionToCoordinates(direction);
        move(coordinates[0], coordinates[1]);
    }

    Direction selectRandomDirection() {
        List<Direction> directionList = Arrays.asList(Direction.RIGHT, Direction.LEFT, Direction.UP, Direction.DOWN);
        Random random = new Random();
        return directionList.get(random.nextInt(directionList.size()));
    }

    int[] convertDirectionToCoordinates(Direction direction) {
        int[] coordinates = new int[2];
        switch (direction) {
            case RIGHT:
                coordinates[0] = 0;
                coordinates[1] = 1;
                break;
            case LEFT:
                coordinates[0] = 0;
                coordinates[1] = -1;
                break;
            case UP:
                coordinates[0] = -1;
                coordinates[1] = 0;
                break;
            case DOWN:
                coordinates[0] = 1;
                coordinates[1] = 0;
                break;
        }
        return coordinates;
    }

    boolean isValidMove(Cell origin, Cell cellTested) {
        boolean accessibility = false;
        if (cellTested == null) {
            return accessibility;
        }

        if (cellTested.isActor()) {
            checkActorsCollision(origin, cellTested);
            return accessibility;
        }

        if (cellTested.getType().equals(CellType.STAIRSDOWN) && origin.isPlayer()) {
            goToStairs(1);
        }

        if (cellTested.getType().equals(CellType.STAIRSUP) && origin.isPlayer()) {
            goToStairs(-1);
        }

        if (cellTested.isFloor() || cellTested.isOpenDoor()) {
            accessibility = true;
        }
        //todo extract to method
        if (origin.isPlayer() && cellTested.isClosedDoor() && ((Player) origin.getActor()).getInventory().hasKey()) {
            Player actor = (Player) origin.getActor();
            actor.setUnlockedDoor(true);
            cellTested.setType(CellType.OPEN_DOOR);
            actor.getInventory().deleteKeyFromInventory();
        }
        if (origin.isPlayer() && ((Player) origin.getActor()).hasDeveloperName()) {
            accessibility = true;
        }

        if (origin.isGhosts()) {
            accessibility = true;
        }
        return accessibility;
    }

    private void goToStairs(int direction) {
        this.teleport = direction;
    }

    private void checkActorsCollision(Cell origin, Cell cellTested) {
        if (origin.isPlayer()) {
            ((Player) this).setOpponent(cellTested.getActor());
            fight(cellTested.getActor());
        }
    }

    private void fight(Actor opponent) {
        System.out.println(" FIGHT:  " + this.getClass().getSimpleName() + " health:" + this.getHealth() +
                " vs. " + opponent.getClass().getSimpleName() + " health:" + opponent.getHealth());
        opponent.setHealth(opponent.getHealth() - this.getStrikeStrength());
        if (opponent.getHealth() > 0) {
            this.setHealth(this.getHealth() - opponent.getStrikeStrength());
            if (this.getHealth() < 1) {
                this.setHealth(0);
                defeated(this);
            }
        } else {
            defeated(opponent);
            opponent.getCell().setActor(null);
        }
    }

    private void defeated(Actor killedInAction) {
        System.out.println(killedInAction.getClass().getSimpleName() + " is dead ");
        if (killedInAction instanceof Player) {
            System.out.println("\n\n [Y] [O] [U]   [D] [I] [E] [D]   [!]\n");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.isAlive = false;
        }
        killedInAction.isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getStrikeStrength() {
        return strikeStrength;
    }

    public void setStrikeStrength(int strikeStrength) {
        this.strikeStrength = strikeStrength;
    }
}
