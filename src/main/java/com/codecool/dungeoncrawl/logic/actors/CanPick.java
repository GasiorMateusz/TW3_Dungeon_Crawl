package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.items.Item;


public interface CanPick {
    Item pickUp();

    boolean canPickUp();
}
