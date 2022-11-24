package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Player extends Actor implements CanPick {


    private String name;
    private List<Item> items= new ArrayList<>();
    private boolean ifHasKey=false;
    private final int normalStrikeStrength=5;
    private boolean ifHasSword=false;
    private boolean ifHasBow=false;

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

    public Player getPlayer() {
        return this;
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
    public boolean hasSword() {
        for(int i=0; i<items.size();i++){
            if (items.get(i).getTileName().equals("sword")) {
                ifHasSword = true;
                return ifHasSword;
            }
        }
        ifHasSword=false;
        return ifHasSword;
    }
    public boolean hasBow() {
        for(int i=0; i<items.size();i++){
            if (items.get(i).getTileName().equals("bow")) {
                ifHasBow = true;
                return ifHasBow;
            }
        }
        ifHasBow=false;
        return ifHasBow;
    }


    @Override
    public void pickUp() {
        if (canPickUp()) {
            items.add(getCell().getItem());
        }
        getCell().setItem(null);
        }

    @Override
    public boolean canPickUp() {
        return getCell().getItem() != null;
    }

    public String getTileName() {
        return "player";
    }

    public List<Item> getItems() {
        return items;
    }

    public void deleteKeyFromInventory() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals("key")) {
                items.remove(i);
                break;
            }
        }
    }

    public boolean hasDeveloperName() {
        if (!getName().equals(null)) {
            return Stream.of("Dominika", "Ania", "Dawid", "Mateusz", "Marcin")
                    .anyMatch(s -> (getName().equalsIgnoreCase(s)));
        }
        return false;
    }

}
