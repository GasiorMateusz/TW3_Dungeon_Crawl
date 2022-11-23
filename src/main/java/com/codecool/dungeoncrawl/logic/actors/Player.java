package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;


public class Player extends Actor implements CanPick, CanAttack{

    private String name;
    private List<Item> items= new ArrayList<>();
    private boolean ifHasKey=false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(Cell cell) {
        super(cell);
        setStrikeStrength(5);
    }

    public boolean hasKey() {
        for(int i=0; i<items.size();i++){
            if (items.get(i).getTileName().equals("key")) {
                ifHasKey = true;
                return ifHasKey;
            }
        }
        ifHasKey=false;
        return ifHasKey;
    }
    @Override
    public void pickUp(){
        if(canPickUp()){
                items.add(getCell().getItem());

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
    public void deleteKeyFromInventory(){
        for(int i=0; i<items.size();i++){
            if (items.get(i).getTileName().equals("key")) {
                items.remove(i);
                break;
            }
        }
    }
    public boolean hasDeveloperName(){
        return getName().equalsIgnoreCase("Dominika") || getName().equalsIgnoreCase("Ania")
                || getName().equalsIgnoreCase("Dawid") || getName().equalsIgnoreCase("Mateusz")
                || getName().equalsIgnoreCase("Marcin");
    }

}
