package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.MedicalThing;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.util.stream.Stream;

public class Player extends Actor implements CanPick {
    private Inventory inventory = new Inventory();
    private String name;
    private int lifeCounter = 0;
    private final int normalStrikeStrength = 5;
    private Actor opponent;
    private boolean unlockedDoor = false;
    private String pickedUpItemName = "";
    private boolean lifeCounterIncreased = false;
    private boolean pickedUp = false;

    public Player(Cell cell) {
        super(cell);
        setStrikeStrength(normalStrikeStrength);
        setHealth(25);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifeCounter() {
        return lifeCounter;
    }

    public Actor getOpponent() {
        return opponent;
    }


    public void setOpponent(Actor opponent) {
        this.opponent = opponent;
    }

    public boolean hasPickedUp() {
        return pickedUp;
    }

    public void setPick(boolean pick) {
        this.pickedUp = pick;
    }

    public boolean isUnlockedDoor() {
        return unlockedDoor;
    }

    public void setUnlockedDoor(boolean unlockedDoor) {
        this.unlockedDoor = unlockedDoor;
    }

    public Player getPlayer() {
        return this;
    }

    @Override
    public Item pickUp() {
        Item item = getCell().getItem();
        if (canPickUp()) {
            inventory.getItems().add(item);
            if (item instanceof Weapon) {
                refreshPlayerStrikeStrenth();
            }
            if (item instanceof MedicalThing) {
                refreshHealthValue();
                System.out.printf(Integer.toString(getCell().getActor().getHealth()));
            }
        }
        getCell().setItem(null);
        if (item != null) {
            pickedUpItemName = item.getTileName();
            pickedUp = true;
        }
        return item;
    }

    public void refreshPlayerStrikeStrenth() {
        int strength = normalStrikeStrength;

        if (((Player) getCell().getActor()).getInventory().hasSword() && ((Player) getCell().getActor()).getInventory().hasBow()) {
            strength = strength + 3;
        } else if (((Player) getCell().getActor()).getInventory().hasBow()) {
            strength = strength + 1;
        } else if (((Player) getCell().getActor()).getInventory().hasSword()) {
            strength = strength + 2;
        } else {
            strength = normalStrikeStrength;
        }
        this.setStrikeStrength(strength);
    }

    public void refreshHealthValue() {
        int health = getHealth();
        health = health + ((MedicalThing) getCell().getItem()).getIncreaseHealth();
        this.setHealth(health);
    }

    public String getPickedUpItemName() {
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

    public boolean hasDeveloperName() {
        if (!getName().equals(null)) {
            return Stream.of("Dominika", "Ania", "Dawid", "Mateusz", "Marcin")
                    .anyMatch(s -> (getName().equalsIgnoreCase(s)));
        }
        return false;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
