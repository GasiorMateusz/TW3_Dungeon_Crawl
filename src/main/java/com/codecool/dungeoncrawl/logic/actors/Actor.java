package com.codecool.dungeoncrawl.logic.actors;
// =================test007
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

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
