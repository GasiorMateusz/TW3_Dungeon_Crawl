package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.weapons.Weapon;

import java.util.stream.Stream;

public class Player extends Actor implements CanPick {
    private Inventory inventory = new Inventory();
    private String name;
    private int lifeCounter = 0;
    private final int normalStrikeStrength = 5;

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
            if(item instanceof Weapon){
                refreshPlayerStrikeStrenth();
            }
        }
        getCell().setItem(null);
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
