package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;


public class Player extends Actor implements CanPick, CanAttack{


    private List<Item> items= new ArrayList<>();
    private boolean ifHasKey=false;



    public Player(Cell cell) {
        super(cell);
        setStrikeStrength(5);
    }

    public boolean hasKey() {
        return ifHasKey;
    }
    @Override
    public void pickUp(){
        if(canPickUp()){
                items.add(getCell().getItem());
                if (getCell().getItem().getTileName().equals("key")){
                    ifHasKey=true;
                }
            }
        getCell().setItem(null);
        }

    @Override
    public boolean canPickUp() {
        return getCell().getItem() !=null;
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
    public List<Item> getItems() {
        return items;
    }

}
