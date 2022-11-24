package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Player extends Actor implements CanPick {


    private String name;

    public Actor getOpponent() {
        return opponent;
    }

    public void setOpponent(Actor opponent) {
        this.opponent = opponent;
    }

    private Actor opponent;


    private int lifeCounter = 0;
    private List<Item> items = new ArrayList<>();
    private boolean ifHasKey = false;
    private final int normalStrikeStrength = 5;
    private boolean ifHasSword = false;
    private boolean ifHasBow = false;
    private boolean unlockedDoor = false;
    private String pickedUpItemName = "";

    public boolean isLifeCounterIncreased() {
        return lifeCounterIncreased;
    }

    private boolean lifeCounterIncreased = false;

    public boolean hasPickedUp() {
        return pickedUp;
    }

    public void setPick(boolean pick) {
        this.pickedUp = pick;
    }

    private boolean pickedUp = false;

    public boolean isUnlockedDoor() {
        return unlockedDoor;
    }

    public void setUnlockedDoor(boolean unlockedDoor) {
        this.unlockedDoor = unlockedDoor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(Cell cell) {
        super(cell);
        setStrikeStrength(5);
        setHealth(25);
    }

    public int getLifeCounter() {
        return lifeCounter;
    }

    public void setLifeCounter(int lifeCounter) {
        this.lifeCounter = lifeCounter;
    }


    public Player getPlayer() {
        return this;
    }

    public boolean hasKey() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals("key")) {
                ifHasKey = true;
                return ifHasKey;
            }
        }
        ifHasKey = false;
        return ifHasKey;
    }

    public boolean hasSword() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals("sword")) {
                ifHasSword = true;
                return ifHasSword;
            }
        }
        ifHasSword = false;
        return ifHasSword;
    }

    public boolean hasBow() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals("bow")) {
                ifHasBow = true;
                return ifHasBow;
            }
        }
        ifHasBow = false;
        return ifHasBow;
    }


    @Override
    public Item pickUp() {
        Item item = getCell().getItem();
        if (canPickUp()) {
            items.add(item);
        }
        getCell().setItem(null);
        if(item != null){
            pickedUpItemName = item.getTileName();
            pickedUp = true;
        }
        return item;
    }

    public String getPickedUpItemName(){
        return pickedUpItemName;
    }

    public void increaseLifeCounter() {
        lifeCounter++;
        lifeCounterIncreased = true;

    }

    public void decreaseLifeCounter() {
        lifeCounter--;
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
