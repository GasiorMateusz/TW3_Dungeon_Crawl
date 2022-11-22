package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;


public class Player extends Actor implements CanPick, CanAttack{


    public Player(Cell cell) {
        super(cell);
        setStrikeStrength(5);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public boolean canAttack() {
        return false;
    }

    @Override
    public int countAttack() {
        return 0;
    }

    @Override
    public boolean attack() {
        return false;
    }
}
