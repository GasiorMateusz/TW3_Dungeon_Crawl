package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.stream.Stream;

public class Player extends Actor implements CanPick {
    private Inventory inventory = new Inventory();
    private String name;
    private int lifeCounter = 0;

    public Player(Cell cell) {
        super(cell);
        setStrikeStrength(5);
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

    public Player getPlayer() {
        return this;
    }

    @Override
    public Item pickUp() {
        Item item = getCell().getItem();
        if (canPickUp()) {
            inventory.getItems().add(item);
        }
        getCell().setItem(null);
        return item;
    }

    public void increaseLifeCounter() {
        lifeCounter++;
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

    public void deleteKeyFromInventory() {
        for (int i = 0; i < inventory.getItems().size(); i++) {
            if (inventory.getItems().get(i).getTileName().equals("key")) {
                inventory.getItems().remove(i);
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

}
